/**
 * Subclass of Account
 * An account created for the User
 * It
 *
 * @author Barret J. Nobel
 * @version 0.1
 */
public class UserAccount extends Account
{
    private String street; //............... instance variables
    private String city;
    private String state;
    private int zipCode;
    private String emailAddress;
    private int phoneNumber;

    /**
     * Default constructor
     */
    UserAccount()
    {
        this( "", "", "U", "", "", 0, "", "", "", 0, "", 0 );
    }

    /**
     * Constructor with parameters
     * @param firstName User's first name
     * @param lastName User's last name
     * @param accountType User's account type
     * @param username User's username
     * @param password User's password
     * @param accountId User's account ID
     * @param street User's street
     * @param city User's city
     * @param state User's state
     * @param zipCode User's zip code
     * @param emailAddress User's email address
     * @param phoneNumber User's phone number
     */
    UserAccount( String firstName, String lastName, String accountType, String username, String password, int accountId, String street, String city, String state, int zipCode, String emailAddress, int phoneNumber )
    {
        super( firstName, lastName, accountType, username, password, accountId );
        this.setStreet( street );
        this.setCity( city );
        this.setState( state );
        this.setZipCode( zipCode );
        this.setEmailAddress( emailAddress );
        this.setPhoneNumber( phoneNumber );
    }

    /**
     * Setter method to set the current street for the user's address
     * @param street Current street
     */
    public void setStreet( String street )
    {
        this.street = street;
    }

    /**
     * Setter method to set the current city for the user's address
     * @param city Current city
     */
    public void setCity( String city )
    {
        this.city = city;
    }

    /**
     * Setter method for the current state for the user's address
     * Valid with only two characters, that match the list of current states
     * @param state current sate
     */
    public void setState( String state )
    {
        this.state = state;
    }

    /**
     * Setter method for the current zip code
     * @param zipCode current zip code
     */
    public  void  setZipCode( int zipCode )
    {
        this.zipCode = zipCode;
    }

    /**
     * Setter method for the current email address
     * @param emailAddress current email address
     */
    public void setEmailAddress( String emailAddress )
    {
        this.emailAddress = emailAddress;
    }

    /**
     * Setter method for the Phone Number
     * @param phoneNumber the User's Phone number
     */
    public void setPhoneNumber( int phoneNumber )
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter method for the current street
     * @return current street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Getter method for the current city
     * @return current city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Getter method for the current state
     * @return current state
     */
    public String getState()
    {
        return state;
    }

    /**
     * Getter method for the current zip code
     * @return current zip code
     */
    public int getZipCode()
    {
        return zipCode;
    }

    /**
     * Getter method for the current email address
     * @return current email address
     */
    public String getEmailAddress()
    {
        return emailAddress;
    }

    /**
     * Getter method for the current phone number
     * @return current phone number
     */
    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Override the toString method
     * @return String displaying the User first / last name, account type, username, account ID, address, email, phone number
     */
    @Override
    public String toString()
    {
        return  String.format( "%s\n" +
        "The Account holders Street is: %s\n" +
        "The Account holders City is: %s\n" +
        "The Account holders State is: %s\n" +
        "The Account holders Zip is: %d\n" +
        "The Account holders Email is: %s\n" +
        "The Account holders Phone numbers is: %d", super.toString(), this.getStreet(), this.getCity(), this.getState(), this.getZipCode(), this.getEmailAddress(), this.getPhoneNumber());
    }
}