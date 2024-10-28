package com.myproject.java._PunchClockSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceManager {
    
	// Records the clock-in time for an employee.
    public static void clockIn(Employee employee) {
        String dateTime = getCurrentDateTime();
        System.out.println(employee.getFullName() + " Clocked in at " + dateTime);
        updateAttendanceFile(employee.getEmployeeID(), "ClockInTime", dateTime);
    }
    
    //Records the clock-out time for an employee.
    public static void clockOut(Employee employee) {
        String dateTime = getCurrentDateTime();
        System.out.println(employee.getFullName() + " Clocked out at " + dateTime);
        updateAttendanceFile(employee.getEmployeeID(), "ClockOutTime", dateTime);
    }
    
//Retrieves the current date and time formatted as "yyyy/MM/dd HH:mm:ss".
    private static String getCurrentDateTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return time.format(LocalDateTime.now());
    }

    //Appends the clock-in or clock-out action to the attendance file.
    private static void updateAttendanceFile(String employeeID, String action, String dateTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            writer.write(employeeID + "," + action + "," + dateTime);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error updating attendance file: " + e.getMessage());
        }
    }
}
