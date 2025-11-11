package org.example.search.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  // Global search across all fields
  @Query("SELECT e FROM Employee e WHERE " +
      "LOWER(e.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(e.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(e.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(e.department) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(e.position) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "CAST(e.age AS string) LIKE CONCAT('%', :searchTerm, '%') OR " +
      "CAST(e.salary AS string) LIKE CONCAT('%', :searchTerm, '%') OR " +
      "CAST(e.dateOfJoining AS string) LIKE CONCAT('%', :searchTerm, '%')")
  Page<Employee> searchEmployees(@Param("searchTerm") String searchTerm, Pageable pageable);

  // Advanced search with multiple specific fields
  @Query("SELECT e FROM Employee e WHERE " +
      "(:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
      "(:minAge IS NULL OR e.age >= :minAge) AND " +
      "(:maxAge IS NULL OR e.age <= :maxAge) AND " +
      "(:minSalary IS NULL OR e.salary >= :minSalary) AND " +
      "(:maxSalary IS NULL OR e.salary <= :maxSalary) AND " +
      "(:department IS NULL OR LOWER(e.department) LIKE LOWER(CONCAT('%', :department, '%'))) AND " +
      "(:position IS NULL OR LOWER(e.position) LIKE LOWER(CONCAT('%', :position, '%'))) AND " +
      "(:active IS NULL OR e.active = :active)")
  Page<Employee> advancedSearch(
      @Param("name") String name,
      @Param("minAge") Integer minAge,
      @Param("maxAge") Integer maxAge,
      @Param("minSalary") Double minSalary,
      @Param("maxSalary") Double maxSalary,
      @Param("department") String department,
      @Param("position") String position,
      @Param("active") Boolean active,
      Pageable pageable);

}
