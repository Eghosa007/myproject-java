package com.myproject.java._PunchClockSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceManager {
    
    public static void clockIn(Employee employee) {
        String dateTime = getCurrentDateTime();
        System.out.println(employee.getFullName() + " Clocked in at " + dateTime);
        updateAttendanceFile(employee.getEmployeeID(), "ClockInTime", dateTime);
    }
    
    public static void clockOut(Employee employee) {
        String dateTime = getCurrentDateTime();
        System.out.println(employee.getFullName() + " Clocked out at " + dateTime);
        updateAttendanceFile(employee.getEmployeeID(), "ClockOutTime", dateTime);
    }
    
    private static String getCurrentDateTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return time.format(LocalDateTime.now());
    }

    private static void updateAttendanceFile(String employeeID, String action, String dateTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            writer.write(employeeID + "," + action + "," + dateTime);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error updating attendance file: " + e.getMessage());
        }
    }
}
