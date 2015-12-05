/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationApplication
{
    static Scanner console = new Scanner( System.in );
    static Account account = null;

    public static void main( String[] args )
    {
        while( true )
        {
            //*********************** PRE LOGIN *******************
            if( account == null)
            {
                switch (initialMenuChoice())
                {
                    case 1:
                        while (true)
                        {
                            account = login();

                            if (account != null)
                            {
                                System.out.printf("Welcome %s %s%n%n", account.getFirstName(), account.getLastName());
                                break;
                            }

                            System.out.print("\nInvalid username or password, try again\n");
                        }
                        break;

                    case 2:
                        newAccountMenu();
                        break;

                    case 3:
                        System.out.println("\nGoodbye!\n");
                        System.exit(0);
                }
            }

            //************************* POST LOGIN *********************
            if( account != null )
            {
                switch (afterLoginMenuChoice(account)) {
                    case 1:
                        afterLoginAccountManagement( account );
                        continue;

                    case 2:
                        break;

                    case 3:
                        System.out.println("\nGoodBye!\n");
                        System.exit(0);
                        break;
                }
            }
        }


//        if( initialMenuChoice() == 1 ) //............. if the user wants to login
//        {
//            while(true)
//            {
//                if (login() != null)
//                {
//                    System.out.print("Display welcome interface");
//                    break;
//                }
//                else
//                    System.out.print("\nInvalid username or password, try again\n");
//            }
//        }
//        else if( newAccountMenuChoice() == 1 ) //..... user wants to create a new Staff account
//        {
//            // TODO: 12/2/15 HAVE USER LOGIN TO ADMIN ACCOUNT
//        }
//        else //....................................... creates a new User account
//        {
//            String [] userAccountAnswers = newUserAccountMenuAnswers();
//
//            for( String item : userAccountAnswers )
//                System.out.println( item );
//
//            // TODO: 12/2/15 CREATE USER ACCOUNT WITH ANSWERS IN ARRAY
//        }
    }

    private static int afterLoginAccountManagementChoice( Account person)
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                                     AccountManagementInterface.initialMenu( person.getAccountType() )};

        //todo update the numbers 1st is User, 2nd is Admin, 3rd is Staff
        int max = ( person.getAccountType().equals("U") ) ? 3 : ( person.getAccountType().equals("A") ) ? 3 : 3; //.... sets the max for response based on account questions

        return errorCheckWithinRange( displayThisText, 1, max );
    }

    private static void afterLoginAccountManagement( Account person )
    {
        switch( afterLoginAccountManagementChoice(person) )
        {
            case 3: //..................................... create new account
                newAccountMenu();
                break;

        }
    }

    private static int afterLoginMenuChoice( Account person )
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                                     AfterLoginInterface.initialMenu( person.getAccountType() )};

        //todo update the numbers 1st is User, 2nd is Admin, 3rd is Staff
        int max = ( person.getAccountType().equals("U") ) ? 3 : ( person.getAccountType().equals("A") ) ? 3 : 3; //.... sets the max for response based on account questions

        return errorCheckWithinRange( displayThisText, 1, max );
    }

    // Method to display and process the initial menu
    // returns an int
    // either 1: Login
    // or     2: create a new account
    private static int initialMenuChoice()
    {
        String[] displayThisText = { generateHeader("Hotel Reservation"),
                                     LoginInterface.initialMenu()
                                    }; //................................................. strings to pass in in for prompts

        return errorCheckWithinRange( displayThisText, 1, 3 ); //.................................... display prompts / get response / error check
    }

    // Method to display and process the login
    // returns boolean if it logged in or not
    private static Account login()
    {
        final int USERNAME = 0;
        final int PASSWORD = 1;

        String[] credentials = new String[ LoginInterface.loginPrompt("").length ];
        String[] displayThisText = LoginInterface.userOrStaffMenu(); //................................................. strings to pass in for prompts

        String answer = ( errorCheckWithinRange(displayThisText, 1, 2) == 1 ) ? "user" : "staff"; //. label for the login prompt

        for( int i = 0; i < credentials.length; i++ ) //................................. loop through the questions to get answers
        {
            System.out.print(LoginInterface.loginPrompt(answer)[i]); //................. display question
            try
            {
                credentials[i] = console.next(); //......................................... get answer
                int testForInt = Integer.parseInt(credentials[0] );
            }
            catch ( NumberFormatException e )
            {
                System.out.println( "\nUserID must be an integer, try again!\n" );
                i--;
            }
        }

        if( answer.equals("user") )
        {
            return AccountListJDBC.userLogin(credentials[USERNAME], credentials[PASSWORD]);
        }
        else
            return AccountListJDBC.staffLogin( credentials[USERNAME], credentials[PASSWORD] );
    }

    // Method to display and process the new account menu
    // returns an int
    // either 1: Staff
    // or     2: User
    private static int newAccountMenuChoice()
    {
        String[] displayThisText = { generateHeader("New Account Creation"),
                                     AccountManagementInterface.newAccountInitialMenu()
                                    }; //..................................................... strings for prompts

        return errorCheckWithinRange( displayThisText, 1, 2 ); //........................................ display prompts / get response / error check
    }

    private static void newAccountMenu()
    {
        switch( newAccountMenuChoice() )
        {
            case 1:
                if( account != null && account.getAccountType().equals("A") )
                {
                    String [] answers = newStaffAccountMenuAnswers();
                    String username = String.format( "%s%s", answers[0].substring(0,1), answers[1]  );
                    String adminOrNot =  answers[4].toLowerCase().equals("y") ? "A" : "SA";

                    System.out.print(adminOrNot);

                    StaffAccount addNewStaff = new StaffAccount( answers[0], answers[1], adminOrNot, username, answers[2], 0);

                    AccountListJDBC.addStaffAccount( addNewStaff );
                    break;
                }
                else
                    System.out.println( "\nSorry, you must be logged in as an administrator to do this.\n" );
                break;

            case 2:
                String [] answers = newUserAccountMenuAnswers();
                String username = String.format( "%s%s", answers[0].substring(0,1), answers[1]  );
                String street = answers[4] + " " + answers[5];
                int zip = Integer.parseInt( answers[8] );
                int phone = Integer.parseInt( answers[10] );

                UserAccount addNewUser = new UserAccount( answers[0], answers[1], "U",  username,
                                                          answers[2], 0, street,  answers[6],
                                                          answers[7], zip, answers[9], phone
                                                        );

                AccountListJDBC.addUserAccount( addNewUser );
                break;
        }
    }

    public static String[] newStaffAccountMenuAnswers()
    {
        String[] answers = new String[ AccountManagementInterface.newAccountStaffMenu().length ];

        System.out.println( generateHeader( "New Staff Account" ) ); //. prints header

        for( int i = 0; i < answers.length; i++ )
        {
            try
            {
                System.out.print( AccountManagementInterface.newAccountStaffMenu()[i] );
                answers[i] = console.next();

                if( i == answers.length )
                    System.out.print("here");
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
                choice = 0;
            }
            catch ( Exception e ) //...................................................................................... generic exception
            {
                System.out.println( e.getMessage() ); //.................................................................. print message
            }
        }while ( choice < low || choice > high ); //.................................................................... loop while choice is outside bounds

        return choice;
    }

    // Method to display and process the new User Account creation
    // returns a String array with all the answers
    private static String[] newUserAccountMenuAnswers()
    {
        String[] answers = new String[ AccountManagementInterface.newAccountUserMenu().length ];

        System.out.println( generateHeader( "New User Account" ) ); //. prints header

        for( int i = 0; i < answers.length; i++ )
        {
            try
            {
                System.out.print( AccountManagementInterface.newAccountUserMenu()[i] );
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

    // Method to generate a header with test passed in
    public static String generateHeader( String titleIn )
    {
        String stars = "************************";
        String title = String.format( "* %-20s *", titleIn );
        return String.format("%n%s%n%s%n%s%n", stars, title, stars);
    }
}
