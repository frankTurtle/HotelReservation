/**
 * Superclass for User and Staff account Objects
 *
 * @author Barret J. Nobel
 * @version 0.1
 */
public class Account
{
    private String firstName;
    private String lastName;
    private String accountType;
    private int accountId;
    private String password;

    /**
     * Default constructor
     */
    public Account()
    {
        this( "", "", "", "", 0 );
    }

    /**
     * Constructor with parameters
     * @param firstName The person's firstName
     * @param lastName The person's last name
     * @param accountType The type of account the person has ( User or Staff )
     * @param password The person's password for the account
     * @param accountId The person's unique ID
     */
    public Account( String firstName, String lastName, String accountType, String password, int accountId )
    {
        this.setFirstName( firstName );
        this.setLastName( lastName );
        this.setAccountType( accountType );
        this.setPassword( password );
        this.setId( accountId );
    }

    /**
     * Setter method for the Account  first name
     * @param name The person's first name
     */
    public void setFirstName( String name )
    {
        this.firstName = name;
    }

    /**
     * Setter method for the Account last name
     * @param name The person's last name
     */
    public void  setLastName( String name )
    {
        this.lastName = name;
    }

    /**
     * Setter method for the Account type. Can be either 'U' for user or 'SA' for staff
     * @param accountType Either 'U' for user or 'SA' for staff
     */
    public void setAccountType( String accountType )
    {
        this.accountType = accountType;
    }

    /**
     * Setter method for the Password
     * @param password Account's password
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * Setter medthod for the accountID
     * @param accountId AccountID to set
     */
    private void setId( int accountId )
    {
        this.accountId = accountId;
    }

    /**
     * Getter method for the Account first name
     * @return Account first name
     */
    public String getFirstName()
    {
        return this.firstName;
    }

    /**
     * Getter method for the Account last Name
     * @return Account last name
     */
    public String getLastName()
    {
        return this.lastName;
    }

    /**
     * Getter method for the Account type
     * @return Account type
     */
    public String getAccountType()
    {
        return this.accountType;
    }

    /**
     * Getter method for the Account ID
     * @return  Account ID
     */
    public int getId()
    {
        return this.accountId;
    }

    /**
     * Getter method for the Account password
     * @return Account password
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Override the toString method
     * @return String with the Account first / last name, account type and ID
     */
    @Override
    public String toString()
    {
        return String.format( "The Account holders First name is: %s\n" +
                              "The Account holders Last name is: %s\n" +
                              "The Account type is: %s\n" +
                              "The Account holders ID is: %d\n", this.getFirstName(), this.getLastName(), this.getAccountType(), this.getId() );
    }
}