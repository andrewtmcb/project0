package project0;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;




@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@Mock	
	private User user;
	
	@Mock
	private ResultSet rs;
	
	@Mock 
	private PreparedStatement ps;
	
	@Mock
	private Connection conn;
	

	@Before
	public void setUp() throws Exception {
		assertNotNull(conn);
		when(conn.createStatement()).thenReturn(ps);
		
		user = new User();
		user.setUsername("HernandezJose");
		user.setPassword("MarinersLove");
		user.setFirstName("Jose");
		user.setSSN("600226683");
		user.setLastName("Hernandez");
		
		
		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(1);
		when(rs.getString(2)).thenReturn(user.getUsername());
		when(rs.getString(3)).thenReturn(user.getPassword());
		when(rs.getString(4)).thenReturn(user.getFirstName());
		when(rs.getString(5)).thenReturn(user.getSSN());
		when(rs.getString(6)).thenReturn(user.getLastName());
		
	}

	//@Test(expected=NullPointerException.class)
	//public void test() throws SQLException {
	//	new BankingUtilAndDOA(conn).createUser(null);
	//}

}