package edu.miu.cs.cs489appsd.lab1b;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs.cs489appsd.lab1b.model.Employee;
import edu.miu.cs.cs489appsd.lab1b.model.PensionPlan;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PensionPlanApp {
    private static final List<Employee> employees = new ArrayList<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Configure ObjectMapper for pretty printing and LocalDate support
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());

        // Initialize sample data
        initializeData();
    }

    public static void main(String[] args) {
        System.out.println("All Employees (sorted by lastName ASC, yearlySalary DESC):");
        printAllEmployees();

        System.out.println("\nMonthly Upcoming Enrollees:");
        printUpcomingEnrollees();
    }

    private static void initializeData() {
        // Create employees and pension plans
        Employee emp1 = new Employee(1L, "Daniel", "Agar", 
            LocalDate.parse("2018-01-17"), 105945.50);
        emp1.setPensionPlan(new PensionPlan("EX1089", 
            LocalDate.parse("2023-01-17"), 100.00));
        
        Employee emp2 = new Employee(2L, "Bernard", "Shaw", 
            LocalDate.parse("2019-04-03"), 197750.00);
        
        Employee emp3 = new Employee(3L, "Carly", "Agar", 
            LocalDate.parse("2014-05-16"), 842000.75);
        emp3.setPensionPlan(new PensionPlan("SM2307", 
            LocalDate.parse("2019-11-04"), 1555.50));
        
        Employee emp4 = new Employee(4L, "Wesley", "Schneider", 
            LocalDate.parse("2019-09-02"), 74500.00);

        employees.addAll(List.of(emp1, emp2, emp3, emp4));
    }

    private static void printAllEmployees() {
        try {
            List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator
                    .comparing(Employee::getLastName)
                    .thenComparing(Employee::getYearlySalary, Comparator.reverseOrder()))
                .collect(Collectors.toList());
            
            System.out.println(mapper.writeValueAsString(sortedEmployees));
        } catch (Exception e) {
            System.err.println("Error printing employees: " + e.getMessage());
        }
    }

    private static void printUpcomingEnrollees() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate firstDayNextMonth = now.plusMonths(1).withDayOfMonth(1);
            LocalDate lastDayNextMonth = firstDayNextMonth.plusMonths(1).minusDays(1);

            List<Employee> upcomingEnrollees = employees.stream()
                .filter(emp -> emp.getPensionPlan() == null)
                .filter(emp -> {
                    LocalDate employmentDate = emp.getEmploymentDate();
                    Period employmentPeriod = Period.between(employmentDate, firstDayNextMonth);
                    return employmentPeriod.getYears() >= 5;
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

            System.out.println(mapper.writeValueAsString(upcomingEnrollees));
        } catch (Exception e) {
            System.err.println("Error printing upcoming enrollees: " + e.getMessage());
        }
    }
} 