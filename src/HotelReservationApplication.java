/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import sun.rmi.runtime.Log;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationApplication
{
    static Scanner console = new Scanner( System.in );

    public static void main( String[] args )
    {
        if( initialMenuChoice() == 1 ) //............. if the user wants to login
        {
            String[] accountCredentials = login();

            // TODO: 12/2/15 NEEDS TO VERIFY CREDENTIALS
        }
        else //....................................... user chose to create a new account
        {

        }

    }

    // Method to display and process the initial menu
    // returns an int
    // either 1: Login
    // or     2: create a new account
    private static int initialMenuChoice()
    {
        String[] displayThisText = { LoginInterface.generateHeader("Hotel Reservation"),
                                     LoginInterface.initialMenu()
                                    };

        return errorCheck( displayThisText );
    }

    // Method to display and process the login
    // returns an array with the credentials entered
    private static String[] login()
    {
        String[] returnString = new String[ LoginInterface.loginPrompt("").length ];
        String[] displayThisText = { LoginInterface.generateHeader("Login"),
                                    "Please Choose from the following: \n",
                                    "1.User login\n",
                                    "2.Staff login\n>:"
                                    };

        String answer = ( errorCheck(displayThisText) == 1 ) ? "user" : "staff";

        for( int i = 0; i < returnString.length; i++ )
        {
            System.out.print( LoginInterface.loginPrompt(answer)[i] );
            returnString[ i ] = console.next();
        }

        return returnString;
    }

//    private static int newAccountMenuChoice()
//    {
//        System.out.print( LoginInterface.newAccountInitialMenu() );
//    }

    private static int errorCheck( String[] displayText )
    {
        int choice = 0;

        do
        {
            for( String item: displayText )
                System.out.print( item );

            try
            {
                choice = console.nextInt();
                if( choice < 1 || choice > 2) throw new Exception("\nChoice must be between 1 and 2, try again!");
            }
            catch ( InputMismatchException e )
            {
                System.out.println("\nValue must be an integer, try again!");
                Object temp = console.next();
            }
            catch ( Exception e )
            {
                System.out.println( e.getMessage() );

            }
        }while ( choice != 1 && choice != 2 );

        return choice;
    }
}
