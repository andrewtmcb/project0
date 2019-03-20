package project0;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminConsole implements Console {
	private LoggingUtil log = new LoggingUtil();

		@Override
		public void run(User user, Scanner scan) {
			Scanner sc = scan;
			boolean running = true;
			String userInput;
			while(running) {
				System.out.println("                  Welcome "+user.getFirstName());
				System.out.println("--------------------------------------------------");
				System.out.println();
				System.out.println();
				String options[] = {"View/Edit Customer Detials","Approve Pending Account Request","logout"};
				userInput = OutputAssist.menuDisplay("Please select one of the following options", options, sc);
				switch (Integer.valueOf(userInput)) { 
			       
				
	
				case 1: 
						System.out.println("Please provide a username:");
					Customer custy = null;
					try {
						custy = (Customer) BankingUtilAndDOA.getUserByUsername(sc.next());
					} catch (InvalidInputException e2) {
						log.logError(e2.getMessage());
						e2.printStackTrace();
					}
						System.out.println(custy.getUsername());
						Account selectedAccount = OutputAssist.selectUsersAccount(custy,sc);
						String[] accountOptions= {"Withdraw","Deposit","Transfer","Delete User and All Accounts"};
						userInput = OutputAssist.menuDisplay("Please select one of the following for account: "+selectedAccount.getAcctNumber(), accountOptions, sc);
						switch (Integer.valueOf(userInput)) { 
						       
							
						case 1: 
								System.out.println("How much would you like to withdraw?");
				            	try {
									selectedAccount.withdrawl(Double.valueOf(sc.next()));
								} catch (NumberFormatException | InvalidInputException e1) {
									log.logError(e1.getMessage());
									e1.printStackTrace();
								};
					            break; 
					            
					  
					     case 2: 
					        	System.out.println("How much would you like to deposit?");
				            	try {
									selectedAccount.deposit(Double.valueOf(sc.next()));
								} catch (NumberFormatException | InvalidInputException e1) {
									log.logError(e1.getMessage());
									e1.printStackTrace();
								}
					          break; 
					        
					        
					        
					      case 3:
					        		String[] transferInput= {"",""};
					        		sc.nextLine();
					            	System.out.println("Please provide the full account number you would like to transfer to");
					            	transferInput[0]=sc.nextLine();
					            	System.out.println("How much would you like to transfer?");
					            	transferInput[1]=sc.next();
					            	try {
					            		Account accountTo = BankingUtilAndDOA.findAccountByNumber(transferInput[0]);
					            		BankingUtilAndDOA.transfer(selectedAccount, accountTo, Double.valueOf(transferInput[1]));
					            	}	 catch (AccountNotFoundException | NumberFormatException | InvalidInputException | SQLException e) {
										log.logError(e.getMessage());
										e.printStackTrace();
									}
					    
					            
					            break; 
							
					            
					            
					      	case 4: 
							try {
								BankingUtilAndDOA.deleteUserAndAllAccoutns(custy.getUsername());
							} catch (SQLException e) {
								log.logError(e.getMessage());
								e.printStackTrace();
							}
							log.logInfo("Accounts pending accounts require approval ------ redirecting");
					          break;        
					            
					          
					          
					          
					          
					          
					       default:
					    	   System.out.println("Invalid section, returning to main menu");
							}
			            
			  
			     case 2: 
			        	OutputAssist.accountApprovalMenu(scan);
			          break; 
			        
			        
			        
			      case 3:
			        	System.out.println("Sending you back to the main menu");
			        	running = false;
			            
			            break; 
					
			       default:
			    	   System.out.println("Invalid section, returning to main menu");
					}
			}
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
