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
	
	Map<String,Account> am; Map<String,User> um;
	static Customer c1; static Customer c2; static Customer c3; static Customer c4;
	static Account a1; static Account a2; static Account a3; static Account a4;
	
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	@BeforeClass
	public void accountTestSetup() throws InvalidInputException{
	c1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
	c2 = new Customer("WalterWhite","WalterWhite98","Walter","White","674119497");
	c3 = new Customer("JJAbraham","JJAbraham98","JJ","Abraham","878600597");
	c4 = new Customer("PeterBacon","PeterBacon98","Peter","Bacon","674009497");
	c1.setHasPendingAcctRequest(false); c2.setHasPendingAcctRequest(false);
	Account a1 = new Account(c1,1000.00);Account a2 = new Account(c2,1000.00);
	Account a3 = new Account (c3,1000.00);
	a2.deposit(123.0);a2.deposit(123.32);
	c1.addNewAcct(a1.getAcctNumber());c2.addNewAcct(a2.getAcctNumber());
	am = new HashMap<String,Account>();
	um = new HashMap<String,User>();
	am.put(a1.getAcctNumber(), a1);am.put(a2.getAcctNumber(), a2);
	um.put(c1.getSSN(), c1);um.put(c2.getSSN(),c2);
	BankingUtil.saveAccts(am);BankingUtil.saveUsers(um);
	}
	/*******************************************************************
	 * Deposit/ Withdraw and Transfer
	 * *****************************************************************/
	
	@Test
	public void testNormalDeposit() throws InvalidInputException {
		double bal1= a1.getBalance();
		a1.deposit(50.0);
		double bal2 = a1.getBalance();
		assertEquals(50.0, bal2-bal1);
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
		assertEquals(-50.0,bal2-bal);
	}
	@Test(expected = InvalidInputException.class)
	public void testNegativeWithdrawl() throws InvalidInputException {
		a1.withdrawl(-90.0);
	}
	@Test
	public void testNormalTransfer() throws InvalidInputException {
		a1.deposit(50.0);
		double a1bal1=a1.getBalance();
		double a2bal1 = a2.getBalance();
		BankingUtil.transfer(a1, a2, 50.0);
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
		BankingUtil.transfer(a1.getAcctNumber(), a2.getAcctNumber(), 50.0);
		double a1bal2 = a1.getBalance();
		double a2bal2 = a2.getBalance();
		assertEquals(-50.0,a1bal1-a1bal2);
		assertEquals(50.0,a2bal1-a2bal2);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testNegativeTransfer() {
		BankingUtil.transfer(a1, a2, -50.0);
	}
	@Test(expected=InvalidInputException.class)
	public void testInvalidAccountNumber() {
		BankingUtil.transfer("4367826478326", "3432432432", 50.0);
	}

	
	/*******************************************************************
	 * Test Constructor
	 ******************************************************************/
	@Test
	public void accountConstructOneUser() {
		Account testAccount = new Account(c1,20.00);
		assertNotNull(testAccount);
		assertEquals(20.00,a1.getBalance());//test initial deposit
		assertEquals(a1.getAcctNumber(),c1.getAcctNumbers().get(0));//test customer has Account linked
	}

	@Test
	public void accountConstructerTwoUser() {
		Account testAccount = new Account(c1,c2,20.00);
		assertNotNull(testAccount);
		assertEquals(20.00,testAccount.getBalance());//test initial deposit
		assertEquals(testAccount.getAcctNumber(),c1.getAcctNumbers().get(1));//test customer 1 has Account linked
		assertEquals(testAccount.getAcctNumber(),c2.getAcctNumbers().get(0));//test customer 2 has Account linked
	}

	@Test(expected=InvalidInputException.class)
	public void negativeInitDeposit() {
		Account testAccount = new Account(c1,c2,-1000.00);
	}

	@Test(expected=InvalidInputException.class)
	public void nullCustomers() {
		Account testAccount = new Account(null,null,10.00);
	}

	/*******************************************************************
	 * add second user
	 ******************************************************************/

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
	

	
	
}
