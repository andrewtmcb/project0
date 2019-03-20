package project0;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class OutputAssist {

	private static LoggingUtil log = new LoggingUtil();
	
	
	public static String menuDisplay(String header,String[] options, Scanner sc){
		if(header.equals("")) {header = "Please select from the following";}
		System.out.println("                  "+header);
		System.out.println("------------------------------------------------------------");
		int i = 1;
		for(String option: options) {
			System.out.println("("+i+")               "+option);
			i++;
		}
		System.out.println("");
		String input = sc.next();
		return input;
		
	}
	
	public static void displayAccounts(User user,Collection<Account> accounts) {
		System.out.println("        Account Details for "+user.getFirstName()+" "+user.getLastName()+"           ");
		System.out.println("-----------------------------------------------------------------------");
		if(accounts.size()==0) {
		System.out.println("                       No active accounts              ");
		}else {
			int i = 1;
			for(Account a: accounts) {
				System.out.println("("+i+")   "+a.getAcctNumber()+"         balance:"+a.getBalance());
				i++;
			}
		}
		System.out.println();
	}
	
	public static String accountMenuHybridCustomerView(String header,String[]options, Scanner sc, Customer user, Collection<Account> accounts) {
		System.out.println();
		
		if(accounts.isEmpty()) {
			System.out.println("It looks like you still do not have an acive account...");
			if(user.isHasPendingAcctRequest()) {
				System.out.print("Our associates are working hard to approve your account request as soon as possible");
			}else {
				System.out.println("Y/N would you like me to process an account request for you now?");
				if(sc.next().toUpperCase().equals("Y")) {
					user.setHasPendingAcctRequest(true);
					System.out.println("Account request sucessful, proceeding to main menu...");
				}else {
					System.out.println("no problem, proceeding to main menu...");
				}
				System.out.println();
			}
			
		}else {
			displayAccounts(user,accounts);
			System.out.println("To make a withdrawl, deposit, or transfer simply type a- followed by the corresponding number displayed to the left of your account information");
			header = "Otherwise select from one of the following options";
		}
		return menuDisplay(header,options,sc);
		
	}
	
	
	
	
	//Important not reguarding this method: when an account is created for a user, a SQL trigger will automatically set hasPendingAccountApproval for the user to false
	public static boolean accountApprovalMenu(Scanner sc) {
		//log.logDebug("made it to the approval menu");
		ArrayList<String> usersWithPendingAccounts = BankingUtilAndDOA.loadUsernamesWithPendingAccount();
		//log.logDebug("made it past loading mending accounts");
		//log.logDebug(usersWithPendingAccounts.toString());
		int i = 1;
		boolean persist = true;
		while(persist) {
			//log.logDebug("made it into the while loop");
			if(usersWithPendingAccounts.size()==0) {
				//log.logDebug("looks like this pending users is somehow empty");
				//log.logInfo("No pending accounts exist  ---  returning to main menu");
			
				persist = false;
			}else {
				//log.logDebug("somehow made it past null arraylist check");
				System.out.println();
				System.out.println("     Users with Pending Approval  ----   Select an account to approve");
				System.out.println("----------------------------------------------------------------------");
				for (String username: usersWithPendingAccounts) {
					System.out.println("("+i+")   "+username);
					i++;
				}
				System.out.println("("+i+")      Return to main menu");
			}
			int selection = Integer.valueOf(sc.next());
			if(selection < 1 || selection > usersWithPendingAccounts.size()) {
				log.logInfo("Returning to Main Menu");
				persist = false;
			}else {
				Account newAccount = new Account(usersWithPendingAccounts.get(Integer.valueOf(selection)-1));
				try {
					BankingUtilAndDOA.createAccount(newAccount);
					log.logInfo("Account created for "+usersWithPendingAccounts.get(Integer.valueOf(selection)-1));
					usersWithPendingAccounts.remove(Integer.valueOf(selection)-1);
				} catch (SQLException e) {
					log.logError("issue creating account "+e.getMessage());
					e.printStackTrace();
				}
			}	
		}
		return true;
	}
	
	
	
	
	
	
	public static Account selectUsersAccount(User user,Scanner sc) {
		int i = 1;
		Map<String, Account> allAccounts = null;
		try {
			allAccounts = BankingUtilAndDOA.loadAccounts();
			//log.logDebug(allAccounts.values().toString());
		} catch (InvalidInputException | PreExistingKeyException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		Collection<Account> allAccountsCollection = allAccounts.values();
		//log.logDebug(String.valueOf(allAccountsCollection.size()));
		ArrayList<Account> customersAccounts = new ArrayList<Account>();
		for(Account a :allAccountsCollection) {
			if(a.getUser0()==null) {
				//log.logDebug("No primary??");
			}else if(a.getUser0().getUsername().equals(user.getUsername())) {
				customersAccounts.add(a);
				//log.logDebug(a.getAcctNumber()+"      "+a.getUser0().getUsername());
			}
		}
		System.out.println("        Account Details for "+user.getFirstName()+" "+user.getLastName()+"           ");
		System.out.println("-----------------------------------------------------------------------");
		if(customersAccounts.size()==0) {
		System.out.println("                       No active accounts              ");
		}else {
			for(Account a: customersAccounts) {
				System.out.println("("+i+")   "+a.getAcctNumber()+"         balance:"+a.getBalance());
				i++;
			}
		}
		System.out.println();
		return customersAccounts.get(Integer.valueOf(sc.next())-1);
	}
	

}
