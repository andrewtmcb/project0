package project0;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
	try {
		Map<String,User> um = TempDoaForTesting.loadUsers();
		Collection<User> uc = um.values();
		for(User u: uc) {
			System.out.println("Username: "+u.getUsername()+" Password: "+u.getPassword());
		}
	} catch (InvalidInputException e) {
		System.out.println("Something went way wrong");
		e.printStackTrace();
	}
	}
}
