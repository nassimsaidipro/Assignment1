// -----------------------------------------------------------------------
// Assignment 1
// Question: 
// Written by: Darwinsh Saint-Jean (40341644)
// -----------------------------------------------------------------------
package driver;

import travel.*;

import java.util.Scanner;

import client.*;

public class Driver {

	// Arrays for the classes
	static Client[] clients;
	static Trip[] trips;
	static Transportation[] transports;
	static Accommodation[] accomomdations;

	public static void main(String[] args) {
		
		
		// Scanner to prompt user input
		Scanner input = new Scanner(System.in);
		
		
		//Welcome message
		System.out.println("=====================================");
		System.out.println(" Welcome to SmartTravel System");
		System.out.println(" Developed by: Darwinsh and Nassim!");
		System.out.println("=====================================\n");
		
		//Prompting the user for their choice between the menu or the scenario tests
		System.out.println(" Please, choose an option:");
        System.out.println("1. Predefined testing scenario");
        System.out.println("2. Menu interface");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        
        
        if(choice ==1) {
        	
        } else if(choice ==2) {
        	
        } else {
        	System.out.print("\nInvalid choice, the program will now close.");
        }
        
        System.out.println("\nThank you for using our SmartTravel system!");

	}
	
	
	public static void predefinedScenario() {
		System.out.println("The predefined testing scenario is now running.");
		
		//Creating 3 clients
		Client c1 = new Client("Maygan", "Beauchamp", "mayg@gmail.com");
		Client c2 = new Client("Venta", "Raji", "venta@gmail.com");
		Client c3 = new Client("Veda", "Melky", "velky@gmail.com");
		
		
	}

	public static Transportation[] copyTransportationArray() {
		return null;
	}
}
