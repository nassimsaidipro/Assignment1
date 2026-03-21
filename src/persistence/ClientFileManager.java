package persistence;

import client.Client;
import java.io.*;

public class ClientFileManager {

    public static void saveClients(Client[] clients, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        	
            for (int i = 0; i < count; i++) {
                Client c = clients[i];
                writer.println(c.getClientId() + ";" + c.getFirstName() + ";" + c.getLastName() + ";" + c.getEmailAdress());
                               
            }
        }
    }

    public static int loadClients(Client[] clients, String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    String[] d = line.split(";");
                    clients[count++] = new Client(d[0], d[1], d[2], d[3]);
                    
                } catch (Exception e) {
                    ErrorLogger.log("Client load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        return count;
    }
}