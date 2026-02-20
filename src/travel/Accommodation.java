package travel;

public abstract class Accommodation {

	private static int numId = 4001;
	private String accommodationId;
	protected String name;
	protected String location;
	protected double pricePerNight;

	public Accommodation() {
		name = "";
		location = "";
		pricePerNight = 0.0;
		accommodationId = "A" + accommodationId;
		numId++;
	}

	public Accommodation(String name, String location, double pricePerNight) {
		this.name = name;
		this.location = location;
		this.pricePerNight = pricePerNight;
		accommodationId = "A" + accommodationId;
		numId++;
	}

	public Accommodation(Accommodation other) {
		name = other.name;
		location = other.location;
		pricePerNight = other.pricePerNight;
		accommodationId = "A" + accommodationId;
		numId++;
	}
	
	public abstract double calculateCost(int numberOfDays);
	
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

    @Override
    public String toString() {
        return "Accommodation ID: " + accommodationId + "\n" +
                "Name: " + name + "\n" +
                "Location: " + location + "\n" +
                "Price Per Night: $" + pricePerNight;
    }

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
