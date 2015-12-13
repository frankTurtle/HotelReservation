/**
 * Created by Barret J. Nobel on 12/4/2015.
 */
public class AccountManagementInterface
{
    // Method to display the initial menu
    // has three different paths depending on who's logged in
    public static String initialMenu( String accountType )
    {
        String returnString = "Please choose from the following:\n";
        int type = ( accountType.equals("U") ) ? 2 : ( accountType.equals("A") ) ? 3 : 0; //... determines if its a user / staff / admin

        switch( type ) //todo update all the questions for each type of account
        {
            case 3: //......................................................................... ADMIN
                returnString += "ADMIN MENU\n";
                returnString += "1. View my account\n";
                returnString += "2. View all accounts\n";
                returnString += "3. View a single account\n";
                returnString += "4. Create a new account\n";
                returnString += "5. Delete an account\n";
                returnString += "6. Previous menu\n>:";
                break;

            case 0: //......................................................................... STAFF
                returnString += "STAFF MENU\n";
                returnString += "1. View my account\n";
                returnString += "2. View all accounts\n";
                returnString += "3. View a single account\n";
                returnString += "4. Create a new account\n";
                returnString += "5. Previous menu\n>:";
                break;

            case 2: //.......................................................................... USER
                returnString += "USER MENU\n";
                returnString += "1. View my account\n";
                returnString += "2. Update my account\n";
                returnString += "3. Delete my account\n";
                returnString += "4. Previous menu\n>:";
                break;
        }

        return returnString;
    }
    // Method to generate the menu for a new account
    // asks user if they want a new staff or user account
    public static String newAccountInitialMenu()
    {
        String returnString = "Choose from the menu:\n";
        returnString += "1.Staff Account\n";
        returnString += "2.User Account\n";
        returnString += "3.Previous menu\n>:";

        return returnString;
    }

    // Method to generate the menu prompts for the new user account
    public static String[] newAccountUserMenu()
    {
        String[] returnStringArray = {  "First Name: ",
                                        "Last Name: ",
                                        "Password: ",
                                        "Confirm Password: ",
                                        "\nHouse Number: ",
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
                                        "Administrator ( Y or N ):"
                                    };

        return returnStringArray;
    }

    public static String deleteAccountConfirmation()
    {
        return String.format( "Are you sure you want to delete your account ( Y or N )?%n>:");
    }

    public static String deleteAccountConfirmation( String idNumber )
    {
        return String.format( "Are you sure you want to delete account %s ( Y or N )?%n>:", idNumber );
    }

    public static String[][] adminDeleteAccountMenu()
    {
        String[] initialMenu = { newAccountInitialMenu() };
        String[] userID = { "Enter UserID: " };
        String[] staffID = { "Enter StaffID: " };
        String[] delete = { deleteAccountConfirmation() };

        String[][] returnString = {initialMenu,
                                    userID,
                                    staffID,
                                    delete
                                    };

        return returnString;
    }

    public static String viewSingleAccountMenu()
    {
        return "Please enter the ID\n>: ";
    }

}
