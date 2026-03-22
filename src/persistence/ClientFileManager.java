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

		// Initialize a PrintWriter with a FileWriter for file output
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

			// Iterate through the client array up to the current count
			for (int i = 0; i < count; i++) {
				Client c = clients[i];
				// Format client data into a single string with semicolon delimiters
				writer.println(c.getClientId() + ";" + c.getFirstName() + ";" + c.getLastName() + ";"
						+ c.getEmailAdress() + ";" + c.getTotalSpent());

			}
		}
	}

	// Reads client data from a text file and reconstructs the Client objects
	public static int loadClients(Client[] clients, String filePath) throws IOException {
		int count = 0;

		// Initialize a BufferedReader to read the file line by line
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;

			while ((line = reader.readLine()) != null) {
				// Ignore empty lines to prevent parsing errors
				if (line.trim().isEmpty())
					continue;

				try {
					// Split the raw string into an array of data fields
					String[] d = line.split(";");

					// Call the public backdoor constructor to recreate the Client with its original
					// ID
					clients[count] = new Client(d[0], d[1], d[2], d[3]);

					if (d.length > 4) {
						clients[count].setAmountSpent(Double.parseDouble(d[4]));

					}
					count++;

				} catch (Exception e) {
					// Log the error to errors.txt and continue reading the next line
					ErrorLogger.log("Client load error on line: " + line + " | Error: " + e.getMessage());
				}
			}
		}

		// Return the final number of clients successfully loaded
		return count;
	}
}