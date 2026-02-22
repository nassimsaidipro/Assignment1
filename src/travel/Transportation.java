package travel;

// Abstract base class representing a general transportation option
// Subclasses must implement calculateCost() and copy()
public abstract class Transportation {

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
		transportId = "TR" + numId;
		numId++;
	}

	// Parameterized constructor - initializes fields with provided values
	// and assigns a unique transport ID using the static counter
	public Transportation(String companyName, String departureCity, String arrivalCity) {
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
		transportId = "TR" + numId;
		numId++;
	}

	// Copy constructor - copies fields from another Transportation object
	// and assigns a new unique transport ID using the static counter
	public Transportation(Transportation other) {
		this.companyName = other.companyName;
		this.departureCity = other.departureCity;
		this.arrivalCity = other.arrivalCity;
		transportId = "TR" + numId;
		numId++;
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

	// Abstract method - subclasses must define how to return a deep copy of themselves
	public abstract Transportation copy();

	// --- Getters and Setters ---

	public String getTransportId() {
		return transportId;
	}

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

}