package project0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomerConsole implements Console{
	
	
	public void run(User user) {
		boolean running = true;
		while(running) {
			Scanner sc = new Scanner(System.in);
			Customer customer = (Customer) user;
			List<Account> customersAccounts = new ArrayList<Account>();
			Map<String, Account> allAccounts = BankingUtil.loadAccts();
			Map<String, User> allUsers = BankingUtil.loadUsers();
			Account selectedAccount = null;
			String userInput = "";
			
			for(String acctNum: customer.getAcctNumbers()) {
				customersAccounts.add(allAccounts.get(acctNum));
			}
		
		
			System.out.println("                  Welcome"+customer.getFirstName());
			System.out.println("--------------------------------------------------");
			System.out.println();
			System.out.println();
			String options[] = {"Request new account","Add/remove joint account onwers","","logout"};
			if(customer.hasEz()) {
				options[3]="Ez transfer to "+customer.getEz()[2];
			}else {
				options[3]="Setup Ez transfer";
			}
			userInput = OutputAssist.accountMenuHybridCustomerView("", options, sc, customer, customersAccounts);
			if(userInput.startsWith("a-") && userInput.length()==3) {
				try {
					selectedAccount = customersAccounts.get(Integer.valueOf(userInput.substring(2)));
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Invalid account selection");
					continue;
				}
			}else {
				switch (Integer.valueOf(userInput)) { 
		       
				
				case 1: 
		            if(customer.isHasPendingAcctRequest()) {
		            	System.out.println("Pending account request already exists, thank you for your patience while we process your request");
		            }else {
		            	customer.setHasPendingAcctRequest(true);
		            	System.out.println("Request successfully submitted, our associates will contact you as soon as your request has been processed");
		            }
		            break; 
		            
		  
		        case 2: 
		            System.out.println("Florida state law requires that ownership alteration requests be in person, we appologize for any inconvience"); 
		          break; 
		        
		        
		        
		        case 3: 
		            if(customer.hasEz()) {
		            	System.out.println("Y/N you would like to transfer funds from your account: "+ customer.getEz()[0]);
		            	System.out.println("to "+customer.getEz()[2]+"s account: "+customer.getEz()[1]);
		            	if(sc.next().toUpperCase().equals("Y")) {
		            		System.out.println("How much would you like to transfer?");
		            		BankingUtil.transfer(customer.getEz()[0], customer.getEz()[1], Double.valueOf(sc.next()));
		            	}
		            }
		            break; 
		        
		        
		        
		        case 4: 
		            running = false; 
		            break; 
				}
				
			}
			System.out.println();
			System.out.println("Returning to main menu");
			saveAcctChanges(customersAccounts, allAccounts);
			saveUserChanges(user, allUsers);
		
		}
	}
	
	public void runRegistraionService() {
		Map<String, User> allUsers = BankingUtil.loadUsers();
		boolean running = true;
		Scanner sc = new Scanner(System.in);
		while(running) {
			System.out.println("              Revature Bank Registrar             ");
			System.out.println("--------------------------------------------------");
			System.out.println("          Please enter username                   ");
			System.out.println("");
			
			String usernameInput = sc.next();
			System.out.println("          Please enter password (8 char or longer)");
			System.out.println();
			String passwordInput = sc.next();
			System.out.println("          Please confirm password                 ");
			System.out.println("");
			String passwordInput2 = sc.next();
			System.out.println("          Please enter First Name                 ");
			System.out.println();
			String firstNameInput = sc.next();
			System.out.println("          Please enter Last Name                  ");
			System.out.println("");
			String lastNameInput = sc.next();
			System.out.println("          Please enter SSN (with no lines or spaces)");
			String SSNInput = sc.next();
			if(!passwordInput.equals(passwordInput2)) {
				//write message how passwords do not match
			}else {
				try {
					Customer customer = new Customer(usernameInput,passwordInput,firstNameInput,lastNameInput,SSNInput, allUsers);
					saveUserChanges(customer,allUsers);
					running = false;
				} catch (InvalidInputException e) {
					// TODO write error message reguarding invalid input
					System.out.println("something went wrong, please try again");
					e.printStackTrace();
				}
			}
	}
}
	

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
	public void saveAcctChanges(List<Account> customersAccounts,Map<String, Account> allAccounts) {
		for(Account acct: customersAccounts) {
			allAccounts.replace(acct.getAcctNumber(), acct);
		}
		BankingUtil.saveAccts(allAccounts);
	}
	
	public void saveUserChanges(User customer, Map<String, User> allUsers) {
		
	}
	
	

	


}
