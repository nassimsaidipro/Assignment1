package persistence;

import travel.*;
import client.Client;
import java.io.*;

public class TripFileManager {

    public static void saveTrips(Trip[] trips, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                Trip t = trips[i];
                
                // Safely grab the IDs. If the object is null (not booked yet), save "NONE"
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

    public static int loadTrips(Trip[] trips, String filePath, 
                                Client[] clients, int clientCount, 
                                Transportation[] transports, int transCount, 
                                Accommodation[] accommodations, int accomCount) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                try {
                    String[] d = line.split(";");
                
                    // 1. Find the exact Client object using the helper method below
                    Client c = findClient(d[4], clients, clientCount);
                    
                    // 2. Create the Trip using your backdoor constructor
                    Trip t = new Trip(d[0], d[1], Integer.parseInt(d[2]), Double.parseDouble(d[3]), c);
                    
                    // 3. Find and link Transportation (if it exists)
                    Transportation trans = findTransport(d[5], transports, transCount);
                    if (trans != null) {
                        t.setTransportation(trans);
                    }
                    
                    // 4. Find and link Accommodation (if it exists)
                    Accommodation accom = findAccommodation(d[6], accommodations, accomCount);
                    if (accom != null) {
                        t.setAccommodation(accom);
                    }
                    
                    // 5. Store the fully reconstructed Trip
                    trips[count++] = t;
                    
                } catch (Exception e) {
                    ErrorLogger.log("Trip load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        return count;
    }

    // PRIVATE HELPER METHODS
    // Loops through existing arrays to find matching IDs
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