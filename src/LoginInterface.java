/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

public class LoginInterface
{
    // Method to create the initial menu
    // has Login or create a new account options
    public static String initialMenu()
    {
        String returnString = "Please choose from the following:\n";
        returnString += "1. Login\n";
        returnString += "2. Create a new Account\n";
        returnString += "3. Exit\n>:";

        return returnString;
    }

    // Method to choose if it'll be a user or staff logging in
    public static String[] userOrStaffMenu()
    {
        String[] returnStringArray = { LoginInterface.generateHeader("Login"),
                                    "Please Choose from the following: \n",
                                    "1.User login\n",
                                    "2.Staff login\n>:"
                                     };

        return returnStringArray;
    }

    // Method to display the prompt to login
    // asks for login and password
    public static String[] loginPrompt( String userOrStaff )
    {
        String[] returnStringArray = { userOrStaff.equals("user") ? "UserID: " : "StaffID: ",
                                       "Password: "};

        return returnStringArray;
    }

    // Method to generate a header with test passed in
    public static String generateHeader( String titleIn )
    {
        String stars = "************************";
        String title = String.format( "* %-20s *", titleIn );
        return String.format("%n%s%n%s%n%s%n", stars, title, stars);
    }

}
