package project0;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Collection<User> c = BankingUtil.loadUsers().values();
		for(User custy: c) {
			System.out.println("Name: "+custy.getFirstName()+ "");
		}
	}
}
