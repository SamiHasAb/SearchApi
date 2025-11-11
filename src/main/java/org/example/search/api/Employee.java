package org.example.search.api;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "phone")
  private String phone;

  @Column(name = "date_of_joining")
  private LocalDate dateOfJoining;

  @Column(name = "salary")
  private Double salary;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "email")
  private String email;

  @Column(name = "department")
  private String department;

  @Column(name = "position")
  private String position;

  @Column(name = "manager_id")
  private Long managerId;

  @Column(name = "annual_bonus", precision = 10, scale = 2)
  private BigDecimal annualBonus;

  @Column(name = "employee_code", unique = true)
  private String employeeCode;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Lob
  @Column(name = "description")
  private String description;

  // Constructors
  public Employee() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public Employee(String name, Integer age, String phone, LocalDate dateOfJoining,
      Double salary, Boolean active, String email, String department,
      String position, Long managerId, BigDecimal annualBonus,
      String employeeCode, String description) {
    this();
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

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}