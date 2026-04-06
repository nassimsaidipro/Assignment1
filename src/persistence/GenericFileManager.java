package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.Client;
import interfaces.CsvPersistable;
import travel.Accommodation;
import travel.Transportation;
import travel.Trip;

public class GenericFileManager<T extends CsvPersistable> {

	public static <T extends CsvPersistable> List<T> load(String filepath, Class<T> clazz) {           
		List<T> items = new ArrayList<T>();
		String className = clazz.getSimpleName();

		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

			String line;
			while ((line = reader.readLine()) != null) {

				T item = null;
				try {
					switch(className) {
					case "Client": 
						item = (T) Client.fromCsvRow(line);
						break;
					case "Accommodation":
						item = (T) Accommodation.fromCsvRow(line);
						break;
					case "Transportation":					
						item = (T) Transportation.fromCsvRow(line);
						break;
					default:
						throw new IllegalArgumentException("Unsupported class: " + clazz.getSimpleName());
					}
					if (item != null) {
						items.add(item);
					}
				} catch (Exception e) {
					ErrorLogger.log("Error parsing line in " + filepath + " — " + e.getMessage());
				}
			}

		} catch (FileNotFoundException e) {
			ErrorLogger.log("File not found: " + filepath);
		} catch (IOException e) {
			ErrorLogger.log("IO error reading file: " + filepath + " — " + e.getMessage());
		}

		return items;	
	}

	public static List<Trip> load(String filepath,List<Client> clients, List<Accommodation> accommodations, List<Transportation> transportations){
		List<Trip> items = new ArrayList<Trip>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

			String line;
			while ((line = reader.readLine()) != null) {
				try {
					Trip trip = Trip.fromCsvRow(line, clients, accommodations, transportations);
					items.add(trip);
				} catch (Exception e) {
					ErrorLogger.log("Error parsing line in " + filepath + " — " + e.getMessage());
				} 			
			}

		} catch (FileNotFoundException e) {
			ErrorLogger.log("File not found: " + filepath);
		} catch (IOException e) {
			ErrorLogger.log("IO error reading file: " + filepath + " — " + e.getMessage());
		}
		return items;	
	}

	public static <T extends CsvPersistable> void save(List<T> items, String filepath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
			for (T i: items) {
				writer.write(i.toCsvRow());
				writer.newLine();
			}
		} catch (FileNotFoundException e) {
			ErrorLogger.log("File not found: " + filepath);
		} catch (IOException e) {
			ErrorLogger.log("IO error reading file: " + filepath + " — " + e.getMessage());
		}

	}
}
