//TASK 1
//Online Banking System
//Description : Create an Online Banking System that enables users to perform various banking operations, including account balance inquiries, fund transfers, and transaction history.

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: $" + balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount!");
            return false;
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            transactionHistory.add("Transferred: $" + amount + " to " + recipient.getAccountNumber());
        }
    }

    public void showTransactionHistory() {
        System.out.println("Transaction History for " + accountHolderName + " (" + accountNumber + "):");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class OnlineBankingSystem {
    private static Map<String, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Online Banking System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Withdraw Funds");
            System.out.println("5. Transfer Funds");
            System.out.println("6. View Transaction History");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    checkBalance();
                    break;
                case 3:
                    depositFunds();
                    break;
                case 4:
                    withdrawFunds();
                    break;
                case 5:
                    transferFunds();
                    break;
                case 6:
                    viewTransactionHistory();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Thank you for using the Online Banking System!");
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        BankAccount account = new BankAccount(accountNumber, accountHolderName, balance);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully!");
    }

    private static void checkBalance() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.println("Account Balance: $" + account.getBalance());
        }
    }

    private static void depositFunds() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.deposit(amount);
            System.out.println("Deposit successful!");
        }
    }

    private static void withdrawFunds() {
        BankAccount account = getAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();  // Consume newline
            account.withdraw(amount);
        }
    }

    private static void transferFunds() {
        BankAccount sender = getAccount();
        if (sender != null) {
            System.out.print("Enter recipient account number: ");
            String recipientAccountNumber = scanner.nextLine();
            BankAccount recipient = accounts.get(recipientAccountNumber);
            if (recipient != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                sender.transfer(recipient, amount);
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Recipient account not found!");
            }
        }
    }

    private static void viewTransactionHistory() {
        BankAccount account = getAccount();
        if (account != null) {
            account.showTransactionHistory();
        }
    }

    private static BankAccount getAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found!");
        }
        return account;
    }
}
