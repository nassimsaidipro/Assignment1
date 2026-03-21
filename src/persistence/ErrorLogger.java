package persistence;

import java.io.*;
import java.time.LocalDateTime;

public class ErrorLogger {
    private static final String LOG_PATH = "output/logs/errors.txt";

    public static void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(LOG_PATH, true))) {
            out.println(LocalDateTime.now() + " - ERROR: " + message);
        } catch (IOException e) {
            System.err.println("Logging failed: " + message);
        }
    }
}
