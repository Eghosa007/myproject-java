package com.myproject.java._PunchClockSystem;

import java.io.*;
import java.util.*;

public class FileManager {

    // Method to update the file with clock in or clock out times
    public static void updateFile(String employeeID, String action, String dateTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
            writer.write(employeeID + "," + action + "," + dateTime);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error updating attendance file: " + e.getMessage());
        }
    }
}

