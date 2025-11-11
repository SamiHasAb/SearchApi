package org.example.search.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {


  @Autowired
  private EmployeeService employeeService;

  private final Random random = new Random();
  private final String[] departments = {"Engineering", "Marketing", "Sales", "HR", "Finance", "Operations"};
  private final String[] positions = {"Developer", "Manager", "Analyst", "Director", "Specialist", "Coordinator"};
  private final String[] firstNames = {"John", "Jane", "Michael", "Sarah", "David", "Lisa", "Robert", "Emily", "William", "Jessica"};
  private final String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};

  @Override
  public void run(String... args) throws Exception {
    // Check if employees already exist to avoid duplicates
    if (employeeService.getAllEmployees(0, 1).getTotalElements() == 0) {
      // Create 1000 sample employees
      List<Employee> employees = new ArrayList<>();

      for (int i = 1; i <= 1000; i++) {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];

        Employee employee = new Employee();
        employee.setName(firstName + " " + lastName);
        employee.setAge(20 + random.nextInt(40)); // Age between 20-60
        employee.setPhone("+1-555-" + String.format("%04d", random.nextInt(10000)));
        employee.setDateOfJoining(LocalDate.now().minusDays(random.nextInt(365 * 5)));
        employee.setSalary(30000.0 + random.nextDouble() * 120000);
        employee.setActive(random.nextBoolean());
        employee.setEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + i + "@company.com");
        employee.setDepartment(departments[random.nextInt(departments.length)]);
        employee.setPosition(positions[random.nextInt(positions.length)]);
        employee.setManagerId(random.nextBoolean() ? (long) random.nextInt(50) + 1 : null);
        employee.setAnnualBonus(new BigDecimal(1000 + random.nextInt(15000)));
        employee.setEmployeeCode("EMP" + String.format("%05d", i));
        employee.setDescription("This is a sample description for " + firstName + " " + lastName);

        employees.add(employee);
      }

      employeeService.saveAllEmployees(employees);
      System.out.println("Created 1000 sample employees");
    }
  }
}
