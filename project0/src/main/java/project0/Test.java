package project0;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws InvalidInputException, SQLException {
		System.out.println(BankingUtilAndDOA.loadUsernamesWithPendingAccount().get(0));
	}
}
