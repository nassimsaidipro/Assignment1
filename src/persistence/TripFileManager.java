// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import travel.*;
import client.Client;
import java.io.*;

public class TripFileManager {

    // Saves Trip data including the IDs of linked objects
    public static void saveTrips(Trip[] trips, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                Trip t = trips[i];
                
                // Use "NONE" if an object isn't linked to avoid NullPointerExceptions
                String clientId = (t.getTravelingClient() != null) ? t.getTravelingClient().getClientId() : "NONE";
                String transId = (t.getTransportation() != null) ? t.getTransportation().getTransportId() : "NONE";
                String accomId = (t.getAccommodation() != null) ? t.getAccommodation().getAccommodationId() : "NONE";
                
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

    // Loads Trip data and performs manual ID linking with error reporting
    public static int loadTrips(Trip[] trips, String filePath, 
                                Client[] clients, int clientCount, 
                                Transportation[] transports, int transCount, 
                                Accommodation[] accommodations, int accomCount) throws IOException {
        int count = 0;
        int recordNumber = 0; // Tracks line number for ErrorLogger

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recordNumber++;
                if (line.trim().isEmpty()) continue; 

                try {
                    String[] d = line.split(";");
                    
                    // Validation: Ensure the Trip ID format is correct (Error #8 in example)
                    if (!d[0].startsWith("T")) {
                        throw new Exception("Invalid tripId: " + d[0]);
                    }

                    // 1. Link Client (Matches Error #7: Client not found)
                    Client c = findClient(d[4], clients, clientCount);
                    if (c == null && !d[4].equals("NONE")) {
                        throw new Exception("Entity not found: ID = Client not found: " + d[4]);
                    }
                    
                    // Create basic Trip object
                    Trip t = new Trip(d[0], d[1], Integer.parseInt(d[2]), Double.parseDouble(d[3]), c);
                    
                    // 2. Link Transportation (Matches Error #6: Transportation not found)
                    Transportation trans = findTransport(d[5], transports, transCount);
                    if (trans == null && !d[5].equals("NONE")) {
                        throw new Exception("Entity not found: ID = Transportation not found: " + d[5]);
                    }
                    if (trans != null) t.setTransportation(trans);
                    
                    // 3. Link Accommodation (Matches Error #9: Accommodation not found)
                    Accommodation accom = findAccommodation(d[6], accommodations, accomCount);
                    if (accom == null && !d[6].equals("NONE")) {
                        throw new Exception("Entity not found: ID = Accommodation not found: " + d[6]);
                    }
                    if (accom != null) t.setAccommodation(accom);
                    
                    trips[count++] = t;
                    
                } catch (Exception e) {
                    // Logs the error using the exact prefix required for Trips
                    ErrorLogger.log("Invalid Trip Exception: Record number " + recordNumber + 
                                    " in trips file. Trip Error: " + e.getMessage());
                }
            }
        }
        return count;
    }

    // --- Private Helper Methods for ID Searching ---

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