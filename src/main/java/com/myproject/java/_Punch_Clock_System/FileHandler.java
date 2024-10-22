package com.myproject.java._Punch_Clock_System;

import com.myproject.java._Punch_Clock_System.FileHandler;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String EMPLOYEE_FILE = "employeeData.txt";
    private static final String ATTENDANCE_FILE = "attendance.txt"; // Ensure this is defined
    public static List<Employee> employees = new ArrayList<>();

    // Method to load employees from a file
    public static void loadEmployeesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String employeeID = parts[2];
                    String role = parts[3];
                    String department = parts[4];
                    Employee employee = new Employee(firstName, lastName, employeeID, role, department);
                    employees.add(employee);
                }
            }
            System.out.println("Employees loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
        }
    }

    // Method to get the list of employees
    public static List<Employee> getEmployees() {
        return employees;
    }

    // Method to update the employee file with attendance info
    public static void updateEmployeeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE_FILE))) {
            for (Employee employee : employees) {
                writer.write(employee.toString());
                writer.newLine();
            }
            System.out.println("Employee file updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating employee file: " + e.getMessage());
        }
    }
}
