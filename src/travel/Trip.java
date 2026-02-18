package travel;

import client.Client;

public class Trip {

	private static int numId = 2001;
	private String tripId;
	private String destination;
	private int duration;
	private double basePrice;
	private Client travelingClient;


	public Trip() {
		destination = "";
		duration = 0;
		basePrice = 0.0;
		travelingClient = null;
		tripId = "T" + numId;
		numId++;
	}

	public Trip(String destination, int duration, double basePrice, Client travelingClient) {
		this.destination = destination;
		this.duration = duration;
		this.basePrice = basePrice;
		this.travelingClient = new Client(travelingClient);
		tripId = "T" + numId;
		numId++;
	}
	
	public Trip(Trip other) {
		
	}

	public double calculateTotalCost() {
		//TO DO
		return 0.0;
	}	    

	public String getTripId() {
		return tripId;
	}	    
	public String getDestination() {
		return destination;
	}	    
	public int getDuration() {
		return duration;
	}	      
	public String getTripType() {
		//TO DO
		return null;
	}	    
	public double getDistance() {
		//TO DO
		return 0.0;
	}

}
