import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class User {

    /**
     * The first name of user.
     */
    private String firstName;

    /**
     * The last name of user.
     */
    private String lastName;

    /**
     * The ID number of user.
     */
    private String uuid;

    /**
     * The MD5 hash of the user's pin;
     */
    private byte pinHash[];

    /**
     * The list of accounts of for this user
     */
    private ArrayList<Account> accounts;

    /**
     * Create a new user
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's account pin
     * @param theBank   the user's Bank Object
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {

        // set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MD5 hash, rather the original value, for security reason
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //get a new, uuid for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", firstName, lastName, this.uuid);
    }

    /**
     * Add an account for the user
     *
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Return the user's UUID
     *
     * @return the UUID
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Check whether a given pin matches the true User pin
     *
     * @param aPin the pin to check
     * @return whether the pin is valid or not
     */
    public boolean validatePin(String aPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        return false;
    }

    /**
     * Get the user's first name
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    public void printAccountsSummary() {

        System.out.printf("\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     *
     * @return the number of accounts
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account.
     *
     * @param acctIdx the index of the account to use
     */
    public void printAccountTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get the balance of a particular account
     *
     * @param acctIdx the index of the account to use
     * @return the balance of the account
     */
    public double getAccountBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /**
     * Get the UUID of the particular account
     * @param acctIdx  the index of the account to use
     * @return         the UUID of the account
     */
    public String getAccountUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAccountTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
