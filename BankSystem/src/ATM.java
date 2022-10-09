import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {

        //Create instance of Scanner
        Scanner scan = new Scanner(System.in);

        //init Bank
        Bank theBank = new Bank("Bank of Drausin");

        // add a user, which also creates a savings account
        User aUser = theBank.addUser("John", "Doe", "1234");

        // add a checking account for our user
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, scan);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser, scan);
        }
    }

    /**
     * Print the ATM's login menu
     *
     * @param theBank the Bank object whose accounts to user
     * @param scan    the Scanner object to use for user input
     * @return the authenticated User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner scan) {

        //init
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID/pin combo until the correct one is reached
        do {

            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = scan.nextLine();
            System.out.print("Enter pin: ");
            pin = scan.nextLine();

            //try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID/pin. Please try again.");
            }

        } while (authUser == null);

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner scan) {

        // print a summary of the user's accounts
        theUser.printAccountsSummary();

        // init
        int choice;

        //user menu
        do {
            System.out.printf("Welcome %s, what would like to do?\n",
                    theUser.getFirstName());

            System.out.println("   1) Show account transaction history");
            System.out.println("   2) Withdraw");
            System.out.println("   3) Deposit");
            System.out.println("   4) Transfer");
            System.out.println("   5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = scan.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Choice 1-5");
            }
        } while (choice < 1 || choice > 5);

        // process the choice
        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, scan);
                break;
            case 2:
                ATM.withdrawFunds(theUser, scan);
                break;
            case 3:
                ATM.depositFunds(theUser, scan);
                break;
            case 4:
                ATM.transferFunds(theUser, scan);
                break;
        }

        //redisplay this menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, scan);
        }
    }

    /**
     * Show the transaction histiory for an account
     *
     * @param theUser the logged-in User object
     * @param scan    the Scanner object used for user input
     */
    public static void showTransHistory(User theUser, Scanner scan) {
        int theAcct;

        // get account whose transaction history to look at
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" +
                    "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = scan.nextInt() - 1;
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        //print the transaction history
        theUser.printAccountTransHistory(theAcct);
    }

    /**
     * Process transferring funds from one account to another
     *
     * @param theUser the logged-in User object
     * @param scan    the Scanner object used for user input
     */
    public static void transferFunds(User theUser, Scanner scan) {

        //init
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        //get the account to transfer from
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" +
                    "to transfer from: ", theUser.numAccounts());
            fromAcct = scan.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAccountBalance(fromAcct);

        //get the account to transfer to
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" +
                    "to transfer from: ", theUser.numAccounts());
            toAcct = scan.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        //get the amount to transfer
        do {
            System.out.printf("Enter the amount to transfer(max $%.02f): $",
                    acctBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);


        //finally do the transfer
        theUser.addAccountTransaction(fromAcct, -1 * amount, String.format(
                "Transfer to account %s", theUser.getAccountUUID(toAcct)));
        theUser.addAccountTransaction(toAcct, amount, String.format(
                "Transfer from account %s", theUser.getAccountUUID(fromAcct)));
    }

    /**
     * Process a fund withdraw from an account
     *
     * @param theUser the logged-in User object
     * @param scan    the Scanner object used for user input
     */
    public static void withdrawFunds(User theUser, Scanner scan) {
        //init
        int fromAcct;
        double amount;
        double acctBal;
        String memo;


        //get the account to withdraw from
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" +
                    "to withdraw from: ", theUser.numAccounts());
            fromAcct = scan.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAccountBalance(fromAcct);


        //get the amount to withdraw
        do {
            System.out.printf("Enter the amount to withdraw(max $%.02f): $",
                    acctBal);
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must not be greater than\n" +
                        "balance of $%.02f.\n", acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        //gobble up rest of previous input
        scan.nextLine();

        //get the memo
        System.out.println("Enter a memo: ");
        memo = scan.nextLine();

        // do the withdraw
        theUser.addAccountTransaction(fromAcct, -1 * amount, memo);
    }

    public static void depositFunds(User theUser, Scanner scan) {

        //init
        int toAcct;
        double amount;
        double acctBal;
        String memo;


        //get the account to withdraw from
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" +
                    "to deposit to: ", theUser.numAccounts());
            toAcct = scan.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account. Please try again.");
            }
        } while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAccountBalance(toAcct);


        //get the amount to withdraw
        do {
            System.out.print("Enter the amount to deposit: $");
            amount = scan.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount < 0);

        //gobble up rest of previous input
        scan.nextLine();

        //get the memo
        System.out.println("Enter a memo: ");
        memo = scan.nextLine();

        // do the withdraw
        theUser.addAccountTransaction(toAcct, amount, memo);
    }


}
