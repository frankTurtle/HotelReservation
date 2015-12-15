/**
 * Created by Barret J. Nobel on 12/4/2015.
 */
public class ReservationManagementInterface
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
                returnString += "1. View a reservation\n";
                returnString += "2. View all reservations\n";
                returnString += "3. Update a reservation\n";
                returnString += "4. Create a new reservation\n";
                returnString += "5. Cancel a reservation\n";
                returnString += "6. Check In\n>:";
                returnString += "7. Check Out\n>:";
                returnString += "8. Previous menu\n>:";
                break;

            case 0: //......................................................................... STAFF
                returnString += "STAFF MENU\n";
                returnString += "1. View a reservation\n";
                returnString += "2. View all reservations\n";
                returnString += "3. Update a reservation\n";
                returnString += "4. Create a new reservation\n";
                returnString += "5. Cancel a reservation\n";
                returnString += "6. Check In\n>:";
                returnString += "7. Check Out\n>:";
                returnString += "8. Previous menu\n>:";
                break;

            case 2: //.......................................................................... USER
                returnString += "USER MENU\n";
                returnString += "1. View my reservation\n";
                returnString += "2. Update my reservation\n";
                returnString += "3. Create a new reservation\n";
                returnString += "4. Cancel my reservation\n";
                returnString += "5. Previous menu\n>:";
                break;
        }

        return returnString;
    }
    // Method to generate the menu for a new account
    // asks user if they want a new staff or user account
    public static String newInitialMenu()
    {
        String returnString = "Choose from the menu:\n";
        returnString += "1.Staff Account\n";
        returnString += "2.User Account\n>:";

        return returnString;
    }

    // Method to generate the menu prompts for the new user account
    public static String[] newReservationMenu()
    {
        String[] returnStringArray = {  "Room Type",
                "Check In Date",
                "Check Out Date",
                "Amount of Rooms"
        };
        return returnStringArray;
    }

    // Method to generate the menu prompts for the new staff account
 /*   public static String[] newAccountStaffMenu()
    {
        String[] returnStringArray = {  "First Name: ",
                                        "Last Name: ",
                                        "Password: ",
                                        "Confirm Password: ",
                                        "Administrator ( Y or N ):"
                                    };

        return returnStringArray;
    }
*/
    public static String deleteReservationConfirmation()
    {
        return String.format( "Are you sure you want to delete your account ( Y or N )?%n>:");
    }

    public static String deleteReservationConfirmation( String idNumber )
    {
        return String.format( "Are you sure you want to delete account %s ( Y or N )?%n>:", idNumber );
    }

    public static String[][] adminDeleteReservationMenu()
    {
        String[] initialMenu = { newInitialMenu() };
        String[] reservationID = { "Enter ReservationID: " };
        String[] delete = { deleteReservationConfirmation() };

        String[][] returnString = {initialMenu,
                reservationID,
                delete
        };

        return returnString;
    }

}