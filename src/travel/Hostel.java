package travel;

// Concrete subclass of Accommodation representing a hostel stay
public class Hostel extends Accommodation {

	private int sharedBedsPerRoom;

	// Default constructor - initializes sharedBedsPerRoom to 0
	// and calls the parent Accommodation default constructor
	public Hostel() {
		super();
		sharedBedsPerRoom = 0;
	}

	// Parameterized constructor - passes shared accommodation fields to the parent constructor
	// and initializes the Hostel-specific sharedBedsPerRoom field
	public Hostel(String name, String location, double pricePerNight, int sharedBedsPerRoom) {
		super(name, location, pricePerNight);
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies the sharedBedsPerRoom field from the other Hostel object
	public Hostel(Hostel other) {
		super(other);
		this.sharedBedsPerRoom = other.sharedBedsPerRoom;
	}

	// Calculates the total cost of the hostel stay for a given number of days
	// Applies a 25% discount if the room has more than 4 shared beds
	@Override
	public double calculateCost(int numberOfDays) {
		//If more than 4 shared beds 25% off
		double priceAdjustment = sharedBedsPerRoom > 4 ? 0.25 : 1;
		return (numberOfDays*pricePerNight)*priceAdjustment;

	}

	// Checks equality based on name, location, price per night, and shared beds per room
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Hostel other = (Hostel) obj;
		return name.equals(other.name) &&
				location.equals(other.location) &&
				pricePerNight == other.pricePerNight &&
				sharedBedsPerRoom == other.sharedBedsPerRoom;
	}

	// Returns a formatted string displaying all relevant Hostel details
	@Override
	public String toString() {
		return "Hostel ID: " + getAccommodationId() + "\n" +
				"Name: " + name + "\n" +
				"Location: " + location + "\n" +
				"Price Per Night: $" + pricePerNight + "\n" +
				"Shared Beds Per Room: " + sharedBedsPerRoom;
	}

	// Returns a deep copy of this Hostel object using the copy constructor
	@Override
	public Accommodation copy() {
	    return new Hostel(this);
	}

	// --- Getters and Setters ---

	public int getSharedBedsPerRoom() {
		return sharedBedsPerRoom;
	}

	public void setSharedBedsPerRoom(int sharedBedsPerRoom) {
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

}