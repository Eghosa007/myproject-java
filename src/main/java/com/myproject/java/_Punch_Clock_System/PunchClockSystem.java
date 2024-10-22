package com.myproject.java._Punch_Clock_System;

import com.myproject.java._Punch_Clock_System.FileHandler;

import java.io.*;
import java.util.Scanner;

public class PunchClockSystem {
    public static void main(String[] args) {
        FileHandler.loadEmployeesFromFile(); // Load employee data at the start

        Scanner scanner = new Scanner(System.in);
        boolean runApp = true;

        do {
            printMainMenu();
            try {
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> managementMenu(scanner);
                    case 2 -> employeeMenu(scanner);
                    case 3 -> contractorMenu(scanner);
                    case 0 -> {
                        System.out.println("Exiting system... Goodbye!");
                        runApp = false;
                        FileHandler.updateEmployeeFile(); // Update employee file before exit
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        } while (runApp);

        scanner.close(); // Ensure the scanner is closed
    }

    private static void printMainMenu() {
        System.out.println("============= Punch Clock Main Menu ============");
        System.out.println("1. Management Menu");
        System.out.println("2. Employee Menu");
        System.out.println("3. Contractor Menu");
        System.out.println("0. Exit");
    }

    private static void managementMenu(Scanner scanner) {
        boolean inManagementMenu = true;
        do {
            System.out.println("============= Management Menu ============");
            System.out.println("1. Clock-In");
            System.out.println("2. Clock-Out");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Management");
                case 2 -> handleClockOut("Management");
                case 0 -> inManagementMenu = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (inManagementMenu);
    }

    private static void employeeMenu(Scanner scanner) {
        boolean inEmployeeMenu = true;
        do {
            System.out.println("============= Employee Menu ============");
            System.out.println("1. Clock-In");
            System.out.println("2. Clock-Out");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Employee");
                case 2 -> handleClockOut("Employee");
                case 0 -> inEmployeeMenu = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (inEmployeeMenu);
    }

    private static void contractorMenu(Scanner scanner) {
        boolean inContractorMenu = true;
        do {
            System.out.println("============= Contractor Menu ============");
            System.out.println("1. Clock-In");
            System.out.println("2. Clock-Out");
            System.out.println("0. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Contractor");
                case 2 -> handleClockOut("Contractor");
                case 0 -> inContractorMenu = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (inContractorMenu);
    }

    private static void handleClockIn(String role) {
        Employee employee = getEmployee(role);
        if (employee != null) {
            AttendanceManager.clockIn(employee);
        } else {
            System.out.println("Employee not found or role mismatch.");
        }
    }

    private static void handleClockOut(String role) {
        Employee employee = getEmployee(role);
        if (employee != null) {
            AttendanceManager.clockOut(employee);
        } else {
            System.out.println("Employee not found or role mismatch.");
        }
    }

    // Method to find an employee based on ID and role
    private static Employee getEmployee(String role) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your ID: ");
        String employeeID = scanner.nextLine();

        // Search through the loaded employees
        for (Employee employee : FileHandler.getEmployees()) {
            if (employee.getEmployeeID().equals(employeeID) && employee.getRole().equals(role)) {
                return employee; // Return the matching employee if ID and role match
            }
        }

        return null; // Return null if no matching employee is found
    }
}
