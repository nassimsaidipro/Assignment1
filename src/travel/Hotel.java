// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

//This class represents a hotel as a specific type of accommodation in the travel system.
//It extends Accommodation and adds a hotel-specific attribute: the star rating.
//The cost calculation applies a multiplier based on the star rating, meaning
//higher-rated hotels cost more per night, reflecting their premium service level.
public class Hotel extends Accommodation {

	private int starRating;

	// Default constructor - initializes starRating to 0
	// and calls the parent Accommodation default constructor
	public Hotel() {
		super();
		starRating = 0;
	}

	// Parameterized constructor - passes shared accommodation fields to the parent constructor
	// and initializes the Hotel-specific starRating field
	public Hotel(String name, String location, double pricePerNight, int starRating) {
		super(name, location, pricePerNight);
		this.starRating = starRating;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies the starRating field from the other Hotel object
	public Hotel(Hotel other) {
		super(other);
		this.starRating = other.starRating;
	}

	// Private constructor used EXCLUSIVELY by the copy() method.
	// Passes the exact ID and shared fields up to the parent's protected constructor,
	// and assigns the Hotel-specific star rating, without triggering a numId increment.
	private Hotel(String accommodationId, String name, String location, double pricePerNight, int starRating) {
		super(accommodationId, name, location, pricePerNight); 
		this.starRating = starRating;
	}

	// Returns a deep copy of this Hotel object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	@Override
	public Accommodation copy() {
		return new Hotel(this.getAccommodationId(), this.name, this.location, this.pricePerNight, this.starRating);
	}

	// Calculates the total cost of the hotel stay for a given number of days
	// A higher star rating increases the price via an additional cost multiplier
	@Override
	public double calculateCost(int numberOfDays) {
		//Higher the rating, higher the price
		double additionalCost = (starRating/10) + 1.0;
		return (numberOfDays*pricePerNight)*additionalCost;
	}

	// Checks equality based on name, location, price per night, and star rating
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

	// Returns a formatted string displaying all relevant Hotel details
	@Override
	public String toString() {
		return "Hotel ID: " + getAccommodationId() + "\n" +
				"Name: " + name + "\n" +
				"Location: " + location + "\n" +
				"Price Per Night: $" + pricePerNight + "\n" +
				"Star Rating: " + starRating;
	}

	// --- Getters and Setters ---

	public int getStarRating() {
		return starRating;
	}

	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
}