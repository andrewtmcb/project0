package project0;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 6487553139341416537L;
	private String acctNumber ="";
	private double balance=0.0;
	private User user0;
	private User user1;
	
	
	
	public User getUser0(){
		return user0;
	}
	
	public User getUser1() {
		return user1;
	}
	
	public boolean isJointAcct() {
		if(user1 == null) {
			return false;
		}
		return true;
	}
	
	
	
	public double getBalance() {
		return balance;
	}

	public void deposit(double depositAmount) throws InvalidInputException {
		if(depositAmount<0.0) {
			throw new InvalidInputException("Cannot deposit negative amount");
		}else {
			this.balance += depositAmount;
		}
	}
	
	
	
	public void withdrawl(double withdrawAmount) throws InvalidInputException {
		if(withdrawAmount<0.0) {
			throw new InvalidInputException("Cannot deposit negative amount");
		}else if(withdrawAmount > balance){
			throw new InvalidInputException("account overdraft denied");
		}else {
			balance -= withdrawAmount;
		}
	}
	
	
	
	public void addSecondUser(User user1) throws Exception {
		if(user1 == null) {
			user1 = this.user1;
		}else{
			throw new Exception("Account already has two users");
		}
	}
	
	
	
	
	public void removeSecondUser() {
		user1 = null;
	}
	
	
	
	
	public void removePrimaryUser() throws Exception {
		if(user0 != null) {
			user0 = user1;
			user1 = null;
		}else {
			throw new Exception("Account needs at least one user");
		}
	}
	
	
	
	
	public String getAcctNumber() {
		return acctNumber;
	}
	
	
	
	
	private void setAcctNumber() throws PreExistingKeyException {
		if(acctNumber.equals("")) {
			this.acctNumber = BankingUtilAndDOA.generateUniqueAcctNumber();
		}else {
			throw new PreExistingKeyException("AccountNumber already initialized");
		}
	}
	
	
	
	
	Account(User user0, double initDeposit){
		user0 = this.user0;
		try {
			this.deposit(initDeposit);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.setAcctNumber();
		} catch (PreExistingKeyException e) {
			// this should never happen
			e.printStackTrace();
		}
	}
	
	
	
	
	Account(User user0, User user1, double initDeposit){
		this(user0,initDeposit);
		this.user1 = user1;
	}

	
}
