import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;


    /**
     * Generate a new UUID for a user.
     * @return the UUID
     */
    public String getNewUserUUID(){

        // init
        String uuid;
        Random rand = new Random();
        int len = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rand.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for(User u: this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);



        return uuid;
    }

    /**
     * Generate a new UUID for an account.
     * @return the UUID
     */
    public String getNewAccountUUID(){

        // init
        String uuid;
        Random rand = new Random();
        int len = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {

            uuid = "";
            for (int i = 0; i < len; i++) {
                uuid += ((Integer)rand.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique = false;
            for(Account a: this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);



        return uuid;
    }

    /**
     * Add an account for the bank.
     * @param anAcct the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);

    }
}
