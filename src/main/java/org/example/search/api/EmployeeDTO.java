package org.example.search.api;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDTO {

  private Long id;

  @NotBlank(message = "Name is mandatory")
  @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
  private String name;

  @Min(value = 18, message = "Age must be at least 18")
  @Max(value = 65, message = "Age must not exceed 65")
  private Integer age;

  @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number format")
  private String phone;

  @PastOrPresent(message = "Date of joining must be in the past or present")
  private LocalDate dateOfJoining;

  @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
  private Double salary;

  private Boolean active;

  @Email(message = "Invalid email format")
  private String email;

  @NotBlank(message = "Department is mandatory")
  private String department;

  @NotBlank(message = "Position is mandatory")
  private String position;

  private Long managerId;

  @DecimalMin(value = "0.0", message = "Annual bonus cannot be negative")
  private BigDecimal annualBonus;

  @NotBlank(message = "Employee code is mandatory")
  private String employeeCode;

  private LocalDateTime createdAt;

  @Size(max = 500, message = "Description cannot exceed 500 characters")
  private String description;

  // Default constructor
  public EmployeeDTO() {
  }

  // Constructor from Entity
  public EmployeeDTO(Long id, String name, Integer age, String phone, LocalDate dateOfJoining,
      Double salary, Boolean active, String email, String department,
      String position, Long managerId, BigDecimal annualBonus,
      String employeeCode, LocalDateTime createdAt, String description) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.phone = phone;
    this.dateOfJoining = dateOfJoining;
    this.salary = salary;
    this.active = active;
    this.email = email;
    this.department = department;
    this.position = position;
    this.managerId = managerId;
    this.annualBonus = annualBonus;
    this.employeeCode = employeeCode;
    this.createdAt = createdAt;
    this.description = description;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getDateOfJoining() {
    return dateOfJoining;
  }

  public void setDateOfJoining(LocalDate dateOfJoining) {
    this.dateOfJoining = dateOfJoining;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public Long getManagerId() {
    return managerId;
  }

  public void setManagerId(Long managerId) {
    this.managerId = managerId;
  }

  public BigDecimal getAnnualBonus() {
    return annualBonus;
  }

  public void setAnnualBonus(BigDecimal annualBonus) {
    this.annualBonus = annualBonus;
  }

  public String getEmployeeCode() {
    return employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
