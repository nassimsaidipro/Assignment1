package travel;

public abstract class Transportation {
	
	private static int numId = 3001;
    private String transportId;
    protected String companyName;
    protected String departureCity;
    protected String arrivalCity;   
    
    
    public Transportation() {
        companyName = "";
        departureCity = "";
        arrivalCity = "";
        transportId = "TR" + numId;
        numId++;
    }
    
    public Transportation(String companyName, String departureCity, String arrivalCity) { 
        this.companyName = companyName;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        transportId = "TR" + numId;
        numId++;
    }
    
    public Transportation(Transportation other) {
        this.companyName = other.companyName;
        this.departureCity = other.departureCity;
        this.arrivalCity = other.arrivalCity;
        transportId = "TR" + numId;
        numId++;
        
    }
    
    public abstract double calculateCost();
    
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

    @Override
    public String toString() {
        return "\nTransport ID: " + transportId + "\n" +
                "Company: " + companyName + "\n" +
                "Departure: " + departureCity + "\n" +
                "Arrival: " + arrivalCity;
    }
    
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
