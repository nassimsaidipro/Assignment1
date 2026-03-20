// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

import exceptions.InvalidAccommodationDataException;

//This class represents a hostel as a specific type of accommodation in the travel system.
//It extends Accommodation and adds a hostel-specific attribute: the number of shared beds per room.
//The cost calculation applies a 25% discount if the room has more than 4 shared beds,
//reflecting the lower comfort level of more crowded rooms.
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
	public Hostel(String name, String location, double pricePerNight, int sharedBedsPerRoom) throws InvalidAccommodationDataException {
		super(name, location, pricePerNight);		
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

	// Copy constructor - copies parent fields via super copy constructor
	// and copies the sharedBedsPerRoom field from the other Hostel object
	public Hostel(Hostel other) {
		super(other);
		this.sharedBedsPerRoom = other.sharedBedsPerRoom;
	}

	// Private constructor used EXCLUSIVELY by the copy() method.
	// Passes the exact ID and shared fields up to the parent's protected constructor,
	// and assigns the Hostel-specific shared beds, without triggering a numId increment.
	private Hostel(String accommodationId, String name, String location, double pricePerNight, int sharedBedsPerRoom) {
		super(accommodationId, name, location, pricePerNight); 
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

	// Returns a deep copy of this Hostel object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	@Override
	public Accommodation copy() {
		return new Hostel(this.getAccommodationId(), this.name, this.location, this.pricePerNight, this.sharedBedsPerRoom);
	}

	// Calculates the total cost of the hostel stay for a given number of days
	// Applies a 25% discount if the room has more than 4 shared beds
	@Override
	public double calculateCost(int numberOfDays) {
		//If more than 4 shared beds 25% off
		double priceAdjustment = sharedBedsPerRoom > 4 ? 0.75 : 1;
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

	// --- Getters and Setters ---

	public int getSharedBedsPerRoom() {
		return sharedBedsPerRoom;
	}

	public void setSharedBedsPerRoom(int sharedBedsPerRoom) {
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

}