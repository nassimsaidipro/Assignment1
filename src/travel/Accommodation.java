// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

import exceptions.InvalidAccommodationDataException;

//This class serves as the abstract blueprint for all accommodation types in the travel system.
//It stores common attributes shared by all accommodations such as name, location, and price per night.
//It auto-generates a unique ID for each accommodation created using a static counter.
//Subclasses (e.g. Hotel, Hostel) must implement calculateCost() and copy() to define
//their own pricing logic and duplication behavior.
public abstract class Accommodation {

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
		pricePerNight = 0.0;
		accomIdGenerator();
	}

	// Parameterized constructor - initializes fields with provided values and assigns a unique accommodation ID
	public Accommodation(String name, String location, double pricePerNight) throws InvalidAccommodationDataException {

		if (pricePerNight > 0) {
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

	public String getAccommodationId() {
		return accommodationId;
	}
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
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

}