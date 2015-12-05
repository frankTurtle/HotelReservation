/**
 * Created by Barret J. Nobel on 12/4/2015.
 */
public class AfterLoginInterface
{
    // Method to display the initial menu
    // has three different options depending on who's logging in
    public static String initialMenu( String accountType )
    {
        String returnString = "Please choose from the following:\n";
        int type = ( accountType.equals("U") ) ? 2 : ( accountType.equals("A") ) ? 3 : 0; //... determins if its admin / user / staff

        switch( type ) //todo update all the questions for each type of account
        {
            case 3: //......................................................................... ADMIN
                returnString += "ADMIN MENU\n";
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;

            case 0: //........................................................................ STAFF
                returnString += "STAFF MENU\n";
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;

            case 2: //......................................................................... USER
                returnString += "USER MENU\n";
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;
        }

        return returnString;
    }
}
