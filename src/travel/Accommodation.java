// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------
package travel;

import exceptions.InvalidAccommodationDataException;
import interfaces.CsvPersistable;
import interfaces.Identifiable;

//This class serves as the abstract blueprint for all accommodation types in the travel system.
//It stores common attributes shared by all accommodations such as name, location, and price per night.
//It auto-generates a unique ID for each accommodation created using a static counter.
//Subclasses (e.g. Hotel, Hostel) must implement calculateCost() and copy() to define
//their own pricing logic and duplication behavior.
public abstract class Accommodation implements Identifiable, CsvPersistable, Comparable<Accommodation> {

	// Static counter to generate unique IDs starting from 4001
	private static int numId = 4001;
	private String accommodationId;
	protected String name;
	protected String location;
	protected double pricePerNight;

	// Default constructor - initializes fields to default values and assigns a unique accommodation ID
	public Accommodation() {
		name = "";
		location = "";
		pricePerNight = 1.0;
		accomIdGenerator();
	}

	// Parameterized constructor - initializes fields with provided values and assigns a unique accommodation ID
	public Accommodation(String name, String location, double pricePerNight) throws InvalidAccommodationDataException {

		if (pricePerNight <= 0) {
			throw new InvalidAccommodationDataException("Price per night needs to be greater than 0$");
		}

		this.name = name;
		this.location = location;
		this.pricePerNight = pricePerNight;
		accomIdGenerator();
	}

	// Copy constructor - initializes fields from another Accommodation object and assigns a new unique ID
	public Accommodation(Accommodation other) {
		name = other.name;
		location = other.location;
		pricePerNight = other.pricePerNight;
		accomIdGenerator();
	}

	// Protected constructor used EXCLUSIVELY by child classes during their copy() operations.
	// It manually assigns the exact accommodationId and shared fields.
	// This deliberately bypasses the static counter so numId does not falsely increment.
	protected Accommodation(String accommodationId, String name, String location, double pricePerNight) {
		this.accommodationId = accommodationId;
		this.name = name;
		this.location = location;
		this.pricePerNight = pricePerNight;
	}

	// Temporary shell constructor for linking objects from CSV
	public Accommodation(String accommodationId) {
		this.accommodationId = accommodationId; 
		this.name = "Unknown Accommodation Name"; 
		this.location = "Unknown Location";
		this.pricePerNight = 0.0;

		// CRITICAL: Do NOT call your automatic ID generator method here, 
		// just like we did with the Client class!
	}

	// Abstract method - subclasses must define how to calculate the total cost for a given number of days
	public abstract double calculateCost(int numberOfDays);

	// Checks equality based on name, location, and price per night (excludes ID)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Accommodation other = (Accommodation) obj;
		return name.equals(other.name) &&
				location.equals(other.location) &&
				pricePerNight == other.pricePerNight;
	}

	// Returns a formatted string representation of the accommodation's details
	@Override
	public String toString() {
		return "Accommodation ID: " + accommodationId + "\n" +
				"Name: " + name + "\n" +
				"Location: " + location + "\n" +
				"Price Per Night: $" + pricePerNight;
	}

	// Abstract method - subclasses must define how to return a deep copy of themselves
	public abstract Accommodation copy();

	// Generates Accommodation ID and increments ID number for next accommodation
	private void accomIdGenerator() {
		accommodationId = "A" + numId;
		numId++;	
	}

	// --- Getters and Setters ---

	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public double getPricePerNight() {
		return pricePerNight;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setPricePerNight(double pricePerNight) throws InvalidAccommodationDataException {

		if (pricePerNight <= 0) {
			throw new InvalidAccommodationDataException("Price per night needs to be greater than 0$");
		}

		this.pricePerNight = pricePerNight;
	}

	@Override
	public String getId() {
		return accommodationId;
	}

	@Override
	public int compareTo(Accommodation o) {
		return Double.compare(o.pricePerNight, this.pricePerNight);
	}

	@Override
	public String toCsvRow() {
		return getId() + ";" + name + ";" + location + ";" + pricePerNight;
	}
	
	public static Accommodation fromCsvRow(String csvLine) throws InvalidAccommodationDataException {
		String[] parts = csvLine.split(";", -1);

		if (parts.length != 6) { 
			throw new InvalidAccommodationDataException("Invalid CSV format: Accommodation row must have exactly 6 fields.");
		}

		String type = parts[0]; 
		String id = parts[1];
		String name = parts[2];
		String location = parts[3];
		
		double pricePerNight;
		try {
			pricePerNight = Double.parseDouble(parts[4]);
		} catch (NumberFormatException e) {
			throw new InvalidAccommodationDataException("Invalid number format for price in CSV.");
		}
		
		if (type.equalsIgnoreCase("HOTEL")) {
			int stars = Integer.parseInt(parts[5]); 
			return new Hotel(id, name, location, pricePerNight, stars); 
			
		} else if (type.equalsIgnoreCase("HOSTEL")) {
			int sharedBedsPerRoom = Integer.parseInt(parts[5]); 
			return new Hostel(id, name, location, pricePerNight, sharedBedsPerRoom); 
			
		} else {
			throw new InvalidAccommodationDataException("Unknown Accommodation type in CSV: " + type);
		}
	}

}