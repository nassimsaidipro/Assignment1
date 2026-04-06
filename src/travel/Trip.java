// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package travel;

import client.Client;
import exceptions.InvalidTripDataException;
import interfaces.Billable;
import interfaces.CsvPersistable;
import interfaces.Identifiable;

//This class represents a complete travel package in the travel system.
//It brings together a client, a destination, a transportation option, and an accommodation
//into a single bookable trip. It tracks the duration and base price of the trip,
//and can calculate the full total cost by combining all three cost components.
//Deep copies are used throughout to protect encapsulation of all linked objects.
public class Trip implements  Identifiable, Billable, CsvPersistable, Comparable<Trip>  {

	// Static counter to generate unique trip IDs starting from 2001
	private static int numId = 2001;
	private String tripId;
	private String destination;
	private int durationInDays;
	private double basePrice;
	private Client travelingClient;
	private Transportation transportation;
	private Accommodation accommodation;

	// Default constructor - initializes all fields to default values,
	// creates a default Client, sets transportation and accommodation to null,
	// and assigns a unique trip ID using the static counter
	public Trip() {
		this.destination = "Unknown";
		this.durationInDays = 1;      
		this.basePrice = 100.0;       
		this.travelingClient = new Client(); 
		this.transportation = null;
		this.accommodation = null;
		tripIdGenerator();
	}

	// Parameterized constructor - initializes trip details with provided values
	// Performs a defensive copy of the client if not null, otherwise creates a default Client
	// Sets transportation and accommodation to null, and assigns a unique trip ID
	public Trip(String destination, int duration, double basePrice, Client travelingClient) throws InvalidTripDataException {

		if (basePrice < 100) {
			throw new InvalidTripDataException("Base price needs to be greater or equal than 100$");
		}

		if (duration < 1 || duration > 20) {
			throw new InvalidTripDataException("Duration can only be 1 to 20 days");
		}

		if (travelingClient == null) {
			throw new InvalidTripDataException("Client ID must exist in current client array.");
		}

		this.destination = destination;
		this.durationInDays = duration;
		this.basePrice = basePrice;
		this.travelingClient = travelingClient.copy();
		this.transportation = null;
		this.accommodation = null;
		tripIdGenerator();
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
		if (other.accommodation != null) {
			this.accommodation = other.accommodation.copy();
		} else {
			this.accommodation = null;
		}

		tripIdGenerator();
	}

	// Public constructor for file loading (Bypasses ID generation)
	public Trip(String tripId, String destination, int durationInDays, double basePrice, Client travelingClient) {
		this.tripId = tripId;
		this.destination = destination;
		this.durationInDays = durationInDays;
		this.basePrice = basePrice;

		if (travelingClient != null) {
			this.travelingClient = travelingClient;
		} else {
			this.travelingClient = null; 
		}

		this.transportation = null;
		this.accommodation = null;
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

		boolean accommodationEquals = (this.accommodation == null && other.accommodation == null) || 
				(this.accommodation != null && this.accommodation.equals(other.accommodation));

		return clientEquals && transportEquals && accommodationEquals;
	}

