// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

import exceptions.InvalidTransportDataException;

//This class represents a bus as a specific type of transportation in the travel system.
//It extends Transportation and adds bus-specific details such as the bus company name,
//number of stops along the route, and the flat fare for the trip.
//The cost of a bus trip is simply the fixed bus fare with no additional calculations.
public class Bus extends Transportation {

	private String busCompany;
	private int numberOfStops;
	private double busFare;

	// Default constructor - initializes all Bus-specific fields to default values
	// and calls the parent Transportation default constructor
	public Bus() {
		super();
		busCompany = "";
		numberOfStops = 1;
		busFare = 0.0;
	}

	// Parameterized constructor - passes shared transportation fields to the parent
	// constructor
	// and initializes Bus-specific fields with provided values
	public Bus(String companyName, String departureCity, String arrivalCity, String busCompany, int numberOfStops,
			double busFare) throws InvalidTransportDataException {
		super(companyName, departureCity, arrivalCity);
		if (numberOfStops < 1) {
			throw new InvalidTransportDataException("Bus requires to have at least 1 stop");
		}
		this.busCompany = busCompany;
		this.numberOfStops = numberOfStops;
		this.busFare = busFare;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies Bus-specific fields from the other Bus object
	public Bus(Bus other) {
		super(other);

		// if (numberOfStops < 1) {
		// throw new InvalidTransportDataException("Bus requires to have at least 1
		// stop");
		// }

		this.busCompany = other.busCompany;
		this.numberOfStops = other.numberOfStops;
		this.busFare = other.busFare;
	}

	// Public constructor used by the copy() method and file managers.
	// Passes the exact ID and shared fields up to the parent's protected
	// constructor,
	// and assigns the Bus-specific variables, without triggering a numId increment.
	public Bus(String transportId, String companyName, String departureCity, String arrivalCity, String busCompany,
			int numberOfStops, double busFare) {
		super(transportId, companyName, departureCity, arrivalCity);
		this.busCompany = busCompany;
		this.numberOfStops = numberOfStops;
		this.busFare = busFare;
	}

	// Returns a deep copy of this Bus object.
	// It relies on the private backdoor constructor to ensure the exact ID is
	// retained
	// without accidentally inflating the static numId sequence.
	@Override
	public Transportation copy() {
		return new Bus(this.getTransportId(), this.companyName, this.departureCity, this.arrivalCity, this.busCompany,
				this.numberOfStops, this.busFare);
	}

	// Checks equality based on company name, departure/arrival cities,
	// bus company, and number of stops (excludes busFare)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Bus other = (Bus) obj;
		return companyName.equals(other.companyName) && departureCity.equals(other.departureCity)
				&& arrivalCity.equals(other.arrivalCity) && busCompany.equals(other.busCompany)
				&& numberOfStops == other.numberOfStops;
	}

	// Returns a formatted string displaying all relevant Bus details
	@Override
	public String toString() {
		return "\nBus ID: " + getTransportId() + "\n" + "Company: " + companyName + "\n" + "Departure: " + departureCity
				+ "\n" + "Arrival: " + arrivalCity + "\n" + "Bus Company: " + busCompany + "\n" + "Number of Stops: "
				+ numberOfStops + "\n" + "Bus Fare: " + busFare;
	}

	// Returns the total cost of the bus trip, which is simply the flat bus fare
	@Override
	public double calculateCost() {
		return busFare;
	}

	// --- Getters and Setters ---

	public String getBusCompany() {
		return busCompany;
	}

	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}

	public int getNumberOfStops() {
		return numberOfStops;
	}

	public void setNumberOfStops(int numberOfStops) throws InvalidTransportDataException {
		if (numberOfStops < 1) {
			throw new InvalidTransportDataException("Bus requires to have at least 1 stop");
		}
		this.numberOfStops = numberOfStops;
	}

}