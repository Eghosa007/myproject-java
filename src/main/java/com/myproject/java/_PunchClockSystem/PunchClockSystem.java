package com.myproject.java._PunchClockSystem;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.myproject.java._PunchClockSystem.FileHandler;

import java.util.Date;

public class PunchClockSystem extends Color{
    public static void main(String[] args) {

    	// Load employee data at the start
        FileHandler.loadEmployeesFromFile(); 
        
       //Initializes a Scanner object to read user input.
        Scanner scanner = new Scanner(System.in);
        boolean runApp = true;
        
        // Do while loop to display the menu until the user exits

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
                        System.out.println(BLUE + "Exiting system... Goodbye!" + RESET);
                        runApp = false;
                        FileHandler.updateEmployeeFile(); // Update employee file before exit
                    }
                    default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Error: Please enter a valid number." + RESET);
            }
        
        } while (runApp);
        
        scanner.close(); // Ensure the scanner is closed
    }
  
    private static RoleMessage getRoleMessage(String role) {
        switch (role) {
            case "Contractor":
                return new ContractorMessage("2025-12-31"); // contract end date message
            default:
                return new RoleMessage();
        }
    }
    

    public static void printMainMenu() {
        System.out.println(PURPLE + "============= Punch Clock Main Menu ============" + RESET);
        System.out.println(BLUE + "1. Management Menu" + RESET);
        System.out.println(BLUE + "2. Employee Menu" + RESET);
        System.out.println(BLUE + "3. Contractor Menu" + RESET);
        System.out.println(RED + "0. Exit" + RESET);
    }

    private static void managementMenu(Scanner scanner) {
        boolean inManagementMenu = true;
        do {
            System.out.println(PURPLE + "============= Management Menu ============" + RESET);
            System.out.println(BLUE + "1. Clock-In" + RESET);
            System.out.println(BLUE + "2. Clock-Out" + RESET);
            System.out.println(RED + "0. Exit to Main Menu" + RESET);
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Management");
                case 2 -> handleClockOut("Management");
                case 0 -> inManagementMenu = false;
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        } while (inManagementMenu);
    }

    private static void employeeMenu(Scanner scanner) {
        boolean inEmployeeMenu = true;
        do {
            System.out.println(PURPLE + "============= Employee Menu ============" + RESET);
            System.out.println(BLUE + "1. Clock-In" + RESET);
            System.out.println(BLUE + "2. Clock-Out" + RESET);
            System.out.println(RED + "0. Exit to Main Menu" + RESET);
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Employee");
                case 2 -> handleClockOut("Employee");
                case 0 -> inEmployeeMenu = false;
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        } while (inEmployeeMenu);
    }

    private static void contractorMenu(Scanner scanner) {
        boolean inContractorMenu = true;
        do {
            System.out.println(PURPLE + "============= Contractor Menu ============" + RESET);
            System.out.println(BLUE + "1. Clock-In"+ RESET);
            System.out.println(BLUE + "2. Clock-Out" + RESET);
            System.out.println(RED + "0. Exit to Main Menu" + RESET);
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> handleClockIn("Contractor");
                case 2 -> handleClockOut("Contractor");
                case 0 -> inContractorMenu = false;
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        } while (inContractorMenu);
    }

    private static void handleClockIn(String role) {
        Employee employee = getEmployee(role);
        if (employee != null) {
            logAttendance(employee, "ClockInTime");
            if (role.equals("Contractor")) {
                // Display the contractor message only after successful clock-in
                System.out.println(YELLOW + "Contractor: Your contract expires on 2025-12-31." + RESET);
            }
        } else {
            System.out.println(RED + "Employee not found or role mismatch." + RESET);
        }
       }
     
    

    private static void handleClockOut(String role) {
        Employee employee = getEmployee(role);
        if (employee != null) {
            logAttendance(employee, "ClockOutTime");
        } else {
            System.out.println(RED + "Employee not found or role mismatch." + RESET);
        }
    }

    // Method to find an employee based on ID and role
    private static Employee getEmployee(String role) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your ID: ");
        String inputEmployeeID = scanner.nextLine();

        // Search through the loaded employees
        for (Employee employee : FileHandler.getEmployees()) {
            // Use equalsIgnoreCase to make the ID comparison case-insensitive
            if (employee.getEmployeeID().equalsIgnoreCase(inputEmployeeID) && employee.getRole().equals(role)) {
                return employee; // Return the matching employee if ID (case-insensitive) and role match
            }
        }

        return null; // Return null if no matching employee is found
    }


 // Log attendance (ClockInTime or ClockOutTime) for the given employee
    private static void logAttendance(Employee employee, String eventType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            String department = employee.getDepartment();
            String employeeID = employee.getEmployeeID();

            // Get the current timestamp
            String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

            // Log format: EMPID,FullName,Department,EventType,Timestamp
            String logEntry = employeeID + "," + fullName + "," + department + "," + eventType + "," + timestamp;

            // Write the log entry to the attendance.txt file
            writer.write(logEntry);
            writer.newLine();

            // Display friendly message to the user
            if (eventType.equals("ClockInTime")) {
                System.out.println(GREEN + fullName + " has successfully clocked in." + RESET);
            } else if (eventType.equals("ClockOutTime")) {
                System.out.println(GREEN + fullName + " has successfully clocked out." + RESET);
            }
        } catch (IOException e) {
            System.out.println(RED + "Error logging attendance: " + e.getMessage() + RESET);
        }
    }
    
}
