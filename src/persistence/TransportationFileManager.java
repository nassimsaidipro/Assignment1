// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import travel.*;
import java.io.*;

// This class manages the persistence of Transportation objects (Flight, Train, Bus).
// It enables the system to serialize polymorphic transportation data into a 
// semicolon-delimited text file and deserialize it back into an array using 
// specific constructors to preserve original transport IDs and data integrity.
public class TransportationFileManager {

    // Saves the array of transportation options to a text file
    public static void saveTransports(Transportation[] trans, int count, String filePath) throws IOException {
        
        // Open a PrintWriter to write data to the specified file path
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            
            // Iterate through the transportation array up to the active count
            for (int i = 0; i < count; i++) {
                
                Transportation t = trans[i];               
                
                // Use instance checking to handle subclass-specific fields during serialization
                if (t instanceof Flight) {
                    Flight f = (Flight) t;
                    writer.println("FLIGHT;" + f.getTransportId() + ";" + f.getCompanyName() + ";" + f.getDepartureCity() + ";" + f.getArrivalCity() + ";" + f.calculateCost() + ";" + f.getLuggageAllowance());
                } else if (t instanceof Train) {
                    Train tr = (Train) t;
                    writer.println("TRAIN;" + tr.getTransportId() + ";" + tr.getCompanyName() + ";" + tr.getDepartureCity() + ";" + tr.getArrivalCity() + ";" + tr.calculateCost() + ";" + tr.getTrainType());
                } else if (t instanceof Bus) {
                    Bus b = (Bus) t;
                    writer.println("BUS;" + b.getTransportId() + ";" + b.getCompanyName() + ";" + b.getDepartureCity() + ";" + b.getArrivalCity() + ";" + b.calculateCost() + ";" + b.getNumberOfStops());
                }
            }
        }
    }

    // Loads transportation data from a text file and populates the provided array
    public static int loadTransports(Transportation[] trans, String filePath) throws IOException {
        int count = 0;
        
        // Open a BufferedReader to read the file line-by-line
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Skip empty lines to avoid parsing errors
                if (line.trim().isEmpty()) continue;
                
                try {
                    // Split the line into an array of data strings using the semicolon delimiter
                    String[] d = line.split(";");                                           
                    
                    // Reconstruct the appropriate object type based on the prefix tag
                    switch (d[0].toUpperCase()) {
                        case "FLIGHT":                        
                            // Recreates Flight using original ID and parsed double values
                            trans[count++] = new Flight(d[1], d[2], d[3], d[4], d[2], Double.parseDouble(d[6]), Double.parseDouble(d[5])); 
                            break;
                        case "TRAIN": 
                            // Recreates Train using original ID and parsed values
                            trans[count++] = new Train(d[1], d[2], d[3], d[4], d[6], "Standard", Double.parseDouble(d[5])); 
                            break;
                        case "BUS": 
                            // Recreates Bus using original ID and parsed integer/double values
                            trans[count++] = new Bus(d[1], d[2], d[3], d[4], d[2], Integer.parseInt(d[6]), Double.parseDouble(d[5])); 
                            break;
                        default:
                            throw new Exception("Unknown transport type: " + d[0]);
                    }
                } catch (Exception e) {
                    // Log the malformed line to the error file without stopping the load process
                    ErrorLogger.log("Transport load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        
        // Return the total number of transportation objects successfully loaded
        return count; 
    }
}