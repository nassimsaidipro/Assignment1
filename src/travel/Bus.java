package travel;

// Concrete subclass of Transportation representing a bus travel option
public class Bus extends Transportation {

	private String busCompany;
	private int numberOfStops;
	private double busFare;

	// Default constructor - initializes all Bus-specific fields to default values
	// and calls the parent Transportation default constructor
	public Bus() {
		super();
		busCompany = "";
		numberOfStops = 0;
		busFare = 0.0;
	}

	// Parameterized constructor - passes shared transportation fields to the parent constructor
	// and initializes Bus-specific fields with provided values
	public Bus(String companyName, String departureCity, String arrivalCity, String busCompany, int numberOfStops, double busFare) {
		super(companyName, departureCity, arrivalCity);
		this.busCompany = busCompany;
		this.numberOfStops = numberOfStops;
		this.busFare = busFare;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies Bus-specific fields from the other Bus object
	public Bus(Bus other) {
		super(other);
		this.busCompany = other.busCompany;
		this.numberOfStops = other.numberOfStops;
		this.busFare = other.busFare;
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
		return companyName.equals(other.companyName) &&
				departureCity.equals(other.departureCity) &&
				arrivalCity.equals(other.arrivalCity) &&
				busCompany.equals(other.busCompany) &&
				numberOfStops == other.numberOfStops;
	}

	// Returns a formatted string displaying all relevant Bus details
	@Override
	public String toString() {
		return "\nBus ID: " + getTransportId() + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity + "\n" +
				"Bus Company: " + busCompany + "\n" +
				"Number of Stops: " + numberOfStops;
	}

	// Returns the total cost of the bus trip, which is simply the flat bus fare
	@Override
	public double calculateCost() {
		return busFare;
	}

	// Returns a deep copy of this Bus object using the copy constructor
	@Override
	public Transportation copy() {
	    return new Bus(this);
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

	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

}