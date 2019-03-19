package project0;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public final class BankingUtilAndDOA{
	private static final String USERS_FILENAME = "bank_user.dat";
	private static final String ACCTS_FILENAME = "bank_acct.dat";
	private static Connection conn = ConnectionFactory.getConnection();
	private static LoggingUtil log = new LoggingUtil();
	
	
	public static ArrayList<String> loadUsernamesWithPendingAccount() {
		ArrayList<String> pendingAccountUsernames = new ArrayList<String>();
		String sql ="select find_users_with_pending_account_apporval();";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pendingAccountUsernames.add(rs.getString(1));
			}

		} catch (SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		
		
		
		return pendingAccountUsernames;
	}
	
	public static  Map<String,User> loadUsers() {
		Map<String,User> retmap = new HashMap<String,User>();
		User ret = null;
		Customer retCusty = null;
		String sql = "select * from proj0.user_table";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if(rs.getInt(1)==0) {
					ret = new Customer();
					try {
						ret.setUsername(rs.getString("username"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					try {
						ret.setPassword(rs.getString("password"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retCusty = (Customer) ret;
					retCusty.setHasPendingAcctRequest(rs.getBoolean(7));
					retmap.put(retCusty.getUsername(), retCusty);
				}else if(rs.getInt(1)==1) {
					ret = new Employee();
					try {
						ret.setUsername(rs.getString("username"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					try {
						ret.setPassword(rs.getString("password"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retmap.put(ret.getUsername(), ret);
				}else if (rs.getInt(1)==2) {
					ret = new Admin();
					try {
						ret.setUsername(rs.getString("username"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					try {
						ret.setPassword(rs.getString("password"));
					} catch (InvalidInputException e) {
						log.logError(e.getMessage());
						e.printStackTrace();
					}
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retmap.put(ret.getUsername(), ret);
				}
			}
		} catch (SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		
		String sqlq = "select * from proj0.user_account_table";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery(sqlq);
			while(rs2.next()) {
				retCusty = (Customer) retmap.get(rs2.getString("username"));
				retCusty.addNewAcct(rs2.getString("accountnumber"));
				retmap.replace(retCusty.getUsername(), retCusty);
			}
		}catch (SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		return retmap;	
	}
	
	
	
	
	
	
	public static  Map<String,Account> loadAccounts() throws InvalidInputException, PreExistingKeyException {
		Map<String,Account> retmap = new HashMap<String,Account>();
		Account ret = null;
		String sql = "select * from proj0.Account_table";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ret = new Account();
				ret.setAcctNumber(rs.getString("accountnumber"));
				ret.deposit(rs.getFloat("balance"));
				retmap.put(ret.getAcctNumber(), ret);
			}
		} catch (SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		
		String sqlq = "select * from proj0.user_account_table";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery(sqlq);
			while(rs2.next()) {
				ret = retmap.get(rs2.getString("accountnumber"));
				ret.addUser(rs2.getString("username"));
				retmap.replace(ret.getAcctNumber(), ret);
			}
		}catch (SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		return retmap;	
	}
	
	
	/*
	 * 
	 * Legacy code
	 * 
	 * 
	 * 
	
	public static Map<String,Account> loadAccts(){
		
		Map<String,Account> bankAccts = null;
		
		try (FileInputStream fileIn = new FileInputStream(ACCTS_FILENAME); ObjectInputStream in = new ObjectInputStream(fileIn);) {
			
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
	*/
	
	public static void updateUser(User u) throws SQLException {
		PreparedStatement updateUserTable = null;
		int userid = 0;
		 String updateStatement =
			        "update proj0.user_table " +
			        "set usertype =  ?, " +
			        "username =  ?, " +
			        "\"password\" =  ?, " +
			        "firstname =  ?," +
			        "lastname =  ?, " +
			        "ssn =  ?, " +
			        "haspendingaccount =  ? "+
			        "where username = ?";
		 conn.setAutoCommit(false);
		updateUserTable = conn.prepareStatement(updateStatement);
		if(!(u instanceof Customer)) {
			if(u instanceof Employee) {
				userid=1;
			}else {
				userid=2;
			}
			updateUserTable.setBoolean(7, false);
		}else {
			Customer custy = (Customer) u;
			updateUserTable.setBoolean(7, custy. isHasPendingAcctRequest() );
		}
		try {
				updateUserTable.setInt(1, userid);
				updateUserTable.setString(2, u.getUsername());
				updateUserTable.setString(3, u.getPassword());
				updateUserTable.setString(4, u.getFirstName());
				updateUserTable.setString(5, u.getLastName());
				updateUserTable.setString(6, u.getSSN());
				updateUserTable.setString(8, u.getUsername());
				updateUserTable.execute();
				conn.commit();
		} catch (SQLException e ) {
			if (conn != null) {
		        try {
		            System.err.print("Transaction is being rolled back");
		            System.out.println(e.getMessage());
		            conn.rollback();
		        } catch(SQLException excep) {
		                System.out.println("rollback failed");
		        } finally {
		                if (updateUserTable != null) {
		                    updateUserTable.close();
		                }
		                conn.setAutoCommit(true);
		        }
		    }
		}
	}
	
	
	public static void createUser(User u) throws SQLException {
		PreparedStatement insertUserTable = null;
		int userid = 0;
		 String insertStatement =
			        "insert into proj0.user_table(usertype ,username ,password ,firstname ,lastname , ssn, haspendingaccount) " +
			        "values (? ,? ,? ,?, ?, ?, ?)";
		 conn.setAutoCommit(false);
		 insertUserTable = conn.prepareStatement(insertStatement);
		if(!(u instanceof Customer)) {
			if(u instanceof Employee) {
				userid=1;
			}else {
				userid=2;
			}
			insertUserTable.setBoolean(7, false);
		}else {
			Customer custy = (Customer) u;
			insertUserTable.setBoolean(7, custy. isHasPendingAcctRequest() );
		}
		try {
				insertUserTable.setInt(1, userid);
				insertUserTable.setString(2, u.getUsername());
				insertUserTable.setString(3, u.getPassword());
				insertUserTable.setString(4, u.getFirstName());
				insertUserTable.setString(5, u.getLastName());
				insertUserTable.setString(6, u.getSSN());
				insertUserTable.execute();
				conn.commit();
		} catch (SQLException e ) {
			if (conn != null) {
		        try {
		            System.err.print("Transaction is being rolled back");
		            System.out.println(e.getMessage());
		            conn.rollback();
		        } catch(SQLException excep) {
		                System.out.println("rollback failed");
		        } finally {
		                if (insertUserTable != null) {
		                    insertUserTable.close();
		                }
		                conn.setAutoCommit(true);
		        }
		    }
		}
		
	}
	
	
	
	/*-----------------------------------LEAGACY CODE--------------------------------------------
	

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
	
------------------------------------------LEGACY CODE------------------------------------------------------------------------	
	*/
	
	public static void updateAccount(Account a) throws SQLException {
		PreparedStatement updateAccountTable = null;
		 String updateStatement =
			        "update proj0.account_table " +
			        "set balance =  ?"+
			        "where accountnumber = ?";
		 conn.setAutoCommit(false);
		updateAccountTable = conn.prepareStatement(updateStatement);
		try {
				updateAccountTable.setFloat(1, (float) a.getBalance());
				updateAccountTable.setString(2, a.getAcctNumber());
				updateAccountTable.execute();
				conn.commit();
		} catch (SQLException e ) {
			if (conn != null) {
		        try {
		            System.err.print("Transaction is being rolled back");
		            System.out.println(e.getMessage());
		            conn.rollback();
		        } catch(SQLException excep) {
		                System.out.println("rollback failed");
		        } finally {
		                if (updateAccountTable != null) {
		                    updateAccountTable.close();
		                }
		                conn.setAutoCommit(true);
		        }
		    }
		}
	}
	
	
	public static void createAccount(Account a) throws SQLException {
		PreparedStatement insertAccountTable = null;
		PreparedStatement insertUserAccountTable = null;
		boolean secondaryUser = false;
		if (a.getUser1()!=null) {
			secondaryUser=true;
		}
		 String insertStatementAccount =
			        "insert into proj0.account_table(accountnumber,balance) " +
			        "values (? ,? )";
		 String insertStatementUserAccount =
			        "insert into proj0.user_account_table(accountnumber, username) " +
			        "values (? ,? )";
		 conn.setAutoCommit(false);
		 
		try {
			insertAccountTable = conn.prepareStatement(insertStatementAccount);
			insertAccountTable.setString(1, a.getAcctNumber());
			insertAccountTable.setFloat(2, (float) a.getBalance());
			insertAccountTable.execute();
			conn.commit();
			insertUserAccountTable = conn.prepareStatement(insertStatementUserAccount);
			insertUserAccountTable.setString(1, a.getAcctNumber());
			insertUserAccountTable.setString(2, a.getUser0().getUsername());
			insertUserAccountTable.execute();
			if(secondaryUser) {
			insertUserAccountTable = conn.prepareStatement(insertStatementUserAccount);
			insertUserAccountTable.setString(1, a.getAcctNumber());
			insertUserAccountTable.setString(2, a.getUser1().getUsername());
			insertUserAccountTable.execute();
			}
			conn.commit();
		} catch (SQLException e ) {
			if (conn != null) {
		        try {
		            System.err.print("Transaction is being rolled back");
		            System.out.println(e.getMessage());
		            conn.rollback();
		        } catch(SQLException excep) {
		                System.out.println("rollback failed");
		        } finally {
		                if (insertUserAccountTable != null) {
		                    insertUserAccountTable.close();
		                }
		                conn.setAutoCommit(true);
		        }
		    }
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
		return SSN;
		
	}
	
	public static List<String> extractUsernames() {
		Collection<User> users;
		List<String> usernames = new ArrayList<String>();
		users = loadUsers().values();
		for(User u: users) {
		usernames.add(u.getUsername());
		}
		return usernames;
	}
	
	public static User getUserByUsernameAndValidatePassword(String username,String password) throws InvalidInputException{
		Map<String,User> bankUsers = loadUsers();
		Collection<User> users = bankUsers.values();
		for(User u: users) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		throw new InvalidInputException("Username or password is invalid");
		
	}
	
	public static User getUserByUsername(String username) throws InvalidInputException {
		Map<String,User> bankUsers = loadUsers();
		Collection<User> users = bankUsers.values();
		for(User u: users) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		throw new InvalidInputException("no matching username found");
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
		
	}
	
	public static void transfer(String accountNumber0, String accountNumber1,double transferAmount) {
		Map<String, Account> bankAccounts=null;
		try {
			bankAccounts = loadAccounts();
		} catch (InvalidInputException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		} catch (PreExistingKeyException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		try {
			transfer(bankAccounts.get(accountNumber0), bankAccounts.get(accountNumber1), transferAmount);
		} catch (InvalidInputException | SQLException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void transfer(Account accountFrom ,Account accountTo, double transferAmount) throws InvalidInputException, SQLException {
	
			accountFrom.withdrawl(transferAmount);
			accountTo.deposit(transferAmount);
			updateAccount(accountFrom);
			updateAccount(accountTo);
			log.logInfo("Transfer Succssful");
		
	}
	
	
	
	
	public static Account findAccountByNumber(String accountNumber) throws AccountNotFoundException {
		Map<String, Account> bankAccounts = null;
		try {
			bankAccounts = loadAccounts();
		} catch (InvalidInputException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		} catch (PreExistingKeyException e) {
			log.logError(e.getMessage());
			e.printStackTrace();
		}
		if(bankAccounts.containsKey(accountNumber)) {
			return bankAccounts.get(accountNumber);
		}else {
			throw new AccountNotFoundException("Account not found");
		}
	}
	


	private BankingUtilAndDOA() {
		
	}
	
	

}
