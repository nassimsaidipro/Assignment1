package travel;

public class Flight extends Transportation {

    private String airlineName;
    private double luggageAllowance;
    private double flightPrice;

    public Flight() {
        super();
        airlineName = "";
        luggageAllowance = 0.0;
        flightPrice = 0.0;
    }

    public Flight(String companyName, String departureCity, String arrivalCity, String airlineName, double luggageAllowance, double flightPrice) {
        super(companyName, departureCity, arrivalCity);
        this.airlineName = airlineName;
        this.luggageAllowance = luggageAllowance;
        this.flightPrice = flightPrice;
    }

    public Flight(Flight other) {
        super(other);
        this.airlineName = other.airlineName;
        this.luggageAllowance = other.luggageAllowance;
        this.flightPrice = other.flightPrice;
    }

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

    @Override
    public String toString() {
        return "Flight ID: " + getTransportId() + "\n" +
                "Company: " + companyName + "\n" +
                "Departure: " + departureCity + "\n" +
                "Arrival: " + arrivalCity + "\n" +
                "Airline: " + airlineName + "\n" +
                "Luggage Allowance: " + luggageAllowance + " kg";
    }
    
    @Override
   	public double calculateCost() {		
   		return flightPrice;
   	}

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
