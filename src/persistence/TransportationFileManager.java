package persistence;

import travel.*;
import java.io.*;

public class TransportationFileManager {

   
    public static void saveTransports(Transportation[] trans, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            
        	for (int i = 0; i < count; i++) {
        		
                Transportation t = trans[i];               
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


    public static int loadTransports(Transportation[] trans, String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	if (line.trim().isEmpty()) continue;
               
                try {
                    String[] d = line.split(";");                                 
                    switch (d[0].toUpperCase()) {
                    case "FLIGHT":                        
                        trans[count++] = new Flight(d[1], d[2], d[3], d[4], d[2], Double.parseDouble(d[6]), Double.parseDouble(d[5])); 
                        break;
                    case "TRAIN": 
                        trans[count++] = new Train(d[1], d[2], d[3], d[4], d[6], "Standard", Double.parseDouble(d[5])); 
                        break;
                    case "BUS": 
                        trans[count++] = new Bus(d[1], d[2], d[3], d[4], d[2], Integer.parseInt(d[6]), Double.parseDouble(d[5])); 
                        break;
                    default:
                        throw new Exception("Unknown transport type: " + d[0]);
                }
                } catch (Exception e) {
                    ErrorLogger.log("Transport load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        return count; 
    }
}