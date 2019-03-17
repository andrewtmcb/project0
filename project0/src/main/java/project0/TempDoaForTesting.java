package project0;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TempDoaForTesting {
	private static Connection conn = ConnectionFactory.getConnection();
	
	
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
}
