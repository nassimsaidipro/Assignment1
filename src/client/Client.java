// -----------------------------------------------------------------------
// Assignment 1
// Written by: Darwinsh Saint-Jean(40341644) and Nassim Saidi(40345885)
// -----------------------------------------------------------------------

package client;

//This class represents a client of the travel agency.
//It stores personal information about the client such as their first name,
//last name, and email address.
//Each client is automatically assigned a unique ID using a static counter,
//ensuring every client can be individually identified within the system.
public class Client {

	// Static counter to generate unique client IDs starting from 1001
	private static int numId = 1001;
	private String clientId;
	private String firstName;
	private String lastName;
	private String emailAddress;

	// Default constructor - initializes all fields to empty strings
	// and assigns a unique client ID using the static counter
	public Client() {
		firstName = "";
		lastName = "";
		emailAddress = "";
		clientId = "C" + numId;
		numId++;
	}

	// Parameterized constructor - initializes fields with provided values
	// and assigns a unique client ID using the static counter
	public Client(String firstName, String lastName, String emailAdress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAdress;
		clientId = "C" + numId;
		numId++;
	}

	// Copy constructor - copies all fields from another Client object
	// and assigns a new unique client ID using the static counter
	public Client(Client other) {
		firstName = other.firstName;
		lastName = other.lastName;
		emailAddress = other.emailAddress;
		clientId = "C" + numId;
		numId++;
	}

	// Private constructor used EXCLUSIVELY by the copy() method.
	// It manually assigns all fields, including the exact clientId, to create a perfect clone.
	// This deliberately bypasses the static counter so numId does not falsely increment.
	private Client(String clientId, String firstName, String lastName, String emailAddress) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}

	// Returns a deep copy of this Client object.
	// It relies on the private backdoor constructor to ensure the exact ID is retained
	// without accidentally inflating the static numId sequence.
	public Client copy() {
		return new Client(this.clientId, this.firstName, this.lastName, this.emailAddress);
	}

	// Returns a formatted string displaying all relevant Client details
	@Override
	public String toString() {
		return "Client ID: " + clientId + "\n" +
				"First Name: " + firstName + "\n" +
				"Last Name: " + lastName + "\n" +
				"Email: " + emailAddress;
	}

	// Checks equality based on first name, last name, and email address (excludes ID)
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Client other = (Client) obj;

		return (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.emailAddress.equals(other.emailAddress));

	}

	// --- Getters and Setters ---

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAdress() {
		return emailAddress;
	}
	public String getClientId() {
		return clientId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAddress = emailAdress;
	}

}