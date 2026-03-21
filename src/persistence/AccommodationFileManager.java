package persistence;

import travel.*;
import java.io.*;

public class AccommodationFileManager {

    public static void saveAccommodations(Accommodation[] accs, int count, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < count; i++) {
                Accommodation a = accs[i];
                
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

    public static int loadAccommodations(Accommodation[] accs, String filePath) throws IOException {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                try {
                    String[] d = line.split(";");
                    
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
                    ErrorLogger.log("Accommodation load error on line: " + line + " | Error: " + e.getMessage());
                }
            }
        }
        return count;
    }
}