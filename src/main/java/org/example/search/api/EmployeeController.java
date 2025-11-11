package org.example.search.api;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<EmployeeResponseDTO> getAllEmployees(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    Page<EmployeeDTO> employeePage = employeeService.getAllEmployees(page, size);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        size
    );

    return ResponseEntity.ok(response);
  }

  @GetMapping("/search")
  public ResponseEntity<EmployeeResponseDTO> searchEmployees(
      @RequestParam String query,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    Page<EmployeeDTO> employeePage = employeeService.searchEmployees(query, page, size);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        size,
        query
    );

    return ResponseEntity.ok(response);
  }

  //  Advanced search endpoint
  @PostMapping("/advanced-search")
  public ResponseEntity<EmployeeResponseDTO> advancedSearch(
      @Valid @RequestBody EmployeeSearchRequestDTO searchRequest) {

    Page<EmployeeDTO> employeePage = employeeService.advancedSearch(searchRequest);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        searchRequest.getSize()
    );

    return ResponseEntity.ok(response);
  }

  //  Query parameter based advanced search (alternative)
  @GetMapping("/advanced-search")
  public ResponseEntity<EmployeeResponseDTO> advancedSearchByParams(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer minAge,
      @RequestParam(required = false) Integer maxAge,
      @RequestParam(required = false) Double minSalary,
      @RequestParam(required = false) Double maxSalary,
      @RequestParam(required = false) String department,
      @RequestParam(required = false) String position,
      @RequestParam(required = false) Boolean active,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    EmployeeSearchRequestDTO searchRequest = new EmployeeSearchRequestDTO();
    searchRequest.setName(name);
    searchRequest.setMinAge(minAge);
    searchRequest.setMaxAge(maxAge);
    searchRequest.setMinSalary(minSalary);
    searchRequest.setMaxSalary(maxSalary);
    searchRequest.setDepartment(department);
    searchRequest.setPosition(position);
    searchRequest.setActive(active);
    searchRequest.setPage(page);
    searchRequest.setSize(size);

    Page<EmployeeDTO> employeePage = employeeService.advancedSearch(searchRequest);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        size
    );

    return ResponseEntity.ok(response);
  }

  //  Dynamic search endpoint using HashMap
  @PostMapping("/dynamic-search")
  public ResponseEntity<EmployeeResponseDTO> dynamicSearch(
      @RequestBody Map<String, Object> searchParams,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    // Log the received parameters for debugging
    System.out.println("Received search parameters: " + searchParams);

    Page<EmployeeDTO> employeePage = employeeService.dynamicSearch(searchParams, page, size);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        size
    );

    // Add search parameters to response for reference
    response.setSearchQuery("Dynamic search with: " + searchParams.toString());

    return ResponseEntity.ok(response);
  }

  //  GET endpoint for dynamic search (alternative)
  @GetMapping("/dynamic-search")
  public ResponseEntity<EmployeeResponseDTO> dynamicSearchGet(
      @RequestParam Map<String, Object> allParams,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "20") int size) {

    // Remove page and size from search parameters as they are handled separately
    Map<String, Object> searchParams = new HashMap<>(allParams);
    searchParams.remove("page");
    searchParams.remove("size");

    System.out.println("Received search parameters: " + searchParams);

    Page<EmployeeDTO> employeePage = employeeService.dynamicSearch(searchParams, page, size);

    EmployeeResponseDTO response = new EmployeeResponseDTO(
        employeePage.getContent(),
        employeePage.getNumber(),
        employeePage.getTotalElements(),
        employeePage.getTotalPages(),
        size
    );

    response.setSearchQuery("Dynamic search with: " + searchParams.toString());

    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
    EmployeeDTO employee = employeeService.getEmployeeById(id);
    return ResponseEntity.ok(employee);
  }

  @PostMapping()
  public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
    EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(
      @PathVariable Long id,
      @Valid @RequestBody EmployeeDTO employeeDTO) {
    EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
    return ResponseEntity.ok(updatedEmployee);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
    return ResponseEntity.noContent().build();
  }
}
