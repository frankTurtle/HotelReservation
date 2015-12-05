/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationApplication
{
    static Scanner console = new Scanner( System.in ); //.. gets input from keyboard
    static Account account = null; //...................... the account we will be using during all interactions

    public static void main( String[] args )
    {
        while( true )
        {
            //*********************** PRE LOGIN *******************
            if( account == null) //...................................................................................... if we've not logged into any account yet
            {
                switch (initialMenuChoice()) //.......................................................................... present the login interface
                {
                    case 1: //........................................................................................... logging in
                        loginPicked();
                        break;

                    case 2: //........................................................................................... create a new account
                        newAccountMenu(); //............................................................................. present new account interface
                        break;

                    case 3: //........................................................................................... user wants to exit
                        System.out.println("\nGoodbye!\n");
                        System.exit(0);
                }
            }

            //************************* POST LOGIN *********************
            if( account != null ) //..................................................................................... after we've logged in
            {
                switch (afterLoginMenuChoice(account)) //................................................................ present menu
                {
                    case 1: //........................................................................................... account management
                        afterLoginAccountManagement( account ); //....................................................... present account management interface
                        continue;

                    case 2: //........................................................................................... reservation management
                        break;

                    case 3: //........................................................................................... exit program
                        System.out.println("\nGoodBye!\n");
                        account = (Account)AccountListJDBC.logout(); //.................................................. sets account to null upon logout
                        break;
                }
            }
        }
    }

    // Method to determine which choice from the menu
    // after you login and choose account management
    private static int afterLoginAccountManagementChoice( Account person)
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                                     AccountManagementInterface.initialMenu( person.getAccountType() )}; //............ array to hold strings to display

        //todo update the numbers 1st is User, 2nd is Admin, 3rd is Staff
        int max = ( person.getAccountType().equals("U") ) ? 3 : ( person.getAccountType().equals("A") ) ? 3 : 3; //.... sets the max for response based on account questions

        return errorCheckWithinRange( displayThisText, 1, max );
    }

    // Method to display and choose from the account management interface
    private static void afterLoginAccountManagement( Account person )
    {
        int type = ( person.getAccountType().equals("U") ) ? 0 : ( person.getAccountType().equals("A") ) ? 1 : 2; //..... sets the type of person for the switch
        boolean stopLoop = false;

        while( !stopLoop )
        {
            switch( type )
            {
                case 0: //............. user
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        //todo FINISH ALL CASES
                        case 1: //..................................................... view my account
                            System.out.println( person );
                            break;

                        case 2: //..................................................... update my account
                            break;

                        case 3: //..................................................... delete my account
                            deleteAccount();
                            stopLoop = true;
                            break;

                        case 4: //..................................................... go to previous menu
                            break;
                    }
                    break;


                case 1: //............. admin
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        //todo FINISH ALL CASES
                        case 1: //..................................................... view my account
                            break;

                        case 2: //.................................................... view all accounts
                            break;

                        case 3: //..................................................... create new account
                            newAccountMenu();
                            break;

                        case 4: //..................................................... delete an account
                            break;

                        case 5: //..................................................... go to previous menu
                            break;
                    }
                    break;

                case 2: //............. staff
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        //todo FINISH ALL CASES
                        case 1: //..................................................... view my account
                            break;

                        case 2: //..................................................... view all accounts
                            break;

                        case 3: //..................................................... create new account
                            break;

                        case 4: //..................................................... go to previous menu
                            break;
                    }
                    break;
            }
        }
    }

    // Method to display a menu to display the menu after you've successfully logged in
    // returns what option you choose as well
    private static int afterLoginMenuChoice( Account person )
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                                     AfterLoginInterface.initialMenu( person.getAccountType() )}; //.................. array full of title and questions

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

    private static void loginPicked()
    {
        while (true)
        {
            account = login(); //........................................................................ gets account object to login to

            if (account != null) //...................................................................... if the account is valid
            {
                System.out.printf("Welcome %s %s%n%n", account.getFirstName(), account.getLastName()); //. welcome them
                break;
            }

            System.out.print("\nInvalid username or password, try again\n"); //.......................... the account is not valid
        }
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

    // Method to display a menu of choices when creating a new account
    // creats the account in the DB once determined
    private static void newAccountMenu()
    {
        switch( newAccountMenuChoice() )
        {
            case 1: //............................................................................................................. create a staff account
                if( account != null && account.getAccountType().equals("A") ) //................................................... make sure an admin is logged in
                {
                    String [] answers = newStaffAccountMenuAnswers(); //........................................................... get answers to staff creation questions
                    String username = String.format( "%s%s", answers[0].substring(0,1), answers[1]  ); //.......................... format the username
                    String adminOrNot =  answers[4].toLowerCase().equals("y") ? "A" : "SA"; //..................................... determines if the account being created is admin or not

                    StaffAccount addNewStaff = new StaffAccount( answers[0], answers[1], adminOrNot, username, answers[2], 0); //.. create the new staff object

                    AccountListJDBC.addStaffAccount( addNewStaff ); //............................................................. add staff object to DB
                    break;
                }
                else //............................................................................................................ not admin logged in
                    System.out.println( "\nSorry, you must be logged in as an administrator to do this.\n" );
                break;

            case 2: //............................................................................................................. create user account
                String [] answers = newUserAccountMenuAnswers(); //................................................................ get answers to user creation questions
                String username = String.format( "%s%s", answers[0].substring(0,1), answers[1]  ); //.............................. format username
                String street = answers[4] + " " + answers[5]; //.................................................................. combine house and street
                int zip = Integer.parseInt( answers[8] ); //....................................................................... parse integers for zip / phone
                int phone = Integer.parseInt( answers[10] );

                UserAccount addNewUser = new UserAccount( answers[0], answers[1], "U",  username,
                                                          answers[2], 0, street,  answers[6],
                                                          answers[7], zip, answers[9], phone
                                                        ); //...................................................................... create a new user account

                AccountListJDBC.addUserAccount( addNewUser ); //................................................................... add the user to the DB
                break;
        }
    }

    // Method to ask and return the answers in creating a new staff account
    public static String[] newStaffAccountMenuAnswers()
    {
        String[] answers = new String[ AccountManagementInterface.newAccountStaffMenu().length ]; //.. array the length of questions to store the answers

        System.out.println( generateHeader( "New Staff Account" ) ); //.............................. prints header

        for( int i = 0; i < answers.length; i++ ) //................................................. loop through each question
        {
            try
            {
                System.out.print( AccountManagementInterface.newAccountStaffMenu()[i] ); //.......... ask question
                answers[i] = console.next(); //...................................................... get answer

                if( i == 3 ) //...................................................................... check passwords match
                {
                    if( !answers[2].equals(answers[3]) )
                        throw new Exception( "Passwords do not match, try again\n" );
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());

                if( i == 3 ) //..................................................................... if passwords do not match
                    i -= 2; //...................................................................... decrement by 2 to go back to ask about password
                else
                {
                    Object chomp = console.next(); //............................................... captures new line
                    i --;
                }
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

                if( i == 3 )
                {
                    if( !answers[2].equals(answers[3]) )
                        throw new Exception( "Passwords do not match, try again\n" );
                }
            }
            catch ( InputMismatchException e )
            {
                System.out.println("Must enter an integer, try again\n");
                Object chomp = console.next();
                i --;
            }
            catch (Exception e)
            {
                System.out.println( e.getMessage() );
//                Object chomp = console.next();
                i -= 2;
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

    public static Account deleteAccount( )
    {
        Account deleteThisAccount = account;

        while( true )
        {
            try
            {
                System.out.print(AccountManagementInterface.deleteAccountConfirmation());
                String answer = console.next().toLowerCase();
                String type = deleteThisAccount.getAccountType();

                if (answer.equals("y") && type.equals("U"))
                {
                    AccountListJDBC.deleteUserAccount( deleteThisAccount.getId() );
                    account = null;
                    break;
                }
                else if (answer.equals("y") && (type.equals("SA") || type.equals("A")))
                {
                    AccountListJDBC.deleteStaffAccount( deleteThisAccount.getId() );
                    account = null;
                    break;
                }
                else if( answer.equals("n"))
                    break;
                else
                    throw new Exception("\nInvalid entry, try again\n\n");
            }
            catch (Exception e)
            {
                System.out.print( e.getMessage() );
            }
        }

        return deleteThisAccount;
    }
}
