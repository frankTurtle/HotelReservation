/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationApplication
{
    static Scanner console = new Scanner( System.in );

    public static void main( String[] args )
    {
        if( initialMenuChoice() == 1 )
        {
            String[] accountCredentials = login();

            // TODO: 12/2/15 NEEDS TO VERIFY CREDENTIALS
        }

    }

    // Method to display and process the initial menu
    private static int initialMenuChoice()
    {
        int choice = 0;

        do
        {
            System.out.print( LoginInterface.generateHeader("Hotel Reservation") );
            System.out.print( LoginInterface.initialMenu() );

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

    // Method to display and process the login
    // returns an array with the credentials entered
    private static String[] login()
    {
        int choice = 0;
        String[] returnString = new String[ LoginInterface.loginPrompt("").length ];

        do
        {
            System.out.print( LoginInterface.generateHeader("Login") );
            System.out.print("Please Choose from the following: \n");
            System.out.print("1.User login\n");
            System.out.print("2.Staff login\n>:");

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

        System.out.print( LoginInterface.generateHeader("Login") );
        String answer = ( choice == 1 ) ? "user" : "staff";

        for( int i = 0; i < returnString.length; i++ )
        {
            System.out.print( LoginInterface.loginPrompt(answer)[i] );
            returnString[ i ] = console.next();
        }

        return returnString;
    }
}
