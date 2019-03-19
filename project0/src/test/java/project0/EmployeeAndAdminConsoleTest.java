package project0;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;

public class EmployeeAndAdminConsoleTest {
	Map<String,Account> tam; Map<String,User> tum;
	static Customer tc1; static Customer tc2; static Customer tc3; static Customer tc4;
	static Account ta1; static Account ta2; static Account ta3; static Account ta4;
	
	
	@BeforeClass
	public void accountTestSetup() throws InvalidInputException{
	tc1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
	tc2 = new Customer("WalterWhite","WalterWhite98","Walter","White","674119497");
	tc3 = new Customer("JJAbraham","JJAbraham98","JJ","Abraham","878600597");
	tc4 = new Customer("PeterBacon","PeterBacon98","Peter","Bacon","674009497");
	tc1.setHasPendingAcctRequest(false); tc2.setHasPendingAcctRequest(false);
	Account ta1 = new Account(tc1,1000.00);Account ta2 = new Account(tc2,1000.00);
	Account ta3 = new Account (tc3,1000.00);
	ta2.deposit(123.0);ta2.deposit(123.32);
	tc1.addNewAcct(ta1.getAcctNumber());tc2.addNewAcct(ta2.getAcctNumber());
	tam = new HashMap<String,Account>();
	tum = new HashMap<String,User>();
	tam.put(ta1.getAcctNumber(), ta1);tam.put(ta2.getAcctNumber(), ta2);
	tum.put(tc1.getSSN(), tc1);tum.put(tc2.getSSN(),tc2);
	//BankingUtil.saveAccts(tam);BankingUtil.saveUsers(tum);
	}
	
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
	
	
	
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
	
	
	
	
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
	
	
	
	
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
}
