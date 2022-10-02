import java.util.ArrayList;

public class Account {
    /**
     * The name of the account.
     */
    private String name;

    /**
     * The account ID number.
     */
    private String uuid;

    /**
     * The User object that owns this account.
     */
    private User holder;

    /**
     * The list of transaction for this account.
     */
    private ArrayList<Transaction> transactions;

    /**
     * Create a new account
     * @param name the name of the account
     * @param holder the User object that hold this account
     * @param theBank the Bank that issues that account
     */
    public Account(String name, User holder, Bank theBank){
        // set account name and holder
        this.name = name;
        this.holder = holder;

        // get new account UUID
        this.uuid = theBank.getNewAccountUUID();

        // init transactions
        this.transactions = new ArrayList<Transaction>();

        /*
        // add to holder and bank lists
        holder.addAccount(this);
        theBank.addAccount(this);
         */
    }

    /**
     * Return an account UUID
     * @return the UUID
     */
    public String getUUID(){
        return this.uuid;
    }

}
