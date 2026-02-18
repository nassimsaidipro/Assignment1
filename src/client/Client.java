package client;

public class Client {

	private static int numId = 1001;
	private String clientId;
	private String firstName;
	private String lastName;
	private String emailAdress;

	public Client() {
		firstName = "";
		lastName = "";
		emailAdress = "";
		clientId = "C" + numId;
		numId++;
	}


	public Client(String firstName, String lastName, String emailAdress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdress = emailAdress;
		clientId = "C" + numId;
		numId++;
	}

	public Client(Client other) {
		firstName = other.firstName;
		lastName = other.lastName;
		emailAdress = other.emailAdress;
		clientId = "C" + numId;
		numId++;
	}

	@Override
	public String toString() {
		return "Client ID: " + clientId + "\n" +
				"First Name: " + firstName + "\n" +
				"Last Name: " + lastName + "\n" +
				"Email: " + emailAdress;
	}

	@Override
	public boolean equals(Object obj) {	
		
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Client other = (Client) obj;

		return (this.firstName.equals(other.firstName) && this.lastName.equals(other.lastName) && this.emailAdress.equals(other.emailAdress));
		
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAdress() {
		return emailAdress;
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
		this.emailAdress = emailAdress;
	}


}
