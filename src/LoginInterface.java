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
        returnString += "2. Create a new Account\n>:";

        return returnString;
    }

    // Method to display the prompt to login
    // asks for login and password
    public static String[] loginPrompt( String userOrStaff )
    {
        String[] returnStringArray = { userOrStaff.equals("user") ? "User First Name: " : "Staff First Name: ",
                                       "Password: "};

        return returnStringArray;
    }

    // Method to generate the menu for a new account
    // asks user if they want a new staff or user account
    public static String newAccountInitialMenu()
    {
        String returnString = "Choose from the menu:\n";
        returnString += "1.Staff Account\n";
        returnString += "2.User Account\n>:";

        return returnString;
    }

    // Method to generate the menu prompts for the new user account
    public static String[] newAccountUserMenu()
    {
        String[] returnStringArray = {  "First Name: ",
                                        "Last Name: ",
                                        "Password: ",
                                        "Confirm Password: ",
                                        "\nHouse: ",
                                        "Street: ",
                                        "City: ",
                                        "State: ",
                                        "Zip: ",
                                        "Email: ",
                                        "Phone Number: "
                                        };
        return returnStringArray;
    }

    // Method to generate hte menu prompts for the new staff account
    public static String[] newAccountStaffMenu()
    {
        String[] returnStringArray = {  "First Name: ",
                                        "Last Name: ",
                                        "Password: ",
                                        "Confirm Password: ",
                                        };

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
