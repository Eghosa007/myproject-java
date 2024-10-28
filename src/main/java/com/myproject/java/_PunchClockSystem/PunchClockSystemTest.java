package com.myproject.java._PunchClockSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PunchClockSystemTest {

    @BeforeEach
    void setUp() {
        // Ensures employee data is loaded before each test
        FileHandler.loadEmployeesFromFile();
    }

    @Test
    void testMainMenuExecution() {
        // Simple check to make sure printMainMenu executes without errors
        assertDoesNotThrow(() -> PunchClockSystem.printMainMenu());
    }

    @Test
    void testEmployeeDataLoaded() {
        // Verify that the employees list is not empty after loading
        assertNotNull(FileHandler.getEmployees(), "Employees should be loaded from file");
        assertFalse(FileHandler.getEmployees().isEmpty(), "Employee list should not be empty after loading");
    }
}