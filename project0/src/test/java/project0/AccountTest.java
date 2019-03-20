package project0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AccountTest {

	private static Account a1 = new Account(); private static Account a2 = new Account();
	private static Account a3 = new Account ();
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	
	
	
	
	
	
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
	@Test
	public void testNormalDeposit() throws InvalidInputException {
		double bal1= a1.getBalance();
		a1.deposit(50.0);
		double bal2 = a1.getBalance();
		assertEquals(50.0, bal2-bal1,.1);
	}

	@Test(expected = InvalidInputException.class)
	public void testOverdraftWithdrawl() throws InvalidInputException {
		double bal = a1.getBalance();
		a1.withdrawl(bal+50.00);
	}
	
	@Test(expected = InvalidInputException.class)
	public void testNegativeDeposit() throws InvalidInputException {
		a1.withdrawl(-25.00);
	}
	@Test
	public void testNormaleWithdrawl() throws InvalidInputException {
		a1.deposit(50.00);
		double bal = a1.getBalance();
		a1.withdrawl(50);
		double bal2 = a1.getBalance();
		assertEquals(-50.0,bal2-bal,.1);
	}
	@Test(expected = InvalidInputException.class)
	public void testNegativeWithdrawl() throws InvalidInputException {
		a1.withdrawl(-90.0);
	}
	/*
	@Test
	public void testNormalTransfer() throws InvalidInputException {
		a1.deposit(50.0);
		double a1bal1=a1.getBalance();
		double a2bal1 = a2.getBalance();
		BankingUtilAndDOA.transfer(a1, a2, 50.0);
		double a1bal2 = a1.getBalance();
		double a2bal2 = a2.getBalance();
		assertEquals(-50.0,a1bal1-a1bal2);
		assertEquals(50.0,a2bal1-a2bal2);
	}
	@Test
	public void testNormalTransferAccountNumber() throws InvalidInputException {
		a1.deposit(50.0);
		double a1bal1=a1.getBalance();
		double a2bal1 = a2.getBalance();
		BankingUtilAndDOA.transfer(a1.getAcctNumber(), a2.getAcctNumber(), 50.0);
		double a1bal2 = a1.getBalance();
		double a2bal2 = a2.getBalance();
		assertEquals(-50.0,a1bal1-a1bal2);
		assertEquals(50.0,a2bal1-a2bal2);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testNegativeTransfer() {
		BankingUtilAndDOA.transfer(a1, a2, -50.0);
	}
	@Test(expected=InvalidInputException.class)
	public void testInvalidAccountNumber() {
		BankingUtilAndDOA.transfer("4367826478326", "3432432432", 50.0);
	}

	*/
	/*******************************************************************
	 * Test Constructor
	 * @throws InvalidInputException 
	 ******************************************************************/
	@Test
	public void accountConstructOneUser() throws InvalidInputException {
		Customer c1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
		Account testAccount = new Account(c1,20.00);
		assertNotNull(testAccount);
		assertEquals(20.00,testAccount.getBalance(),.1);//test initial deposit
	}

	@Test
	public void accountConstructerTwoUser() throws InvalidInputException {
		Customer c1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
		Customer c2 = new Customer("WalterWhite","WalterWhite98","Walter","White","674119497");
		Account testAccount = new Account(c1,c2,20.00);
		assertNotNull(testAccount);
		assertEquals(20.00,testAccount.getBalance(),.1);//test initial deposit
		assertNotNull(testAccount.getUser1());//test customer 1 has Account linked
		assertNotNull(testAccount.getUser0());//test customer 2 has Account linked
	}
/*
	@Test(expected=Exception.class)
	public void negativeInitDeposit() throws InvalidInputException {
		Customer c1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
		Customer c2 = new Customer("WalterWhite","WalterWhite98","Walter","White","674119497");
		Account testAccount = new Account(c1,c2,-1000.00);
	}

	@Test(expected=Exception.class)
	public void nullCustomers() {
		Account testAccount = new Account(null,null,-10000.00);
	}
*/
	/*******************************************************************
	 * add second user
	 ******************************************************************/
/*
	@Test
	public void addSecondUser() {
		try {
			a3.addSecondUser(c4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(a3.getAcctNumber(),c4.getAcctNumbers().get(0));
	}

	@Test(expected=InvalidInputException.class)
	public void addNullSecond() throws Exception {
		a3.addSecondUser(null);
	}
	/*******************************************************************
	 * Remove user
	 * @throws Exception 
	 ******************************************************************/
	/*
	@Test
	public void removePrimary() {
		try {
			a3.removePrimaryUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(a3.getUser1());
	}

	@Test(expected=InvalidInputException.class)
	public void removeSecondary() throws Exception {
		a3.removePrimaryUser();;
	}
	*/

	
	
}
