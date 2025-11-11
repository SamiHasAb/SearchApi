package org.example.search.api;

import java.util.List;

public class EmployeeResponseDTO {
  private List<EmployeeDTO> employees;
  private int currentPage;
  private long totalItems;
  private int totalPages;
  private int pageSize;
  private String searchQuery;

  // Constructors
  public EmployeeResponseDTO() {}

  public EmployeeResponseDTO(List<EmployeeDTO> employees, int currentPage,
      long totalItems, int totalPages, int pageSize) {
    this.employees = employees;
    this.currentPage = currentPage;
    this.totalItems = totalItems;
    this.totalPages = totalPages;
    this.pageSize = pageSize;
  }

  public EmployeeResponseDTO(List<EmployeeDTO> employees, int currentPage,
      long totalItems, int totalPages, int pageSize,
      String searchQuery) {
    this(employees, currentPage, totalItems, totalPages, pageSize);
    this.searchQuery = searchQuery;
  }

  // Getters and Setters
  public List<EmployeeDTO> getEmployees() { return employees; }
  public void setEmployees(List<EmployeeDTO> employees) { this.employees = employees; }

  public int getCurrentPage() { return currentPage; }
  public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

  public long getTotalItems() { return totalItems; }
  public void setTotalItems(long totalItems) { this.totalItems = totalItems; }

  public int getTotalPages() { return totalPages; }
  public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

  public int getPageSize() { return pageSize; }
  public void setPageSize(int pageSize) { this.pageSize = pageSize; }

  public String getSearchQuery() { return searchQuery; }
  public void setSearchQuery(String searchQuery) { this.searchQuery = searchQuery; }
}