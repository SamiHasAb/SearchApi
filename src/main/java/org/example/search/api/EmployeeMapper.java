package org.example.search.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

  // Convert Entity to DTO
  public EmployeeDTO toDTO(Employee employee) {
    if (employee == null) {
      return null;
    }

    return new EmployeeDTO(
        employee.getId(),
        employee.getName(),
        employee.getAge(),
        employee.getPhone(),
        employee.getDateOfJoining(),
        employee.getSalary(),
        employee.getActive(),
        employee.getEmail(),
        employee.getDepartment(),
        employee.getPosition(),
        employee.getManagerId(),
        employee.getAnnualBonus(),
        employee.getEmployeeCode(),
        employee.getCreatedAt(),
        employee.getDescription()
    );
  }

  // Convert DTO to Entity
  public Employee toEntity(EmployeeDTO employeeDTO) {
    if (employeeDTO == null) {
      return null;
    }

    Employee employee = new Employee();
    employee.setId(employeeDTO.getId());
    employee.setName(employeeDTO.getName());
    employee.setAge(employeeDTO.getAge());
    employee.setPhone(employeeDTO.getPhone());
    employee.setDateOfJoining(employeeDTO.getDateOfJoining());
    employee.setSalary(employeeDTO.getSalary());
    employee.setActive(employeeDTO.getActive());
    employee.setEmail(employeeDTO.getEmail());
    employee.setDepartment(employeeDTO.getDepartment());
    employee.setPosition(employeeDTO.getPosition());
    employee.setManagerId(employeeDTO.getManagerId());
    employee.setAnnualBonus(employeeDTO.getAnnualBonus());
    employee.setEmployeeCode(employeeDTO.getEmployeeCode());
    employee.setDescription(employeeDTO.getDescription());

    return employee;
  }

  // Convert List of Entities to List of DTOs
  public List<EmployeeDTO> toDTOList(List<Employee> employees) {
    return employees.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

  // Convert List of DTOs to List of Entities
  public List<Employee> toEntityList(List<EmployeeDTO> employeeDTOs) {
    return employeeDTOs.stream()
        .map(this::toEntity)
        .collect(Collectors.toList());
  }
}
