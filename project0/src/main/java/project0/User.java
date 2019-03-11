package project0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class User implements Serializable {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String SSN;
	
	
	
	public String getUsername() {
		return username;
	}




	public void setUsername(String username, Map<String,User> bankUsers) throws InvalidInputException {
		if(BankingUtil.usernameIsUnique(username, bankUsers)) {
			this.username = username;
		}else {
			throw new InvalidInputException("Username must be unique");
		}
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) throws InvalidInputException {
		if(password.length()<8) {
			throw new InvalidInputException("password must be at least 8 characters long");
		}else {
			this.password = password;
		}
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getSSN() {
		return SSN;
	}



	public void setSSN(String ssnInput,Map<String,User> bankUsers) {
		try {
			this.SSN = BankingUtil.validateFormatSSN(ssnInput, bankUsers);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PreExistingKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static final long serialVersionUID = -5041034682363391888L;
	
	public User(String username, String password, String firstName, String lastName, String SSN, Map<String,User> bankUsers) throws InvalidInputException {
		super();
		this.setUsername(username,bankUsers);
		this.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.setSSN(SSN, bankUsers);
	}

}
