import java.util.Scanner;

class UserAccount {
    private double balance;

    public UserAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

class ATM {
    private UserAccount account;
    private Scanner scanner;

    public ATM(UserAccount account) {
        this.account = account;
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Visit Again!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        return scanner.nextInt();
    }

    private void withdraw() {
        System.out.print("Please Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.printf("Collect your cash of $%.2f from Drawer. New balance: $%.2f\n", amount, account.getBalance());
        } else {
            System.out.println("You have Insufficient funds. Try again.");
        }
    }

    private void deposit() {
        System.out.print("Please Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        account.deposit(amount);
        System.out.printf("Deposited of $%.2f successfully. New balance: $%.2f\n", amount, account.getBalance());
    }

    private void checkBalance() {
        System.out.printf("Your Current balance: $%.2f\n", account.getBalance());
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        UserAccount account = new UserAccount(5000.0);
        ATM atm = new ATM(account);
        atm.run();
    }
}