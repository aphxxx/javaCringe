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
        while(true){

            //stay in the login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, scan);

            //stay in main menu until user quits
            ATM.printUserMenu(curUser, scan);
        }
    }

    /**
     * Print the ATM's login menu
     * @param theBank   the Bank object whose accounts to user
     * @param scan      the Scanner object to use for user input
     * @return          the authenticated User object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner scan){

        //init
        String userID;
        String pin;
        User authUser;

        // prompt the user for user ID/pin combo until the correct one is reached
        do{

            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user ID: ");
            userID = scan.nextLine();
            System.out.print("Enter pin: ");
            pin = scan.nextLine();

            //try to get the user object corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if(authUser == null){
                System.out.println("Incorrect user ID/pin. Please try again.");
            }

        } while(authUser == null);

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner scan){

        // print a summary of the user's accounts
        theUser.printAccountsSummary();

        // init
        int choice;

        //user menu
        do{
            System.out.printf("Welcome %s, what would like to do?",
                    theUser.getFirstName());

            System.out.println("   1) Show account transaction history");
            System.out.println("   2) Withdraw");
            System.out.println("   3) Deposit");
            System.out.println("   4) Transfer");
            System.out.println("   5) Quit");
            System.out.println();
            choice = scan.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("Invalid choice. Choice 1-5");
            }
        } while(choice < 1 || choice > 5);

        // process the choice
        switch (choice){
            case 1:
                ATM.showTransHistory(theUser, scan);
                break;
            case 2:
                ATM.withdrawlFunds(theUser, scan);
                break;
            case 3:
                ATM.depositFunds(theUser, scan);
                break;
            case 4:
                ATM.transferFunds(theUser, scan);
                break;
        }

        //redisplay this menu unless the user wants to quit
        if (choice != 5){
            ATM.printUserMenu(theUser, scan);
        }
    }

    public static void showTransHistory(User theUser, Scanner scan){
        int theAcct;

        // get account whose transaction history to look at
        do {
            System.out.printf("Enter the number(1-%d) of the account\n"+
                    "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = scan.nextInt()-1;
            if(theAcct < 0 || theAcct  >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");
            }
        } while (theAcct < 0 || theAcct  >= theUser.numAccounts());

        //print the transaction history
        theUser.printAcctTransHistory(theAcct);
    }

    public static void withdrawlFunds(User theUser, Scanner scan){

    }

    public static void depositFunds(User theUser, Scanner scan){

    }

    public static void transferFunds(User theUser, Scanner scan){

    }
}
