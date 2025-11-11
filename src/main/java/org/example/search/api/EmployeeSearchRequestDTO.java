package org.example.search.api;

public class EmployeeSearchRequestDTO {

  private String name;
  private Integer minAge;
  private Integer maxAge;
  private Double minSalary;
  private Double maxSalary;
  private String department;
  private String position;
  private Boolean active;
  private int page = 0;
  private int size = 20;

  // Constructors
  public EmployeeSearchRequestDTO() {
  }

  public EmployeeSearchRequestDTO(String name, Integer minAge, Integer maxAge,
      Double minSalary, Double maxSalary, String department,
      String position, Boolean active, int page, int size) {
    this.name = name;
    this.minAge = minAge;
    this.maxAge = maxAge;
    this.minSalary = minSalary;
    this.maxSalary = maxSalary;
    this.department = department;
    this.position = position;
    this.active = active;
    this.page = page;
    this.size = size;
  }

  // Getters and Setters
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMinAge() {
    return minAge;
  }

  public void setMinAge(Integer minAge) {
    this.minAge = minAge;
  }

  public Integer getMaxAge() {
    return maxAge;
  }

  public void setMaxAge(Integer maxAge) {
    this.maxAge = maxAge;
  }

  public Double getMinSalary() {
    return minSalary;
  }

  public void setMinSalary(Double minSalary) {
    this.minSalary = minSalary;
  }

  public Double getMaxSalary() {
    return maxSalary;
  }

  public void setMaxSalary(Double maxSalary) {
    this.maxSalary = maxSalary;
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

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

}
