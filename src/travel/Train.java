package travel;

public class Train extends Transportation {

	private String trainType; 
	private String seatClass;
	private double trainFare;

	public Train() {
		super();
		trainType = "";
		seatClass = "";
		trainFare = 0.0;
	}

	public Train(String companyName, String departureCity, String arrivalCity, String trainType, String seatClass, double priceFare) {
		super(companyName, departureCity, arrivalCity);
		this.trainType = trainType;
		this.seatClass = seatClass;
		this.trainFare = priceFare;
	}

	public Train(Train other) {
		super(other);
		this.trainType = other.trainType;
		this.seatClass = other.seatClass;
		this.trainFare = other.trainFare;
	}

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

	@Override
	public String toString() {
		return "\nTrain ID: " + getTransportId() + "\n" +
				"Company: " + companyName + "\n" +
				"Departure: " + departureCity + "\n" +
				"Arrival: " + arrivalCity + "\n" +
				"Train Type: " + trainType + "\n" +
				"Seat Class: " + seatClass;
	}

	@Override
	public double calculateCost() {		
		return trainFare;
	}
	
	@Override
	public Transportation copy() {
	    return new Train(this);
	}

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
