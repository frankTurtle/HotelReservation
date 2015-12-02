import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

public class LoginInterface
{
    public static String initialMenu() {
        String returnString = generateHeader( "Hotel Reservation" );
        returnString += "Please choose from the following:\n";
        returnString += "1. Login\n";
        returnString += "2. Create a new Account\n>:";

        return returnString;
    }

    public static String loginPrompt( String userOrStaff )
    {
        String returnString = generateHeader( "Login" );
        returnString += userOrStaff.equals("user") ? "User Login: " : "Staff Login: ";

        return returnString;
    }

    public static String passwordPrompt()
    {
        return "Password: ";
    }

    public static String newAccountMenu()
    {
        String returnString = generateHeader( "New Account" );
        returnString += "Choose from the menu:\n";
        returnString += "1.Staff Account\n";
        returnString += "2.User Account\n>:";

        return returnString;
    }

    private static String generateHeader( String titleIn )
    {
        String stars = "*********************";
        String title = String.format( "*%-19s*", titleIn );
        return String.format("%n%s%n%s%n%s%n", stars, title, stars);
    }

    public static void main(String[] args)
    {

        System.out.print(loginPrompt("user") );
        System.out.println( passwordPrompt());
        System.out.print( newAccountMenu());
//        String username; //............................. variables
//        String password;
//        boolean loginSuccess = false;
//        Scanner console = new Scanner( System.in );
//
//        do
//        {
//            try
//            {
//                System.out.print( generateHeader("staff") ); //............................... print header
//                username = console.next(); //................................................. capture username
//                System.out.print( "Password: " ); //.......................................... prompt for the password
//                password = console.next();
//            }
//            catch (Exception e)
//            {
//                System.out.println( "\nInvalid Username or password, try again\n\n" );
//                Object temp = console.next(); //.............................................. captures the return character
//            }
//
//            // NEED TO CREATE ACCOUNT OBJECT AND VERIFY CREDENTIALS
//            // IF ITS VALID SET LOGIN SUCCESS TO TRUE
//
//        } while( !loginSuccess );

    }

//    // Method to generate the header for the login system
//    // takes 1 String argument
//    // determines if it will display Staff or User Login
//    private static String generateHeader( String userOrStaff )
//    {
//        String returnString = String.format( "%s%n%s%n%s%n%n", "*********************", "* Hotel Reservation *", "*********************" );
//
//        returnString += userOrStaff.equals("user") ? "User Login: " : "Staff Login: "; //...................................................... add more to the return string
//
//        return returnString;
//    }

}
