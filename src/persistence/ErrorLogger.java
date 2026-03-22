package persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger {
    private static final String LOG_PATH = "output/logs/errors.txt";
    
    // Arrays to hold the errors during the batch load
    private static String[] errorList = new String[200]; 
    private static int errorCount = 0;

    // Call this before a new load starts to reset the bucket
    public static void clear() {
        errorCount = 0;
        errorList = new String[200];
    }

    // Collects an error instead of printing it immediately
    public static void log(String message) {
        if (errorCount < errorList.length) {
            errorList[errorCount] = message;
            errorCount++;
        }
    }

    // Prints the beautiful formatted block to the text file
    public static void generateReport(String sourceFolder) {
        if (errorCount == 0) return; // Don't make a file if there were no errors

        // Ensure the folder exists
        new File("output/logs").mkdirs();

        try (PrintWriter out = new PrintWriter(new FileOutputStream(LOG_PATH, true))) {
            out.println("================================================================================================================================");
            out.println("BATCH LOAD ERRORS");
            out.println("Source: " + sourceFolder);
            
            // Format the time exactly like the example
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            out.println("Time:   " + dtf.format(LocalDateTime.now()));
            out.println("Count:  " + errorCount);
            out.println();
            
      
            for (int i = 0; i < errorCount; i++) {
                out.printf(" %d: %s\n", (i + 1), errorList[i]);
            }
            out.println("================================================================================================================================");
            out.println(); // Add a blank line for the next batch
            
        } catch (IOException e) {
            System.err.println("Logging failed to write to file.");
        }
    }
}