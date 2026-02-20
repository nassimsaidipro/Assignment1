package travel;

import client.Client;

public class Trip {

	private static int numId = 2001;
	private String tripId;
	private String destination;
	private int durationInDays;
	private double basePrice;
	private Client travelingClient;
	private Transportation transportation;
	private Accommodation accomodation; 


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
	
	public Trip(Trip other) {
		destination = other.destination;
		durationInDays = other.durationInDays;
		basePrice = other.basePrice;
		
		if (other.travelingClient != null) {
	        this.travelingClient = new Client(other.travelingClient); 
	    } else {
	        this.travelingClient = new Client(); 
	    }
		// A completer tcheck clone() method

		tripId = "T" + numId;
		numId++;
	}

	public double calculateTotalCost() {
		return basePrice + accomodation.calculateCost(durationInDays) + transportation.calculateCost();
	}
	
	//Add les autres accessors and mutators
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
	
	public void setTransportation(Transportation transportation) {
	    this.transportation = transportation;
	}

	public void setAccommodation(Accommodation accommodation) {
	    this.accomodation = accommodation;
	}
	

}
