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

	public static int loadTransports(Transportation[] transports, String filePath) throws IOException {
		int count = 0;
		int recordNumber = 0; // Tracks the current line for the error report

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				recordNumber++;
				if (line.trim().isEmpty())
					continue;

				try {
					String[] d = line.split(";");
					// Assuming d[1] is the Type (FLIGHT, BUS, TRAIN)
					String type = d[1].toUpperCase();

					Transportation t;
					if (type.equals("FLIGHT")) {
						t = new Flight(d[0], d[2], d[3], d[4], d[5], Double.parseDouble(d[6]),
								Double.parseDouble(d[7]));
					} else if (type.equals("BUS")) {
						t = new Bus(d[0], d[2], d[3], d[4], d[5], Integer.parseInt(d[6]), Double.parseDouble(d[7]));
					} else if (type.equals("TRAIN")) {
						t = new Train(d[0], d[2], d[3], d[4], d[5], d[6], Double.parseDouble(d[7]));
					} else {

						throw new Exception("Transport Error: Unknown transport type: " + type);
					}

					transports[count++] = t;

				} catch (Exception e) {
					// Logs the specific line number and error message to the batch bucket
					ErrorLogger.log("Invalid Transportation Exception: Record number " + recordNumber
							+ " in transports file. " + e.getMessage());
				}
			}
		}
		return count;
	}

	public static void saveTransports(Transportation[] transports, int count, String filePath) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (int i = 0; i < count; i++) {
				Transportation t = transports[i];
				String type = (t instanceof Flight) ? "FLIGHT" : (t instanceof Bus) ? "BUS" : "TRAIN";

				// Save logic depends on your specific Transportation fields
				writer.println(t.getTransportId() + ";" + type + ";" + t.getCompanyName() + ";" + t.getDepartureCity()
						+ ";" + t.getArrivalCity());
			}
		}
	}
}
