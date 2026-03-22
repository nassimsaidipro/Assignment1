// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import travel.*;
import java.io.*;

// This class manages file persistence for Accommodation objects. It handles 
// writing Hotel and Hostel data to a text file using a semicolon-separated format, 
// and reading that file to reconstruct the objects back into an array. It also 
// ensures that any corrupted data lines are logged safely without crashing the program.
public class AccommodationFileManager {

    public static int loadAccommodations(Accommodation[] accs, String filePath) throws IOException {
        int count = 0;
        int recordNumber = 0; // Tracks the line number in the CSV

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recordNumber++;
                if (line.trim().isEmpty()) continue;

                try {
                    String[] d = line.split(";");
                    // Assuming d[1] is the Type (HOTEL, HOSTEL)
                    String type = d[1].toUpperCase();

                    Accommodation a;
                    if (type.equals("HOTEL")) {
                        a = new Hotel(d[0], d[2], d[3], Double.parseDouble(d[4]), Integer.parseInt(d[5]));
                    } else if (type.equals("HOSTEL")) {
                        a = new Hostel(d[0], d[2], d[3], Double.parseDouble(d[4]), Integer.parseInt(d[5]));
                    } else {
                        // This triggers Error #3: Unknown Accommodation type
                        throw new Exception("Accommodation Error: Unknown Accommodation type: " + type);
                    }

                    accs[count++] = a;

                } catch (Exception e) {
                    // Logs to the ErrorLogger bucket for the final report
                    ErrorLogger.log("Invalid Accommodation Exception: Record number " + recordNumber + 
                                    " in accommodations file. " + e.getMessage());
                }
            }
        }
        return count;
    }

    public static void saveAccommodations(Accommodation[] accs, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                Accommodation a = accs[i];
                String type = (a instanceof Hotel) ? "HOTEL" : "HOSTEL";
                writer.println(a.getAccommodationId() + ";" + type + ";" + a.getName() + ";" + 
                               a.getLocation() + ";" + a.getPricePerNight());
            }
        }
    }
}