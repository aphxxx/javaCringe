import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    /**
     * Get the name of the bank
     *
     * @return name of the bank
     */
    public String getName() {
        return name;
    }

    /**
     * Create a new Bank object with empty lists of users and accounts
     *
     * @param name
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }


    /**
     * Generate a new UUID for a user.
     *
     * @return the UUID
     */
    public String getNewUserUUID() {

        // init
        String uuid;
        Random rand = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer) rand.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);


        return uuid;
    }

    /**
     * Generate a new UUID for an account.
     *
     * @return the UUID
     */
    public String getNewAccountUUID() {

        // init
        String uuid;
        Random rand = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer) rand.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);


        return uuid;
    }

    /**
     * Add an account for the bank.
     *
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Create a new User of the bank
     *
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param pin       the user's pin
     * @return          the new User object
     */
    public User addUser(String firstName, String lastName, String pin) {

        //create a new User object and add it to out list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account for the user and add to User and Bank accounts lists
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the User object associated with a particular userID and pin, if they are valid
     *
     * @param userID the UUID of the user
     * @param pin    the pin of the user
     * @return       the User object, if login is successful, or null, if it is not
     */
    public User userLogin(String userID, String pin) {
        // search through list of user
        for (User u : this.users) {
            //check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // if we haven't found the user of have an incorrect pin
        return null;
    }
}
