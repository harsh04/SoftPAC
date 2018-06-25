package framework;

import java.util.Scanner;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

public class Console {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TestNG testngRunner = new TestNG();
		
		System.out.println("======================================");
		System.out.println("=== SoftPAC Testing Tool Framework ===");
		System.out.println("======================================\n\n");
		System.out.println("Test cases available to perform : ");
		System.out.println("1. Login");
		System.out.println("2. Membership Registration");
		System.out.println("3. Membership Modification");
		System.out.println("4. Transaction");
		System.out.println("5. Fixed Deposit");
		System.out.print("\n\nEnter your choice: ");
		int response = sc.nextInt();
		switch (response) {
		case 1:

			break;
		case 2:
			System.out.println("	1. Individual");
			System.out.println("	2. Non - Individual");
			System.out.print("\nEnter your choice: ");
			response = sc.nextInt();
			break;
		case 3:
			System.out.println("	1. Individual");
			System.out.println("	2. Non - Individual");
			System.out.println("	3. Alerts");
			System.out.print("\nEnter your choice: ");
			response = sc.nextInt();
			break;
		case 4:
			System.out.println("	1. Transaction Entry > Reciept/Payment");
			System.out.print("\nEnter your choice: ");
			response = sc.nextInt();
			break;
		case 5:
			System.out.println("	1. Create Deposit");
			System.out.println("	2. View Deposit");
			System.out.println("	3. Close Deposit");
			System.out.print("\nEnter your choice: ");
			response = sc.nextInt();
			break;
			//To run tests in Parallel mode
	        testngRunner.setParallel(XmlSuite.ParallelMode.METHODS);
	        //Provide the list of test classes
	        testngRunner.setTestClasses(new Class[] {
	                org.softpost.Class1.class,org.softpost.Class2.class });
	        //Run tests
	        testngRunner.run();
		default:
			break;
		}
	}

}
