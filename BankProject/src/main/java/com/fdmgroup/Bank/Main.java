
package com.fdmgroup.Bank;

import java.util.Scanner;

import com.fdmgroup.controller.AccountController;

public class Main {
	private static final AccountController controller = new AccountController();
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean running = true;
		while (running) {
			printMenu();
			String choice = scanner.nextLine();
			switch (choice) {
			case "1" -> createCustomer();
			case "2" -> deleteCustomer();
			case "3" -> createAccount();
			case "4" -> deleteAccount();
			case "5" -> chargeFee();
			case "6" -> listCustomers();
			case "7" -> viewBalance();
			case "8" -> deposit();
			case "9" -> withdraw();
			case "10" -> listAccounts();
			case "11" -> transfer();
			case "0" -> running = false;
			default -> System.out.println("Invalid option. Try again.");
			}
		}
		System.out.println("Goodbye!");
	
	}
	

	private static void printMenu() {
		System.out.println("\n----------Bank Teller Menu -----------------");
		System.out.println("1. Create Customer                         |");
		System.out.println("2. Delete Customer                         |");
		System.out.println("3. Create Account                          |");
		System.out.println("4. Delete Account                          |");
		System.out.println("5. Charge Fee to Customer                  |");
		System.out.println("6. List All Customers                      |");
		System.out.println("7. View Account Balance                    |");
		System.out.println("8. Deposit Money                           |");
		System.out.println("9. Withdraw Money                          |");
		System.out.println("10. List All Accounts                      |");
		System.out.println("11. Transfer Money Between Accounts        |");
		System.out.println("0. Exit                                    |");
		System.out.print("Enter your choice: ");
	}

	private static void createCustomer() {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Enter address: ");
		String address = scanner.nextLine();
		System.out.print("Enter type (person/company): ");
		String type = scanner.nextLine();
		Customer customer = controller.createCustomer(name, address, type);
		if (customer != null) {
			String customerType = (customer instanceof Person) ? "person" : "company";

			System.out.println("Created " + customerType + " with account ID: " + customer.getCUSTOMER_ID() + " with Name: " + customer.getName());
		} else {
			System.out.println("Invalid customer type.");
		}
	}
	
	


	private static void viewBalance() {
		System.out.print("Enter Account ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Account account = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == id).findFirst()
				.orElse(null);
		if (account != null) {
			System.out.println("Account Name: " + account.getACCOUNT_ID());
			System.out.println("Balance: £" + account.getBalance());
		} else {
			System.out.println("Account not found.");
		}
	}

	private static void deposit() {
		System.out.print("Enter Account ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Account account = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == id).findFirst()
				.orElse(null);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}
		System.out.print("Enter amount to deposit: ");
		double amount = Double.parseDouble(scanner.nextLine());
		account.deposit(amount);
		System.out.println("Deposit successful. New balance: £" + account.getBalance());
	}

	private static void withdraw() {
		System.out.print("Enter Account ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Account account = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == id).findFirst()
				.orElse(null);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}
		System.out.print("Enter amount to withdraw: ");
		double amount = Double.parseDouble(scanner.nextLine());
		if (account.withdraw(amount)) {
			System.out.println("Withdrawal successful. New balance: £" + account.getBalance());
		} else {
			System.out.println("Insufficient funds.");
		}
	}

	private static void transfer() {
		System.out.print("Enter Source Account ID: ");
		int fromId = Integer.parseInt(scanner.nextLine());
		Account fromAccount = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == fromId).findFirst()
				.orElse(null);

		if (fromAccount == null) {
			System.out.println("Source account not found.");
			return;
		}

		System.out.print("Enter Destination Account ID: ");
		int toId = Integer.parseInt(scanner.nextLine());
		Account toAccount = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == toId).findFirst()
				.orElse(null);

		if (toAccount == null) {
			System.out.println("Destination account not found.");
			return;
		}

		System.out.print("Enter amount to transfer: ");
		double amount = Double.parseDouble(scanner.nextLine());

		if (fromAccount.getBalance() < amount) {
			System.out.println("Insufficient funds in source account.");
			return;
		}

		fromAccount.withdraw(amount);
		toAccount.deposit(amount);
		System.out.printf("Transferred £%.2f from Account %d to Account %d%n", amount, fromAccount.getACCOUNT_ID(),
				toAccount.getACCOUNT_ID());
	}

	private static void listAccounts() {
		if (controller.getAccounts().isEmpty()) {
			System.out.println("No accounts available.");
			return;
		}
		for (Customer customer : controller.getCustomers()) {
			for (Account account : controller.getAccounts()) {
				System.out.printf("- Customer Name: %s | Account ID: %d | Balance: £%.2f | Type: %s%n",
						customer.getName(), account.getACCOUNT_ID(), account.getBalance(),
						account.getClass().getSimpleName());
			}
		}
	}

	private static void deleteCustomer() {
		System.out.print("Enter Customer ID to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		Customer toRemove = controller.getCustomers().stream().filter(c -> c.getCUSTOMER_ID() == id).findFirst()
				.orElse(null);
		if (toRemove != null) {
			controller.removeCustomer(toRemove);
			System.out.println("Customer removed.");
		} else {
			System.out.println("Customer not found.");
		}
	}

	private static void createAccount() {
		System.out.print("Enter Customer ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Customer customer = controller.getCustomers().stream().filter(c -> c.getCUSTOMER_ID() == id).findFirst()
				.orElse(null);
		if (customer == null) {
			System.out.println("Customer not found.");
			return;
		}
		System.out.print("Enter account type (c for checking/s for savings): ");
		String type = scanner.nextLine();
		
		
		Account account = controller.createAccount(customer, type);
		if (account != null) {
			String customerType = (customer instanceof Person) ? "person" : "company";
			System.out.println("Created " + customerType + " with account ID: " + account.getACCOUNT_ID());
		} else {
			System.out.println("Invalid account type.");
		}
	}

	private static void deleteAccount() {
		System.out.print("Enter Account ID to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		Account toRemove = controller.getAccounts().stream().filter(a -> a.getACCOUNT_ID() == id).findFirst()
				.orElse(null);
		if (toRemove != null) {
			controller.removeAccount(toRemove);
			System.out.println("Account removed.");
		} else {
			System.out.println("Account not found.");
		}
	}

	private static void chargeFee() {
		System.out.print("Enter Customer ID to charge: ");
		int id = Integer.parseInt(scanner.nextLine());
		Customer customer = controller.getCustomers().stream().filter(c -> c.getCUSTOMER_ID() == id).findFirst()
				.orElse(null);
		if (customer == null) {
			System.out.println("Customer not found.");
			return;
		}
		System.out.print("Enter amount to charge: ");
		double amount = Double.parseDouble(scanner.nextLine());
		customer.chargeAllAccounts(amount);
		System.out.println("Fee charged.");
	}

	private static void listCustomers() {
		if (controller.getCustomers().isEmpty()) {
			System.out.println("No customers available.");
			return;
		}
		for (Customer customer : controller.getCustomers()) {
			System.out.println("- " + customer.getCUSTOMER_ID() + " | " + customer.getName() + " | "
					+ customer.getClass().getSimpleName());
		}
	}

}
