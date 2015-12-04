/**
 * Created by Barret J. Nobel on 12/4/2015.
 */
public class AfterLoginInterface
{
    public static String initialMenu( String accountType )
    {
        String returnString = "Please choose from the following:\n";
        int type = ( accountType.equals("U") ) ? ( accountType.equals("A") ) ? 3 : 0 : 2;

        switch( type )
        {
            case 3: //....... ADMIN
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;

            case 0: //........STAFF
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;

            case 2: //........ USER
                returnString += "1. Account Management\n";
                returnString += "2. Reservation Management\n";
                returnString += "3. Exit\n>:";
                break;
        }

        return returnString;
    }
}
