// -----------------------------------------------------------------------
// Assignment 2
// Written by: Darwinsh Saint-Jean (40341644) & Nassim Saidi (40345885)
// -----------------------------------------------------------------------

package travel;

import exceptions.InvalidTransportDataException;
import interfaces.CsvPersistable;
import interfaces.Identifiable;

//This class serves as the abstract blueprint for all transportation types in the travel system.
//It stores common attributes shared by all transportation options such as company name,
//departure city, and arrival city.
//It auto-generates a unique ID for each transportation instance using a static counter.
//Subclasses (e.g. Bus, Flight, Train) must implement calculateCost() and copy() to define
//their own pricing logic and duplication behavior.
public abstract class Transportation implements Identifiable, CsvPersistable, Comparable<Transportation> {

	// Static counter to generate unique IDs starting from 3001
	private static int numId = 3001;
	private String transportId;
	protected String companyName;
	protected String departureCity;
	protected String arrivalCity;

	// Default constructor - initializes all fields to default values
	// and assigns a unique transport ID using the static counter
	public Transportation() {
		companyName = "";
		departureCity = "";
		arrivalCity = "";
		transportIdGenerator();
	}

	// Parameterized constructor - initializes fields with provided values
	// and assigns a unique transport ID using the static counter
	public Transportation(String companyName, String departureCity, String arrivalCity) {
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		transportIdGenerator();
	}

	// Copy constructor - copies fields from another Transportation object
	// and assigns a new unique transport ID using the static counter
	public Transportation(Transportation other) {
		this.companyName = other.companyName;
		this.departureCity = other.departureCity;
		this.arrivalCity = other.arrivalCity;
		transportIdGenerator();
	}

	// It manually assigns the exact transportId and shared fields.
	// This deliberately bypasses the static counter so numId does not falsely increment.
	protected Transportation(String transportId, String companyName, String departureCity, String arrivalCity) {
		this.transportId = transportId;
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
	}

	// Temporary shell constructor for linking objects from CSV
	public Transportation(String transportId) {
		this.transportId = transportId; 
		this.companyName = "Unknown Company";
		this.departureCity = "Unknown Departure City";
		this.arrivalCity = "Unknown Arrival City";	
	}

	// Abstract method - subclasses must define how to calculate the total transportation cost
	public abstract double calculateCost();

	// Checks equality based on company name, departure city, and arrival city (excludes ID)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Transportation other = (Transportation) obj;
		return companyName.equals(other.companyName) &&
				departureCity.equals(other.departureCity) &&
				arrivalCity.equals(other.arrivalCity);
	}

	// Returns a formatted string displaying all relevant Transportation details
	@Override
	public String toString() {
		return "\nTransport ID: " + transportId + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity;
	}

	// Generates Transportation ID and increments ID number for next transportation
	private void transportIdGenerator() {
		transportId = "TR" + numId;
		numId++;
	}

	// Abstract method - subclasses must define how to return a deep copy of themselves
	public abstract Transportation copy();

	// --- Getters and Setters ---

	public String getCompanyName() {
		return companyName;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	@Override
	public String getId() {
		return transportId;
	}

	@Override
	public int compareTo(Transportation o) {
		return Double.compare(o.calculateCost(), this.calculateCost());
	}

	@Override
	public String toCsvRow() {
		return getId() + ";" + companyName + ";" + departureCity + ";" + arrivalCity + ";" + this.calculateCost();
	}

	public static Transportation fromCsvRow(String csvLine) throws InvalidTransportDataException {
		String[] parts = csvLine.split(";", -1);

		if (parts.length != 7) { 
			throw new InvalidTransportDataException("Invalid CSV format: Transportation row must have exactly 7 fields.");
		}

		String type = parts[0]; 
		String id = parts[1];
		String companyName = parts[2];
		String departureCity = parts[3];
		String arrivalCity = parts[4];

		double fare;
		try {
			fare = Double.parseDouble(parts[5]);
		} catch (NumberFormatException e) {
			throw new InvalidTransportDataException("Invalid number format for price in CSV.");
		}

		if (type.equalsIgnoreCase("FLIGHT")) {
			double luggageWeight = Double.parseDouble(parts[6]); 			
			return new Flight(id, companyName, departureCity, arrivalCity, luggageWeight, fare); 

		} else if (type.equalsIgnoreCase("TRAIN")) {
			String trainType = parts[6]; 
			//Standard as seat class because not given in csv file.
			return new Train(id, companyName, departureCity, arrivalCity, trainType, "Standard", fare); 

		} else if (type.equalsIgnoreCase("BUS")) {
			int numberOfStops = Integer.parseInt(parts[6]);
			return new Bus(id, companyName, departureCity, arrivalCity, numberOfStops, fare); 

		} else {
			throw new InvalidTransportDataException("Unknown Transportation type in CSV: " + type);
		}
	}
}