// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

//This class represents a flight as a specific type of transportation in the travel system.
//It extends Transportation and adds flight-specific details such as the airline name,
//luggage allowance in kilograms, and the flat flight price.
//The cost of a flight is simply the fixed flight price with no additional calculations.
public class Flight extends Transportation {

	private String airlineName;
	private double luggageAllowance;
	private double flightPrice;

	// Default constructor - initializes all Flight-specific fields to default values
	// and calls the parent Transportation default constructor
	public Flight() {
		super();
		airlineName = "";
		luggageAllowance = 0.0;
		flightPrice = 0.0;
	}

	// Parameterized constructor - passes shared transportation fields to the parent constructor
	// and initializes Flight-specific fields with provided values
	public Flight(String companyName, String departureCity, String arrivalCity, String airlineName, double luggageAllowance, double flightPrice) {
		super(companyName, departureCity, arrivalCity);
		this.airlineName = airlineName;
		this.luggageAllowance = luggageAllowance;
		this.flightPrice = flightPrice;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies Flight-specific fields from the other Flight object
	public Flight(Flight other) {
		super(other);
		this.airlineName = other.airlineName;
		this.luggageAllowance = other.luggageAllowance;
		this.flightPrice = other.flightPrice;
	}

	// Private constructor used EXCLUSIVELY by the copy() method.
	// Passes the exact ID and shared fields up to the parent's protected constructor,
	// and assigns the Flight-specific variables, without triggering a numId increment.
	private Flight(String transportId, String companyName, String departureCity, String arrivalCity, String airlineName, double luggageAllowance, double flightPrice) {
		super(transportId, companyName, departureCity, arrivalCity);
		this.airlineName = airlineName;
		this.luggageAllowance = luggageAllowance;
		this.flightPrice = flightPrice;
	}

	// Returns a deep copy of this Flight object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	@Override
	public Transportation copy() {
		return new Flight(this.getTransportId(), this.companyName, this.departureCity, this.arrivalCity, this.airlineName, this.luggageAllowance, this.flightPrice);
	}

	// Checks equality based on company name, departure/arrival cities,
	// airline name, and luggage allowance (excludes flightPrice)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Flight other = (Flight) obj;
		return companyName.equals(other.companyName) &&
				departureCity.equals(other.departureCity) &&
				arrivalCity.equals(other.arrivalCity) &&
				airlineName.equals(other.airlineName) &&
				luggageAllowance == other.luggageAllowance;
	}

	// Returns a formatted string displaying all relevant Flight details
	@Override
	public String toString() {
		return "\nFlight ID: " + getTransportId() + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity + "\n" +
				"Airline: " + airlineName + "\n" +
				"Luggage Allowance: " + luggageAllowance + " kg" + "\n" +
				"Flight price: " + flightPrice;
	}

	// Returns the total cost of the flight, which is simply the flat flight price
	@Override
	public double calculateCost() {
		return flightPrice;
	}

	// --- Getters and Setters ---

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public double getLuggageAllowance() {
		return luggageAllowance;
	}

	public void setLuggageAllowance(double luggageAllowance) {
		this.luggageAllowance = luggageAllowance;
	}


}