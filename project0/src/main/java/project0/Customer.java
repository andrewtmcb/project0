package project0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer extends User{
	
	private static final long serialVersionUID = 9093911299610170057L;
	private List<String> acctNumbers = new ArrayList<String>();
	private boolean hasPendingAcctRequest;
	private String[] ez = {"","",""};
	
	
	public void setEz(String fromAcct,String targetAcct, String user0,String user1) {
		this.ez[0]= fromAcct;
		this.ez[1]= targetAcct;
		this.ez[2]=user0+" and "+user1;
	}
	
	public void setEz(String fromAcct, String targetAcct, String user0) {
		this.ez[0]= fromAcct;
		this.ez[1]= targetAcct;
		this.ez[2]= user0;
	}
	
	public String[] getEz(){
		return ez;
	}
	
	public boolean hasEz() {
		if(ez[0].equals("")) {
			return false;
		}
		return true;
	}
	
	public boolean isHasPendingAcctRequest() {
		return hasPendingAcctRequest;
	}

	public void setHasPendingAcctRequest(boolean hasPendingAcctRequest) {
		this.hasPendingAcctRequest = hasPendingAcctRequest;
	}

	public List<String> getAcctNumbers() {
		return acctNumbers;
	}
	
	public void addNewAcct(String acctNumber) {
		this.acctNumbers.add(acctNumber);
	}
	
	@Override
	public String toString() {
		String output = "";
		output+="Username: "+ this.getUsername()+"Password: "+this.getPassword()+"Firstname : "+this.getFirstName()+"Lastname: "+this.getLastName()+"SSN: "+this.getSSN();
		return output;
	}
	
	public Customer(String username,String password,String firstName,String lastName,String SSN) throws InvalidInputException{
		super(username, password, firstName, lastName, SSN);
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
