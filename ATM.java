import java.util.HashMap;
import java.util.Scanner;

class ATM {

    class BankAccount {
        private String accountNumber;
        private double balance;

        public BankAccount(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public boolean deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                return true;
            }
            return false;
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
    private HashMap<String, BankAccount> accounts;

    public ATM() {
        accounts = new HashMap<>();
    }

    public void addAccount(String accountNumber, double initialBalance) {
        accounts.put(accountNumber, new BankAccount(accountNumber, initialBalance));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("Choose an option: ");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
                    } else {
                        System.out.println("Insufficient balance or invalid amount.");
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    if (account.deposit(depositAmount)) {
                        System.out.println("Deposit successful. Current balance: " + account.getBalance());
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
        public static void main(String[] args) {
            ATM atm = new ATM();
            atm.addAccount("123456", 1000.0);
            atm.addAccount("654321", 500.0);
            
            atm.start();
        }
}
