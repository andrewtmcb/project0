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
		User c1 = new Customer("AutoHotkey","AutoHotkey98","Auto","Hotkey","878649597");
		Account a1 = new Account(c1,1000.00);
		//TempDoaForTesting.createAccount(a1);
		//System.out.println("Much Succcccses");
		a1.deposit(1000000.00);
		TempDoaForTesting.updateAccount(a1);
		System.out.println("Even More Succcccses");
		
	}
}
