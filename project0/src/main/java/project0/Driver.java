package project0;
import java.util.Scanner;

public class Driver {
	private static String input = "";
	private static Scanner sc = new Scanner(System.in);
	private static boolean running = true;
	private static CustomerConsole cc = new CustomerConsole();
	private static EmployeeConsole ec = new EmployeeConsole();
	private static AdminConsole ac = new AdminConsole();
	private static LoggingUtil log = new LoggingUtil();
	
	
	
	public static void main(String[] args) {
		while(running) {
			User u = null;
			System.out.println("            Welcome to Revature Bank              ");
			System.out.println("--------------------------------------------------");
			System.out.println("                  Please select from the following");
			System.out.println("(1)               Register new account            ");
			System.out.println("(2)               Login                           ");
			System.out.println("");
			input = sc.next();
			if (input.equals("1")) {
				cc.runRegistraionService();
			}else if(input.equals("2")) {
				System.out.println("                 Welcome Back                     ");
				System.out.println("--------------------------------------------------");
				System.out.println("                 Please enter username            ");
				System.out.println("");
				String usernameInput = sc.next();
				System.out.println("                 Please enter password            ");
				String passwordInput = sc.next();
				try {
					u = BankingUtilAndDOA.getUserByUsernameAndValidatePassword(usernameInput, passwordInput);
				} catch (InvalidInputException e) {
					log.logError(e.getMessage());
				}
				if(u instanceof Customer) {
					cc.run(u,sc);
				}else if (u instanceof Employee) {
					ec.run(u, sc);
				}else if(u instanceof Admin) {
					ac.run(u,sc);
				}
			}else {
				System.out.println("invalid input returning to main menu");
			}
		}
	}
}
