package project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TempDoaForTesting {
	private static Connection conn = ConnectionFactory.getConnection();
	
	
	/*
	public static  Map<String,User> loadUsers() throws InvalidInputException {
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
					ret.setUsername(rs.getString("username"));
					ret.setPassword(rs.getString("password"));
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retCusty = (Customer) ret;
					retCusty.setHasPendingAcctRequest(rs.getBoolean(7));
					retmap.put(retCusty.getUsername(), retCusty);
				}else if(rs.getInt(1)==1) {
					ret = new Employee();
					ret.setUsername(rs.getString("username"));
					ret.setPassword(rs.getString("password"));
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retmap.put(ret.getUsername(), ret);
				}else if (rs.getInt(1)==2) {
					ret = new Admin();
					ret.setUsername(rs.getString("username"));
					ret.setPassword(rs.getString("password"));
					ret.setFirstName(rs.getString("firstname"));
					ret.setLastName(rs.getString("lastname"));
					ret.setSSN(rs.getString("ssn"));
					retmap.put(ret.getUsername(), ret);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retmap;	
	}
	
	
	
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
	*/
	
	/*
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retmap;	
	}
	
	
	
	
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
	*/
	
	
}
