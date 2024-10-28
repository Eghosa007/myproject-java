package com.myproject.java._PunchClockSystem;

public class Employee extends Color {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String role;
    private String department;

    // Constructor
    public Employee(String firstName, String lastName, String employeeID, String role, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.role = role;
        this.department = department;
    }

    // Getter for employee ID
    public String getEmployeeID() {
        return employeeID;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Getter for department
    public String getDepartment() {
        return department;
    }

    // Method to get the full name of the employee
    public String getFullName() {
        return GREEN + firstName + " " + lastName + RESET; // Concatenate first and last name
    }

    // Setter methods
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
