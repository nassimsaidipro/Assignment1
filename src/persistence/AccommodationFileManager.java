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

    // Saves the current array of accommodations to a text file
    public static void saveAccommodations(Accommodation[] accs, int count, String filePath) throws IOException {
        
        // Open a PrintWriter to write to the specified file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            
            // Loop through the array up to the current count
            for (int i = 0; i < count; i++) {
                Accommodation a = accs[i];
                
                // Identify the specific subclass to format and write its unique attributes
                if (a instanceof Hotel) {
                    Hotel h = (Hotel) a;                   
                    writer.println("HOTEL;" + h.getAccommodationId() + ";" + h.getName() + ";" + h.getLocation() + ";" + h.getPricePerNight() + ";" + h.getStarRating());                       
                } else if (a instanceof Hostel) {
                    Hostel hos = (Hostel) a;
                    writer.println("HOSTEL;" + hos.getAccommodationId() + ";" + hos.getName() + ";" + hos.getLocation() + ";" + hos.getPricePerNight() + ";" + hos.getSharedBedsPerRoom());                
                }
            }
        }
    }

    // Loads accommodation data from a text file and reconstructs the objects
    public static int loadAccommodations(Accommodation[] accs, String filePath) throws IOException {
        int count = 0;
        
        // Open a BufferedReader to read the file line-by-line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Skip any empty or blank lines at the bottom of the file
                if (line.trim().isEmpty()) continue;

                // Try to parse the line; catch errors to prevent the entire load from failing
                try {
                    // Split the line into individual data fields using the semicolon delimiter
                    String[] d = line.split(";");
                    
                    // Check the first field to determine if we are building a Hotel or a Hostel
                    switch (d[0].toUpperCase()) {
                        case "HOTEL":                            
                            accs[count++] = new Hotel(d[1], d[2], d[3], Double.parseDouble(d[4]), Integer.parseInt(d[5]));
                            break;
                        case "HOSTEL":                         
                            accs[count++] = new Hostel(d[1], d[2], d[3], Double.parseDouble(d[4]), Integer.parseInt(d[5]));
                            break;
                        default:
                            throw new Exception("Unknown accommodation type: " + d[0]);
                    }
                } catch (Exception e) {
                    // Log the specific corrupted line to the error file and move to the next line
                    ErrorLogger.log("Accommodation load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        
        // Return the number of accommodations successfully loaded into the array
        return count;
    }
}