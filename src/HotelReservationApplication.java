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

            for( String item : userAccountAnswers )
                System.out.println( item );

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
                                    }; //................................................. strings to pass in in for prompts

        return errorCheckWithinRange( displayThisText, 1, 2 ); //.................................... display prompts / get response / error check
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
                                    }; //................................................. strings to pass in for prompts

        String answer = ( errorCheckWithinRange(displayThisText, 1, 2) == 1 ) ? "user" : "staff"; //. label for the login prompt

        for( int i = 0; i < returnString.length; i++ ) //................................. loop through the questions to get answers
        {
            System.out.print( LoginInterface.loginPrompt(answer)[i] ); //................. display question
            returnString[ i ] = console.next(); //........................................ get answer
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
                                    }; //..................................................... strings for prompts

        return errorCheckWithinRange( displayThisText, 1, 2 ); //........................................ display prompts / get response / error check

    }

    // Method to check for input errors
    // takes the low and high for range of values to test within
    private static int errorCheckWithinRange(String[] displayText, int low, int high )
    {
        int choice = 0; //................................................................................................ variable to return the answer with

        do
        {
            for( String item: displayText ) //............................................................................ loop through prompts
                System.out.print( item ); //.............................................................................. display them

            try
            {
                choice = console.nextInt(); //............................................................................ get input
                if( choice < low || choice > high )  //................................................................... test bounds
                    throw new Exception( String.format("%nChoice must be between %d and %d, try again!", low, high) ); //. throw error if not in bounds
            }
            catch ( InputMismatchException e ) //......................................................................... it its not an integer
            {
                System.out.println("\nValue must be an integer, try again!"); //.......................................... display message
                Object temp = console.next(); //.......................................................................... capture lingering input
            }
            catch ( Exception e ) //...................................................................................... generic exception
            {
                System.out.println( e.getMessage() ); //.................................................................. print message
            }
        }while ( choice != low && choice != high ); //.................................................................... loop while choice is outside bounds

        return choice;
    }

    // Method to display and process the new User Account creation
    // returns a String array with all the answers
    private static String[] newUserAccountMenu()
    {
        String[] answers = new String[ LoginInterface.newAccountUserMenu().length ];

        System.out.println( LoginInterface.generateHeader( "New User Account" ) ); //. prints header

        for( int i = 0; i < answers.length; i++ )
        {
            try
            {
                System.out.print( LoginInterface.newAccountUserMenu()[i] );
                answers[i] = ( i == 4 || i == 8 || i == 10) ? Integer.toString(console.nextInt()) : console.next();
            }
            catch ( InputMismatchException e )
            {
                System.out.println("Must enter an integer, try again\n");
                Object chomp = console.next();
                i --;
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
                Object chomp = console.next();
                i--;
            }
        }

        return answers;
    }
}
