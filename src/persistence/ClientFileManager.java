// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package persistence;

import client.Client;
import java.io.*;

// This class provides the persistence logic for Client objects, allowing the 
// system to save client data to a semicolon-separated text file and reload it
// into the program's arrays. It uses the custom "backdoor" constructor to 
// preserve original Client IDs and logs any malformed data rows via the ErrorLogger.
public class ClientFileManager {

	// Writes the array of current clients to a specified text file
	public static void saveClients(Client[] clients, int count, String filePath) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (int i = 0; i < count; i++) {
				Client c = clients[i];
				// Saves: ID;First;Last;Email;AmountSpent
				writer.println(c.getClientId() + ";" + c.getFirstName() + ";" + c.getLastName() + ";"
						+ c.getEmailAdress() + ";" + c.getTotalSpent());
			}
		}
	}

	// Reads client data and reconstructs objects while tracking line numbers for errors
	public static int loadClients(Client[] clients, String filePath) throws IOException {
		int count = 0;
		int recordNumber = 0; // NEW: Tracks the exact line in the CSV file

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = reader.readLine()) != null) {
				recordNumber++; // Increment for every line read (including empty ones)
				
				if (line.trim().isEmpty())
					continue;

				try {
					String[] d = line.split(";");

					// Validate column count to match Error #1 in your example
					if (d.length < 4) {
						throw new Exception("Must have at least 4 columns (ID, First, Last, Email)");
					}

					// Recreate the Client using the backdoor constructor
					// Updated to pass 0.0 initially, then set the actual amount if it exists
					clients[count] = new Client(d[0], d[1], d[2], d[3]);

					if (d.length > 4) {
						clients[count].setAmountSpent(Double.parseDouble(d[4]));
					}
					count++;

				} catch (Exception e) {					
					ErrorLogger.log("Invalid Client Exception: Record number " + recordNumber + 
					                " in clients file. " + e.getMessage());
				}
			}
		}
		return count;
	}
}