/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.Scanner;

public class LoginApplication
{
    public static void main(String[] args)
    {
        String username;
        int password;
        boolean loginSuccess = false;
        Scanner console = new Scanner( System.in );

        do
        {
            try
            {
                System.out.print( generateHeader("staff") ); //............................... print header
                username = console.next(); //................................................. capture username
                System.out.print( "Password: " ); //.......................................... prompt for the password
                password = console.nextInt();
            }
            catch (Exception e)
            {
                System.out.println( "\nInvalid Username or password, try again\n\n" );
                Object temp = console.next(); //.............................................. captures the return character
            }

            // NEED TO CREATE ACCOUNT OBJECT AND VERIFY CREDENTIALS
            // IF ITS VALID SET LOGIN SUCCESS TO TRUE

        } while( !loginSuccess );

    }

    private static String generateHeader( String userOrStaff )
    {
        String returnString = String.format( "%s%n%s%n%s%n%n", "*********************", "* Hotel Reservation *", "*********************" );

        returnString += userOrStaff.equals("user") ? "User Login: " : "Staff Login: ";

        return returnString;
    }

}
