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
        else if( newAccountMenuChoice() == 1 ) //..... user wants to create a new Staff account
        {
            // TODO: 12/2/15 HAVE USER LOGIN TO ADMIN ACCOUNT
        }
        else //....................................... creates a new User account
        {
            String [] userAccountAnswers = newUserAccountMenu();

            // TODO: 12/2/15 CREATE USER ACCOUNT WITH ANSWERS IN ARRAY
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

        return errorCheck( displayThisText, 1, 2 );
    }

    // Method to display and process the login
    // returns a String array with the credentials entered
    private static String[] login()
    {
        String[] returnString = new String[ LoginInterface.loginPrompt("").length ];
        String[] displayThisText = { LoginInterface.generateHeader("Login"),
                                    "Please Choose from the following: \n",
                                    "1.User login\n",
                                    "2.Staff login\n>:"
                                    };

        String answer = ( errorCheck(displayThisText, 1, 2) == 1 ) ? "user" : "staff";

        for( int i = 0; i < returnString.length; i++ )
        {
            System.out.print( LoginInterface.loginPrompt(answer)[i] );
            returnString[ i ] = console.next();
        }

        return returnString;
    }

    // Method to display and process the new account menu
    // returns an int
    // either 1: Staff
    // or     2: User
    private static int newAccountMenuChoice()
    {
        String[] displayThisText = { LoginInterface.generateHeader("New Account Creation"),
                                     LoginInterface.newAccountInitialMenu()
                                    };

        return errorCheck( displayThisText, 1, 2 );

    }

    // Method to check for input errors
    // takes the low and high for range of values to test within
    private static int errorCheck( String[] displayText, int low, int high )
    {
        int choice = 0;

        do
        {
            for( String item: displayText )
                System.out.print( item );

            try
            {
                choice = console.nextInt();
                if( choice < low || choice > high ) throw new Exception( String.format("%nChoice must be between %d and %d, try again!", low, high) );
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
        }while ( choice != low && choice != high );

        return choice;
    }

    // Method to display and process the new User Account creation
    // returns a String array with all the answers
    private static String[] newUserAccountMenu()
    {
        System.out.println( LoginInterface.generateHeader( "New User Account" ) );

        for( String item : LoginInterface.newAccountUserMenu() )
            System.out.println( item );

        return new String[1]; // TODO: 12/2/15 update return
    }
}
