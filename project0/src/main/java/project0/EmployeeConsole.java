package project0;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeeConsole extends CustomerConsole {
	
	private LoggingUtil log = new LoggingUtil();
		
		@Override
		public void run(User user, Scanner scan) {
			boolean running = true;
			String userInput;
			while(running) {
				System.out.println("                  Welcome "+user.getFirstName());
				System.out.println("--------------------------------------------------");
				System.out.println();
				System.out.println();
				String options[] = {"View Customer Detials","Approve Pending Account Request","logout"};
				userInput = OutputAssist.menuDisplay("Please select one of the following options", options, scan);
				log.logDebug(userInput);
				switch (Integer.valueOf(userInput)) { 
			       
				
				case 1: 
						System.out.println("Please provide a username:");
		            	// TODO write customer information method in Output Assist
			            break; 
			            
			  
			     case 2: 
			    	 	log.logDebug("made it to case 2");
			        	boolean didItWork = OutputAssist.accountApprovalMenu(scan);
			        	log.logDebug(String.valueOf(didItWork));
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
				
				
				
				
				
				
				
				
				
				
			/*	
			Customer customer = (Customer) user;
			List<Account> customersAccounts = new ArrayList<Account>();
			Map<String, Account> allAccounts = BankingUtilAndDOA.loadAccounts();
			Map<String, User> allUsers;
			allUsers = BankingUtilAndDOA.loadUsers();
			Account selectedAccount = null;
			String userInput = "";
				
			for(String acctNum: customer.getAcctNumbers()) {
				customersAccounts.add(allAccounts.get(acctNum));
			}
			
			
			
				String options[] = {"Request new account","Add/remove joint account owners","","logout"};
				if(customer.hasEz()) {
					options[2]="Ez transfer to "+customer.getEz()[2];
				}else {
					options[2]="Setup Ez transfer";
				}
				userInput = OutputAssist.accountMenuHybridCustomerView("", options, sc, customer, customersAccounts);
				
				
				
				
				
				if(userInput.startsWith("a-") && userInput.length()==3) {
					try {
						selectedAccount = customersAccounts.get(Integer.valueOf(userInput.substring(2))-1);
					}catch(ArrayIndexOutOfBoundsException e){
						System.out.println("Invalid account selection");
						continue;
					}
					String[] accountOptions= {"Withdraw","Deposit","Transfer"};
					userInput = OutputAssist.menuDisplay("Please select one of the following for account: "+selectedAccount.getAcctNumber(), accountOptions, sc);
					switch (Integer.valueOf(userInput)) { 
					       
						
					case 1: 
							System.out.println("How much would you like to withdraw?");
			            	try {
								selectedAccount.withdrawl(Double.valueOf(sc.next()));
							} catch (NumberFormatException | InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							};
				            break; 
				            
				  
				     case 2: 
				        	System.out.println("How much would you like to deposit?");
			            	try {
								selectedAccount.deposit(Double.valueOf(sc.next()));
							} catch (NumberFormatException | InvalidInputException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				          break; 
				        
				        
				        
				      case 3:
				        		String[] transferInput= {"","",""};
				        		sc.nextLine();
				            	System.out.println("Please provide the full account number you would like to transfer to");
				            	transferInput[0]=sc.nextLine();
				            	System.out.println("How much would you like to transfer?");
				            	transferInput[1]=sc.next();
				            	System.out.println("(Y/N) would you like to make this your ez-transfer?");
				            	transferInput[2]=sc.next();
				            	try {
				            		Account accountTo = BankingUtilAndDOA.findAccountByNumber(transferInput[0]);
				            		BankingUtilAndDOA.transfer(selectedAccount, accountTo, Double.valueOf(transferInput[1]));
				            		if(transferInput[2].toUpperCase().equals("Y")) {
				            			//figure out why in gods name we need hard coding here
				            			customer.setEz(selectedAccount.getAcctNumber(), accountTo.getAcctNumber(), "MR.white");
				            			System.out.println(customer.getEz()[0]);
				            			saveUserChanges(customer);
				            		}
				            	}	 catch (AccountNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    
				            
				            break; 
						
				       default:
				    	   System.out.println("Invalid section, returning to main menu");
						}
						
						
						
						
						
						
						
					
				}
				
				
				
				
				
				else {
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
			            	System.out.println("to Mr/Ms "+customer.getEz()[2]+"'s account: "+customer.getEz()[1]);
			            	if(sc.next().toUpperCase().equals("Y")) {
			            		System.out.println("How much would you like to transfer?");
			            		BankingUtilAndDOA.transfer(customer.getEz()[0], customer.getEz()[1], Double.valueOf(sc.next()));
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
				saveUserChanges(customer);
			
			}
		}
		
		
		

	
		
	public static void displayUserByUsername() {
		
	}
	

	*/
	

}
