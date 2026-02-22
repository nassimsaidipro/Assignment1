// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

import client.Client;

// Class representing a complete travel trip
// A trip links a client with a destination, transportation, and accommodation
public class Trip {

	// Static counter to generate unique trip IDs starting from 2001
	private static int numId = 2001;
	private String tripId;
	private String destination;
	private int durationInDays;
	private double basePrice;
	private Client travelingClient;
	private Transportation transportation;
	private Accommodation accomodation;

	// Default constructor - initializes all fields to default values,
	// creates a default Client, sets transportation and accommodation to null,
	// and assigns a unique trip ID using the static counter
	public Trip() {
		destination = "";
		durationInDays = 0;
		basePrice = 0.0;
		travelingClient = new Client();
		transportation = null;
		accomodation = null;
		tripId = "T" + numId;
		numId++;
	}

	// Parameterized constructor - initializes trip details with provided values
	// Performs a defensive copy of the client if not null, otherwise creates a default Client
	// Sets transportation and accommodation to null, and assigns a unique trip ID
	public Trip(String destination, int duration, double basePrice, Client travelingClient) {
		this.destination = destination;
		this.durationInDays = duration;
		this.basePrice = basePrice;

		if (travelingClient != null) {
	        this.travelingClient = new Client(travelingClient);
	    } else {
	        this.travelingClient = new Client();
	    }

		this.transportation = null;
		this.accomodation = null;
		tripId = "T" + numId;
		numId++;
	}

	// Copy constructor - performs deep copies of all fields from another Trip object
	// Defensively copies the client, transportation, and accommodation if they are not null
	// Assigns a new unique trip ID using the static counter
	public Trip(Trip other) {
		destination = other.destination;
		durationInDays = other.durationInDays;
		basePrice = other.basePrice;

		// Deep copy the client if present, otherwise create a default Client
		if (other.travelingClient != null) {
	        this.travelingClient = new Client(other.travelingClient);
	    } else {
	        this.travelingClient = new Client();
	    }

		// Deep copy transportation if present, otherwise set to null
		if (other.transportation != null) {
	        this.transportation = other.transportation.copy();
	    } else {
	        this.transportation = null;
	    }

		// Deep copy accommodation if present, otherwise set to null
	    if (other.accomodation != null) {
	        this.accomodation = other.accomodation.copy();
	    } else {
	        this.accomodation = null;
	    }
	    
		tripId = "T" + numId;
		numId++;
	}
	
	    // Checks equality based on destination, duration, base price, client, transportation, and accommodation.
		// The unique tripId is excluded from this comparison since every trip object gets a different ID.
		@Override
		public boolean equals(Object obj) {
			
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;

			Trip other = (Trip) obj;

			// Check primitive fields and destination string
			if (this.durationInDays != other.durationInDays || 
				this.basePrice != other.basePrice || 
				!this.destination.equals(other.destination)) {
				return false;
			}

			// Safely check objects that might potentially be null
			boolean clientEquals = (this.travelingClient == null && other.travelingClient == null) || 
								   (this.travelingClient != null && this.travelingClient.equals(other.travelingClient));
								   
			boolean transportEquals = (this.transportation == null && other.transportation == null) || 
									  (this.transportation != null && this.transportation.equals(other.transportation));
									  
			boolean accommodationEquals = (this.accomodation == null && other.accomodation == null) || 
										  (this.accomodation != null && this.accomodation.equals(other.accomodation));

			return clientEquals && transportEquals && accommodationEquals;
		}

		// Returns a formatted string displaying all Trip details, cascading down to print
		// the details of the Client, Transportation, and Accommodation if they exist.
		@Override
		public String toString() {
			String clientInfo = (travelingClient != null) ? travelingClient.toString() : "No client assigned.";
			String transportInfo = (transportation != null) ? transportation.toString() : "No transportation booked.";
			String accommodationInfo = (accomodation != null) ? accomodation.toString() : "No accommodation booked.";

			return "Trip ID: " + tripId + "\n" +
				   "Destination: " + destination + "\n" +
				   "Duration: " + durationInDays + " days\n" +
				   "Base Price: $" + basePrice + "\n" +
				   "--- Client Details ---\n" + 
				   clientInfo + "\n" +
				   "--- Transportation Details ---\n" + 
				   transportInfo + "\n" +
				   "--- Accommodation Details ---\n" + 
				   accommodationInfo;
		}

	// Calculates the total cost of the trip by summing the base price,
	// accommodation cost (based on duration), and transportation cost
	public double calculateTotalCost() {
		return basePrice + accomodation.calculateCost(durationInDays) + transportation.calculateCost();
	}

	// --- Getters and Setters ---

	public String getTripId() {
		return tripId;
	}

	public String getDestination() {
		return destination;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	// Returns a deep copy of the transportation, or null if not set
	public Transportation getTransportation() {
	    if (this.transportation == null) {
	        return null;
	    }
	    return this.transportation.copy();
	}

	// Sets transportation using a deep copy to protect encapsulation, or null if not provided
	public void setTransportation(Transportation transportation) {
	    if (transportation == null) {
	        this.transportation = null;
	    } else {
	        this.transportation = transportation.copy();
	    }
	}

	// Returns a deep copy of the accommodation, or null if not set
	public Accommodation getAccommodation() {
	    if (this.accomodation == null) {
	        return null;
	    }
	    return this.accomodation.copy();
	}

	// Sets accommodation using a deep copy to protect encapsulation, or null if not provided
	public void setAccommodation(Accommodation accommodation) {
	    if (accommodation == null) {
	        this.accomodation = null;
	    } else {
	        this.accomodation = accommodation.copy();
	    }
	}

	// Returns a deep copy of the traveling client, or null if not set
	public Client getTravelingClient() {
	    if (this.travelingClient == null) {
	        return null;
	    }
	    return new Client(this.travelingClient);
	}

	// Sets the traveling client using a deep copy to protect encapsulation, or null if not provided
	public void setTravelingClient(Client travelingClient) {
	    if (travelingClient == null) {
	        this.travelingClient = null;
	    } else {
	        this.travelingClient = new Client(travelingClient);
	    }
	}

}