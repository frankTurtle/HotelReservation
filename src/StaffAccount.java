/**
 * Subclass of Account
 * An account created for the Staff
 *
 * @author Barret J. Nobel
 * @version 0.1
 */
public class StaffAccount extends Account
{
    /**
     * Default constructor
     * by default the account created is not an administrator
     */
    StaffAccount()
    {
        this( "", "", "SM", "", "", 0 );
    }

    /**
     * Constructor with parameters
     * @param firstName the Staff's first dame
     * @param lastName the Staff's last name
     * @param accountType the type of Staff account 'A' for Admin 'SM' for Staff Member
     * @param password the Staff's password
     * @param accountId the Staff's unique ID
     */
    StaffAccount( String firstName, String lastName, String accountType, String username, String password, int accountId )
    {
        super( firstName, lastName, accountType, username, password, accountId );
    }

    /**
     * Override the toString method
     * @return String with the Staff  first / last name, account type and ID
     */
    @Override
    public String toString()
    {
        return super.toString();
    }
}