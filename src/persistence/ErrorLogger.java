// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import java.io.*;
import java.time.LocalDateTime;

// This utility class provides a centralized logging mechanism for the system.
// It records non-fatal errors—primarily those encountered during file loading—to 
// an external text file. This ensures that the program can skip corrupted data 
// and continue running while leaving a timestamped audit trail for debugging.
public class ErrorLogger {
    
    // The relative path where the error log file is stored
    private static final String LOG_PATH = "output/logs/errors.txt";

    // Appends a specific error message to the log file
    public static void log(String message) {
        
        // Use FileOutputStream with 'true' to enable append mode, preventing file overwrites
        try (PrintWriter out = new PrintWriter(new FileOutputStream(LOG_PATH, true))) {
            
            // Print the current date, time, and the error message to the file
            out.println(LocalDateTime.now() + " - ERROR: " + message);
            
        } catch (IOException e) {
            // Fallback: If the log file itself cannot be accessed, print the error to the standard console
            System.err.println("Logging failed: " + message);
        }
    }
}