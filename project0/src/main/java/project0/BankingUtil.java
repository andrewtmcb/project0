package project0;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;


public final class BankingUtil{
	private static final String USERS_FILENAME = "bank_user.dat";
	private static final String ACCTS_FILENAME = "bank_acct.dat";
	
	
	
	public static Map<String,User> loadUsers(){
		
		Map<String,User> bankUsers = null;
		
		try (FileInputStream fileIn = new FileInputStream(USERS_FILENAME); ObjectInputStream in = new ObjectInputStream(fileIn);) {
			
			bankUsers = (Map<String,User>) in.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e) {
			//add message about casting issues
			e.printStackTrace();
		}
		
		return bankUsers;
		
	}
	
	public static Map<String,Account> loadAccts(){
		
		Map<String,Account> bankAccts = null;
		
		try (FileInputStream fileIn = new FileInputStream(USERS_FILENAME); ObjectInputStream in = new ObjectInputStream(fileIn);) {
			
			bankAccts = (Map<String,Account>) in.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e) {
			//add message about casting issues
			e.printStackTrace();
		}
		
		return bankAccts;
		
	}
	
	public static void saveUsers(Map<String,User> bankUsers) {

		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream (USERS_FILENAME));) {

			//fileOut = new FileOutputStream(USERS_FILENAME);

			// fileOut.write("Some text".getBytes());

			//ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(bankUsers);
			System.out.println("Success");

		} catch (FileNotFoundException e) {
			System.out.println("file not found yo");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("you got I/O shiz goin wrong");
			e.printStackTrace();
		} 
		

	}
	
	public static void saveAccts(Map<String,Account> bankAccts) {

		try (ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream (ACCTS_FILENAME));
				) {

			//fileOut = new FileOutputStream(USERS_FILENAME);

			// fileOut.write("Some text".getBytes());

			//ObjectOutputStream out = new ObjectOutputStream(fileOut);

			out.writeObject(bankAccts);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		

	}
	
	public static String generateUniqueAcctNumber() {
		Random rand = new Random();
	  	String acctNumber = "RB";
	  	for (int i = 2; i < 16; i++){
	        int n = rand.nextInt(10) + 0;
	        acctNumber += Integer.toString(n);
	        if(i%4==0) {
	        	acctNumber += " ";
	        }
	    }	
	    return acctNumber;
	}
	
	public static String validateFormatSSN(String SSN) throws InvalidInputException, PreExistingKeyException {
		Map<String,User> bankUsers = loadUsers();
		String validSSN ="";
		if(SSN.length()!=9) {
			throw new InvalidInputException("invalid SSN");
		}else {
			for(int i = 0; i<9;i++) {
				if(Character.isDigit(SSN.charAt(i))) {
					validSSN += SSN.charAt(i);
				}else {
					throw new InvalidInputException("SSN must consist of only numbers");
				}
				if(i == 2 || i ==4) {
					validSSN += "-";
				}
			}
		}
		if(bankUsers==null) {
			return validSSN;
		}else if(bankUsers.containsKey(validSSN)) {
			throw new PreExistingKeyException("User already attached to this SSN");
		}else {
			return validSSN;
		
		}
		
	}
	
	public static List<String> extractUsernames() {
		Collection<User> users =  loadUsers().values();
		List<String> usernames = new ArrayList<String>();
		for(User u: users) {
			usernames.add(u.getUsername());
		}
		return usernames;
	}
	
	public static User getUserByUsernameAndPassword(String username,String password) throws InvalidInputException{
		Map<String,User> bankUsers = loadUsers();
		Collection<User> users = bankUsers.values();
		for(User u: users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		throw new InvalidInputException("Username or password is invalid");
		
	}
	
	public static boolean usernameIsUnique(String username) {
		Map<String,User> bankUsers = loadUsers();
		if(bankUsers==null) {
			return true;
		}
		List<String> usernames= extractUsernames();
		if(usernames.contains(username)) {
			return false;
		}
		return true;
	}
	public static void approveAccount(Customer c) {
		Account newAccount = new Account(c,0.0);
		c.addNewAcct(newAccount.getAcctNumber());
		Map<String,Account> accounts =loadAccts();
		Map<String,User> users =loadUsers();
		accounts.put(newAccount.getAcctNumber(), newAccount);
		users.replace(c.getSSN(), c);
		saveAccts(accounts);
		saveUsers(users);
	}
	
	public static void transfer(String accountNumber0, String accountNumber1,double transferAmount) {
		Map<String,Account> bankAccounts = loadAccts();
		transfer(bankAccounts.get(accountNumber0), bankAccounts.get(accountNumber1), transferAmount);
		
	}
	
	
	
	public static void transfer(Account accountFrom ,Account accountTo, double transferAmount) {
		Map<String,Account> bankAccounts = loadAccts();
		try {
			accountFrom.withdrawl(transferAmount);
			accountTo.deposit(transferAmount);
			bankAccounts.put(accountFrom.getAcctNumber(), accountFrom);
			saveAccts(bankAccounts);
			System.out.println("Transfer Succssful");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	public static Account findAccountByNumber(String accountNumber) throws AccountNotFoundException {
		Map<String,Account> bankAccounts = loadAccts();
		if(bankAccounts.containsKey(accountNumber)) {
			return bankAccounts.get(accountNumber);
		}else {
			throw new AccountNotFoundException("Account not found");
		}
	}
	
	
	
	
	
	private BankingUtil() {
		
	}
	
	

}
