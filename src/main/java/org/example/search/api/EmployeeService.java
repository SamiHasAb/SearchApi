package org.example.search.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeeMapper employeeMapper;

  @PersistenceContext
  private EntityManager entityManager;

  public Page<EmployeeDTO> getAllEmployees(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Employee> employeePage = employeeRepository.findAll(pageable);

    return employeePage.map(employeeMapper::toDTO);
  }

  public Page<EmployeeDTO> searchEmployees(String searchTerm, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Employee> employeePage = employeeRepository.searchEmployees(searchTerm, pageable);

    return employeePage.map(employeeMapper::toDTO);
  }

  // Advanced search with multiple fields
  public Page<EmployeeDTO> advancedSearch(EmployeeSearchRequestDTO searchRequest) {
    Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());

    Page<Employee> employeePage = employeeRepository.advancedSearch(
        searchRequest.getName(),
        searchRequest.getMinAge(),
        searchRequest.getMaxAge(),
        searchRequest.getMinSalary(),
        searchRequest.getMaxSalary(),
        searchRequest.getDepartment(),
        searchRequest.getPosition(),
        searchRequest.getActive(),
        pageable
    );

    return employeePage.map(employeeMapper::toDTO);
  }

  //  Dynamic search using HashMap
  public Page<EmployeeDTO> dynamicSearch(Map<String, Object> searchParams, int page, int size) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
    Root<Employee> root = query.from(Employee.class);

    List<Predicate> predicates = new ArrayList<>();

    // Iterate through all search parameters
    for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();

      if (value != null && !value.toString().trim().isEmpty()) {
        switch (key.toLowerCase()) {
          case "name":
            predicates.add(cb.like(cb.lower(root.get("name")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "age":
            predicates.add(cb.equal(root.get("age"), Integer.parseInt(value.toString())));
            break;
          case "minage":
            predicates.add(cb.greaterThanOrEqualTo(root.get("age"),
                Integer.parseInt(value.toString())));
            break;
          case "maxage":
            predicates.add(cb.lessThanOrEqualTo(root.get("age"),
                Integer.parseInt(value.toString())));
            break;
          case "salary":
            predicates.add(cb.equal(root.get("salary"),
                Double.parseDouble(value.toString())));
            break;
          case "minsalary":
            predicates.add(cb.greaterThanOrEqualTo(root.get("salary"),
                Double.parseDouble(value.toString())));
            break;
          case "maxsalary":
            predicates.add(cb.lessThanOrEqualTo(root.get("salary"),
                Double.parseDouble(value.toString())));
            break;
          case "phone":
            predicates.add(cb.like(cb.lower(root.get("phone")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "email":
            predicates.add(cb.like(cb.lower(root.get("email")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "department":
            predicates.add(cb.like(cb.lower(root.get("department")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "position":
            predicates.add(cb.like(cb.lower(root.get("position")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "active":
            predicates.add(cb.equal(root.get("active"),
                Boolean.parseBoolean(value.toString())));
            break;
          case "employeecode":
            predicates.add(cb.like(cb.lower(root.get("employeeCode")),
                "%" + value.toString().toLowerCase() + "%"));
            break;
          case "managerid":
            predicates.add(cb.equal(root.get("managerId"),
                Long.parseLong(value.toString())));
            break;
        }
      }
    }

    query.where(predicates.toArray(new Predicate[0]));

    // Get total count for pagination
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<Employee> countRoot = countQuery.from(Employee.class);
    countQuery.select(cb.count(countRoot));
    countQuery.where(predicates.toArray(new Predicate[0]));

    Long totalCount = entityManager.createQuery(countQuery).getSingleResult();

    // Apply pagination
    TypedQuery<Employee> typedQuery = entityManager.createQuery(query);
    typedQuery.setFirstResult(page * size);
    typedQuery.setMaxResults(size);

    List<Employee> resultList = typedQuery.getResultList();

    // Convert to Page
    List<EmployeeDTO> employeeDTOs = employeeMapper.toDTOList(resultList);

    return new org.springframework.data.domain.PageImpl<>(
        employeeDTOs,
        PageRequest.of(page, size),
        totalCount
    );
  }

  public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
    Employee employee = employeeMapper.toEntity(employeeDTO);
    Employee savedEmployee = employeeRepository.save(employee);
    return employeeMapper.toDTO(savedEmployee);
  }

  public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
    Employee existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

    // Update fields
    existingEmployee.setName(employeeDTO.getName());
    existingEmployee.setAge(employeeDTO.getAge());
    existingEmployee.setPhone(employeeDTO.getPhone());
    existingEmployee.setDateOfJoining(employeeDTO.getDateOfJoining());
    existingEmployee.setSalary(employeeDTO.getSalary());
    existingEmployee.setActive(employeeDTO.getActive());
    existingEmployee.setEmail(employeeDTO.getEmail());
    existingEmployee.setDepartment(employeeDTO.getDepartment());
    existingEmployee.setPosition(employeeDTO.getPosition());
    existingEmployee.setManagerId(employeeDTO.getManagerId());
    existingEmployee.setAnnualBonus(employeeDTO.getAnnualBonus());
    existingEmployee.setEmployeeCode(employeeDTO.getEmployeeCode());
    existingEmployee.setDescription(employeeDTO.getDescription());

    Employee updatedEmployee = employeeRepository.save(existingEmployee);
    return employeeMapper.toDTO(updatedEmployee);
  }

  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }

  public List<Employee> saveAllEmployees(List<Employee> employees) {
    return employeeRepository.saveAll(employees);
  }

  public EmployeeDTO getEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    return employeeMapper.toDTO(employee);
  }


}
