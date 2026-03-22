// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import travel.*;
import client.Client;
import java.io.*;

// This class handles the persistence of Trip objects. Unlike simpler objects, 
// Trip serialization requires linking IDs for Clients, Transportation, and 
// Accommodations. During loading, the class uses helper methods to search 
// existing arrays and "re-link" these objects to the Trip, ensuring 
// relational integrity is maintained between sessions.
public class TripFileManager {

    // Saves the array of Trip objects to a text file using semicolon delimiters
    public static void saveTrips(Trip[] trips, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                Trip t = trips[i];
                
                // Safely extract IDs from linked objects; use "NONE" if the component isn't booked
                String clientId = (t.getTravelingClient() != null) ? t.getTravelingClient().getClientId() : "NONE";
                String transId = (t.getTransportation() != null) ? t.getTransportation().getTransportId() : "NONE";
                String accomId = (t.getAccommodation() != null) ? t.getAccommodation().getAccommodationId() : "NONE";
                
                // Print the formatted line representing a single trip
                writer.println(t.getTripId() + ";" + 
                               t.getDestination() + ";" + 
                               t.getDurationInDays() + ";" + 
                               t.getBasePrice() + ";" + 
                               clientId + ";" + 
                               transId + ";" + 
                               accomId);
            }
        }
    }

    // Reconstructs Trip objects from a file by searching associated object arrays for matches
    public static int loadTrips(Trip[] trips, String filePath, 
                                Client[] clients, int clientCount, 
                                Transportation[] transports, int transCount, 
                                Accommodation[] accommodations, int accomCount) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip blank lines
                if (line.trim().isEmpty()) continue; 

                try {
                    String[] d = line.split(";");
                
                    // 1. Locate the specific Client object in memory using the saved ID
                    Client c = findClient(d[4], clients, clientCount);
                    
                    // 2. Instantiate the Trip using the specialized constructor to preserve the ID
                    Trip t = new Trip(d[0], d[1], Integer.parseInt(d[2]), Double.parseDouble(d[3]), c);
                    
                    // 3. Search and re-link the Transportation object if an ID was saved
                    Transportation trans = findTransport(d[5], transports, transCount);
                    if (trans != null) {
                        t.setTransportation(trans);
                    }
                    
                    // 4. Search and re-link the Accommodation object if an ID was saved
                    Accommodation accom = findAccommodation(d[6], accommodations, accomCount);
                    if (accom != null) {
                        t.setAccommodation(accom);
                    }
                    
                    // 5. Add the fully reconstructed Trip to the array
                    trips[count++] = t;
                    
                } catch (Exception e) {
                    // Log errors for malformed lines to the error log file
                    ErrorLogger.log("Trip load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        return count;
    }

    // --- PRIVATE HELPER METHODS ---
    // These methods perform linear searches on existing arrays to find object references by ID.

    private static Client findClient(String id, Client[] clients, int count) {
        if (id.equals("NONE")) return null;
        for (int i = 0; i < count; i++) {
            if (clients[i].getClientId().equals(id)) return clients[i];
        }
        return null; 
    }

    private static Transportation findTransport(String id, Transportation[] transports, int count) {
        if (id.equals("NONE")) return null;
        for (int i = 0; i < count; i++) {
            if (transports[i].getTransportId().equals(id)) return transports[i];
        }
        return null;
    }

    private static Accommodation findAccommodation(String id, Accommodation[] accommodations, int count) {
        if (id.equals("NONE")) return null;
        for (int i = 0; i < count; i++) {
            if (accommodations[i].getAccommodationId().equals(id)) return accommodations[i];
        }
        return null;
    }
}