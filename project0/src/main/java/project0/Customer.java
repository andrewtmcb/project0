package project0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Customer extends User{
	
	private static final long serialVersionUID = 9093911299610170057L;
	private List<String> acctNumbers = new ArrayList<String>();
	private boolean hasPendingAcctRequest;
	private String[] ez = {"",""};
	
	
	public void setEz(String fromAcct,String targetAcct, String user0,String user1) {
		ez[0]= fromAcct;
		ez[1]= targetAcct;
		ez[2]=user0+" and "+user1;
	}
	
	public void setEz(String fromAcct, String targetAcct, String user0) {
		ez[0]= fromAcct;
		ez[1]= targetAcct;
		ez[2]= user0;
	}
	
	public String[] getEz(){
		return ez;
	}
	
	public boolean hasEz() {
		if(ez[0]=="") {
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
	
	public Customer(String username,String password,String firstName,String lastName,String SSN, Map<String,User> bankUsers) throws InvalidInputException{
		super(username, password, firstName, lastName, SSN, bankUsers);
	}
	
}
