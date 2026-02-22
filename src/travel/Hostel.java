package travel;

public class Hostel extends Accommodation {

	private int sharedBedsPerRoom;

	public Hostel() {
		super();
		sharedBedsPerRoom = 0;
	}

	public Hostel(String name, String location, double pricePerNight, int sharedBedsPerRoom) {
		super(name, location, pricePerNight);
		this.sharedBedsPerRoom = sharedBedsPerRoom;
	}

	public Hostel(Hostel other) {
		super(other);
		this.sharedBedsPerRoom = other.sharedBedsPerRoom;
	}

	@Override
	public double calculateCost(int numberOfDays) {
		//If more than 4 shared beds 25% off
		double priceAdjustment = sharedBedsPerRoom > 4 ? 0.25 : 1;
		return (numberOfDays*pricePerNight)*priceAdjustment;
		
	}

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

	@Override
	public String toString() {
		return "Hostel ID: " + getAccommodationId() + "\n" +
				"Name: " + name + "\n" +
				"Location: " + location + "\n" +
				"Price Per Night: $" + pricePerNight + "\n" +
				"Shared Beds Per Room: " + sharedBedsPerRoom;
	}

	@Override
	public Accommodation copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
