package framework;

import java.util.Scanner;
import org.testng.TestNG;

public class Console {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TestNG testngRunner = new TestNG();
		flag: while (true) {
			System.out.println("======================================");
			System.out.println("=== SoftPAC Testing Tool Framework ===");
			System.out.println("======================================\n\n");
			System.out.println("Test cases available to perform : ");
			System.out.println("1. Login");
			System.out.println("2. Membership Registration");
			System.out.println("3. Membership Modification");
			System.out.println("4. Transaction");
			System.out.println("5. Fixed Deposit");
			System.out.println("6. Run All Test Cases");
			System.out.println("7. Exit");
			System.out.print("\n\nEnter your choice: ");
			int response = sc.nextInt();
			switch (response) {
			case 1:
				//To run tests in Parallel mode
				//testngRunner.setParallel(XmlSuite.ParallelMode.METHODS);
				//Provide the list of test classes
				testngRunner.setTestClasses(new Class[] { KDF.Login.class });
				testngRunner.run();
				break;
			case 2:
				System.out.println("	1. Individual");
				System.out.println("	2. Non - Individual");
				System.out.print("\nEnter your choice: ");
				response = sc.nextInt();
				switch(response){
					case 1:
						testngRunner.setTestClasses(new Class[] { KDF.MembershipRegistrationIndividual.class });
						testngRunner.run();
						break;
					case 2:
						testngRunner.setTestClasses(new Class[] { KDF.MembershipRegistrationNonIndividual.class });
						testngRunner.run();
						break;
					default:
						System.out.println("Wrong input!");
						break;
				}

				break;
				
			case 3:
				System.out.println("	1. Individual");
				System.out.println("	2. Non - Individual");
				System.out.println("	3. Alerts");
				System.out.print("\nEnter your choice: ");
				response = sc.nextInt();
				switch(response){
					case 1:
						System.out.println("vaishali se mango...");
						break;
					case 2:
						testngRunner.setTestClasses(new Class[] { KDF.ModificationNonIndividual.class });
						testngRunner.run();
						break;
					default:
						System.out.println("Wrong input!");
						break;
				}
				break;
				
			case 4:
				System.out.println("	1. Transaction Entry > Reciept/Payment");
				System.out.println("	2. View Transactions");
				System.out.print("\nEnter your choice: ");
				response = sc.nextInt();
				switch(response){
					case 1:
						testngRunner.setTestClasses(new Class[] { KDF.TransactionEntry.class });
						testngRunner.run();
						break;
					case 2:
						testngRunner.setTestClasses(new Class[] { KDF.TransactionView.class });
						testngRunner.run();
						break;
					default:
						System.out.println("Wrong input!");
						break;
				}	
				break;
				
			case 5:
				System.out.println("	1. Create Single Fixed Deposit");
				System.out.println("	2. Create Joint Fixed Deposit");
				System.out.println("	3. View Deposit");
				System.out.println("	4. Close Deposit");
				System.out.print("\nEnter your choice: ");
				response = sc.nextInt();
				switch(response){
					case 1:
						testngRunner.setTestClasses(new Class[] { KDF.CreateFdSingle.class });
						testngRunner.run();
						break;
					case 2:
						testngRunner.setTestClasses(new Class[] { KDF.CreateFdJoint.class });
						testngRunner.run();
						break;
					case 3:
						testngRunner.setTestClasses(new Class[] { KDF.ViewFd.class });
						testngRunner.run();
						break;
					case 4:
						testngRunner.setTestClasses(new Class[] { KDF.closeFd.class });
						testngRunner.run();
						break;
					default:
						System.out.println("Wrong input!");
						break;
				}
				break;

			case 6:
				testngRunner.setTestClasses(new Class[] { 
						KDF.Login.class,
						KDF.MembershipRegistrationIndividual.class,
						KDF.MembershipRegistrationNonIndividual.class,
						KDF.ModificationNonIndividual.class,
						KDF.TransactionEntry.class,
						KDF.TransactionView.class,
						KDF.CreateFdSingle.class,
						KDF.CreateFdJoint.class,
						KDF.ViewFd.class,
						KDF.closeFd.class
						});
				testngRunner.run();
				break;
				
			case 7:
				System.out.println("==============================================");
				System.out.println("=== Closing SoftPAC Testing Tool Framework ===");
				System.out.println("==============================================\n\n");
				break flag;

			default:
				System.out.println("Wrong input!");
				break;
			}
			System.out.println("======================================");
			System.out.println("======================================");
			System.out.println("======================================");
			System.out.println("=== Test another case? \"y\" or \"n\": ===");
			if(!sc.next().equalsIgnoreCase("y")){
				break flag;
			}
		}
		sc.close();
	}

}