	// Returns a formatted string displaying all Trip details, cascading down to print
	// the details of the Client, Transportation, and Accommodation if they exist.
	@Override
	public String toString() {
		String clientInfo = (travelingClient != null) ? travelingClient.toString() : "No client assigned.";
		String transportInfo = (transportation != null) ? transportation.toString() : "No transportation booked.";
		String accommodationInfo = (accommodation != null) ? accommodation.toString() : "No accommodation booked.";

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
	// accommodation cost (based on duration), and transportation cost.
	// Safely handles trips where accommodation or transportation haven't been booked yet.
	public double calculateTotalCost() {
		double total = basePrice;

		if (this.accommodation != null) {
			total += this.accommodation.calculateCost((durationInDays-1));
		}

		if (this.transportation != null) {
			total += this.transportation.calculateCost();
		}

		return total;
	}

	// Generates Trip ID and increments ID number for next trip
	private void tripIdGenerator() {
		tripId = "T" + numId;
		numId++;
	}

	// --- Getters and Setters ---


	public String getDestination() {
		return destination;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDurationInDays(int durationInDays) throws InvalidTripDataException {
		if (durationInDays < 1 || durationInDays > 20) {
			throw new InvalidTripDataException("Duration can only be 1 to 20 days");
		}
		this.durationInDays = durationInDays;
	}

	public void setBasePrice(double basePrice) throws InvalidTripDataException {
		if (basePrice < 100.0) {
			throw new InvalidTripDataException("Base price needs to be greater or equal than 100$");
		}
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
		if (this.accommodation == null) {
			return null;
		}
		return this.accommodation.copy();
	}

	// Sets accommodation using a deep copy to protect encapsulation, or null if not provided
	public void setAccommodation(Accommodation accommodation) {
		if (accommodation == null) {
			this.accommodation = null;
		} else {
			this.accommodation = accommodation.copy();
		}
	}

	// Returns a deep copy of the traveling client, or null if not set
	public Client getTravelingClient() {
		if (this.travelingClient == null) {
			return null;
		}
		return this.travelingClient;
	}

	// Sets the traveling client using a deep copy to protect encapsulation, or null if not provided
	public void setTravelingClient(Client travelingClient) throws InvalidTripDataException {
		if (travelingClient == null) {
			throw new InvalidTripDataException("Client ID must exist in current client array.");
		}

		this.travelingClient = travelingClient.copy();
	}

	@Override
	public int compareTo(Trip o) {
		return Double.compare(o.calculateTotalCost(), this.calculateTotalCost());
	}

	@Override
	public String toCsvRow() {
		return this.getId() + ";" + this.travelingClient.getId() + ";" + this.accommodation.getId() + ";" + this.transportation.getId() + ";" + this.destination + ";" + this.durationInDays + ";" + this.basePrice;
	}

	@Override
	public double getBasePrice() {
		return basePrice;
	}

	@Override
	public double getTotalCost() {	
		return calculateTotalCost();
	}

	@Override
	public String getId() {
		return tripId;
	}

	public static Trip fromCsvRow(String csvLine) throws InvalidTripDataException {     
		String[] parts = csvLine.split(";", -1); 

		if (parts.length != 7) {
			throw new InvalidTripDataException("Invalid CSV format: Trip row must have exactly 7 fields.");
		}

		String tripId = parts[0];
		String clientId = parts[1];
		String accmId = parts[2]; // Might be ""
		String transId = parts[3]; // Might be ""
		String destination = parts[4];

		int durationDays;
		double basePrice;
		try {
			durationDays = Integer.parseInt(parts[5]);
			basePrice = Double.parseDouble(parts[6]);
		} catch (NumberFormatException e) {
			throw new InvalidTripDataException("Invalid number format for duration or price in CSV.");
		}

		if (clientId == null || clientId.trim().isEmpty()) {
			throw new InvalidTripDataException("Invalid Trip: ClientID is mandatory.");
		}

		if (clientId == null || clientId.trim().isEmpty()) {
			throw new InvalidTripDataException("Invalid Trip: ClientID is mandatory.");
		}
		
		//Je vais fix plus tard;
		return null;

		//Temporary client 
		//    Client shellClient = new Client(clientId);

		// Est-ce que ils se font validate par les exceptions. (Non faut je fixe)
		//  Trip newTrip = new Trip(tripId, destination, durationDays, basePrice, shellClient);

		//   if (accmId != null && !accmId.trim().isEmpty()) {
		//      Accommodation shellAccm = new Accommodation(accmId); 

		//      newTrip.setAccommodation(shellAccm); 
		//  }

		//   if (transId != null && !transId.trim().isEmpty()) {
		//       Transportation shellTrans = new Transportation(transId);

		//       newTrip.setTransportation(shellTrans); 
		//   }

	}

}