package travel;

public class Bus extends Transportation {

	private String busCompany;
	private int numberOfStops;
	private double busFare;

	public Bus() {
		super();
		busCompany = "";
		numberOfStops = 0;
		busFare = 0.0;
	}

	public Bus(String companyName, String departureCity, String arrivalCity, String busCompany, int numberOfStops, double busFare) {
		super(companyName, departureCity, arrivalCity);
		this.busCompany = busCompany;
		this.numberOfStops = numberOfStops;
		this.busFare = busFare;
	}

	public Bus(Bus other) {
		super(other);
		this.busCompany = other.busCompany;
		this.numberOfStops = other.numberOfStops;
		this.busFare = other.busFare;
	}

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

	@Override
	public String toString() {
		return "\nBus ID: " + getTransportId() + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity + "\n" +
				"Bus Company: " + busCompany + "\n" +
				"Number of Stops: " + numberOfStops;
	} 

	@Override
	public double calculateCost() {		
		return busFare;
	}

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
