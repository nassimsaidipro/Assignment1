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

// -----------------------------------------------------------------------
// Assignment 3
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

/*
 * This utility class provides a centralized, generic system for managing file I/O operations.
 * It uses Java Generics and Reflection to load and save various travel system objects 
 * (Clients, Trips, etc.) to and from CSV files. It ensures data integrity through 
 * centralized error logging and supports complex object linking for Trip records.
 */

public class GenericFileManager<T extends CsvPersistable> {

	/**
	 * Generic method to load data from a CSV file into a List of objects.
	 * Uses clazz.getSimpleName() to route data to the correct factory method.
	 */
	public static <T extends CsvPersistable> List<T> load(String filepath, Class<T> clazz) {           
		List<T> items = new ArrayList<T>();
		String className = clazz.getSimpleName();

		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

			String line;
			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty()) continue; 

				T item = null;
				try {
					// Identify class type and manufacture the specific object
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

	/**
	 * Specialized load method for Trips to handle object linking.
	 * Passes existing data lists to resolve ID references during parsing.
	 */
	public static List<Trip> load(String filepath,List<Client> clients, List<Accommodation> accommodations, List<Transportation> transportations){
		List<Trip> items = new ArrayList<Trip>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {

			String line;
			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty()) continue; 

				try {
					// Instantiate trip and link to real objects immediately
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

	/**
	 * Generic method to save any List of CsvPersistable objects to a CSV file.
	 */
	public static <T extends CsvPersistable> void save(List<T> items, String filepath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
			for (T i: items) {
				// Write each object's CSV representation to a new line
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