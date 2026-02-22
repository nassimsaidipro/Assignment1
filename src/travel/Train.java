// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

//This class represents a train as a specific type of transportation in the travel system.
//It extends Transportation and adds train-specific details such as the train type,
//seat class, and the flat fare for the trip.
//The cost of a train trip is simply the fixed train fare with no additional calculations.
public class Train extends Transportation {

	private String trainType;
	private String seatClass;
	private double trainFare;

	// Default constructor - initializes all Train-specific fields to default values
	// and calls the parent Transportation default constructor
	public Train() {
		super();
		trainType = "";
		seatClass = "";
		trainFare = 0.0;
	}

	// Parameterized constructor - passes shared transportation fields to the parent constructor
	// and initializes Train-specific fields with provided values
	public Train(String companyName, String departureCity, String arrivalCity, String trainType, String seatClass, double priceFare) {
		super(companyName, departureCity, arrivalCity);
		this.trainType = trainType;
		this.seatClass = seatClass;
		this.trainFare = priceFare;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies Train-specific fields from the other Train object
	public Train(Train other) {
		super(other);
		this.trainType = other.trainType;
		this.seatClass = other.seatClass;
		this.trainFare = other.trainFare;
	}

	// Private constructor used EXCLUSIVELY by the copy() method.
	// Passes the exact ID and shared fields up to the parent's protected constructor,
	// and assigns the Train-specific variables, without triggering a numId increment.
	private Train(String transportId, String companyName, String departureCity, String arrivalCity, String trainType, String seatClass) {
		super(transportId, companyName, departureCity, arrivalCity);
		this.trainType = trainType;
		this.seatClass = seatClass;
	}

	// Returns a deep copy of this Train object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	@Override
	public Transportation copy() {
		return new Train(this.getTransportId(), this.companyName, this.departureCity, this.arrivalCity, this.trainType, this.seatClass);
	}

	// Checks equality based on company name, departure/arrival cities,
	// train type, and seat class (excludes trainFare)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Train other = (Train) obj;
		return companyName.equals(other.companyName) &&
				departureCity.equals(other.departureCity) &&
				arrivalCity.equals(other.arrivalCity) &&
				trainType.equals(other.trainType) &&
				seatClass.equals(other.seatClass);
	}

	// Returns a formatted string displaying all relevant Train details
	@Override
	public String toString() {
		return "\nTrain ID: " + getTransportId() + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity + "\n" +
				"Train Type: " + trainType + "\n" +
				"Seat Class: " + seatClass;
	}

	// Returns the total cost of the train trip, which is simply the flat train fare
	@Override
	public double calculateCost() {
		return trainFare;
	}

	// --- Getters and Setters ---

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

}