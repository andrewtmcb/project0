package project0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public final class OutputAssist {
	
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

}
