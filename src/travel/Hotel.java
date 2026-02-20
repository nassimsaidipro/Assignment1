package travel;

public class Hotel extends Accommodation {

	private int starRating;
	
	public Hotel() {
	    super();
	    starRating = 0;
	}

	public Hotel(String name, String location, double pricePerNight, int starRating) {
	    super(name, location, pricePerNight);
	    this.starRating = starRating;
	}

	public Hotel(Hotel other) {
	    super(other);
	    this.starRating = other.starRating;
	}
	
	@Override
	public double calculateCost(int numberOfDays) {
		//Higher the rating, higher the price
		double additionalCost = (starRating/10) + 1.0;
		return (numberOfDays*pricePerNight)*additionalCost;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    
	    Hotel other = (Hotel) obj;
	    return name.equals(other.name) && 
	           location.equals(other.location) && 
	           pricePerNight == other.pricePerNight &&
	           starRating == other.starRating;
	}

	@Override
	public String toString() {
	    return "Hotel ID: " + getAccommodationId() + "\n" +
	           "Name: " + name + "\n" +
	           "Location: " + location + "\n" +
	           "Price Per Night: $" + pricePerNight + "\n" +
	           "Star Rating: " + starRating;
	}
	
	public int getStarRating() {
	    return starRating;
	}

	public void setStarRating(int starRating) {
	    this.starRating = starRating;
	}
}
