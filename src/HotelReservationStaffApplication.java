/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationStaffApplication
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
                        break;

                    case 2: //........................................................................................... reservation management
                        afterLoginReservationManagement( account );
                        break;

                    case 3: //........................................................................................... exit program
                        System.out.println("\nGoodBye!\n");
                        if( AccountList.logout() ) account = null; //.................................................... sets account to null upon logout
                        break;
                }
            }
        }
    }

    // Method to determine which choice from the menu
    // after you login and choose account management
    private static int afterLoginAccountManagementChoice( Account person )
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                                     AccountManagementInterface.initialMenu( person.getAccountType() )}; //............ array to hold strings to display

        int max = ( person.getAccountType().equals("U") ) ? 4 : ( person.getAccountType().equals("A") ) ? 6 : 5; //.... sets the max for response based on account questions

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
                case 0: //............................................................. user
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        case 1: //..................................................... view my account
                            System.out.println( person );
                            break;

                        case 2: //..................................................... update my account
                            //TODO: Implement
                            break;

                        case 3: //..................................................... delete my account
                            deleteAccount( account );
                            stopLoop = true;
                            break;

                        case 4: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;


                case 1: //............................................................ admin
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        case 1: //..................................................... view my account
                            System.out.println( person );
                            break;

                        case 2: //..................................................... view all accounts
                            viewAllAccounts();
                            break;

                        case 3: //..................................................... view a single account
                            viewSingleAccount();
                            break;

                        case 4: //..................................................... create new account
                            newAccountMenu();
                            break;

                        case 5: //..................................................... delete an account
                            Account deleted = deleteAccountAdmin();
                            break;

                        case 6: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;

                case 2: //............. staff
                    switch( afterLoginAccountManagementChoice(person) )
                    {
                        case 1: //..................................................... view my account
                            System.out.println( person );
                            break;

                        case 2: //..................................................... view all accounts
                            viewAllAccounts();
                            break;

                        case 3: //..................................................... view a single account
                            viewSingleAccount();
                            break;

                        case 4: //..................................................... create new account
                            newAccountMenu();
                            break;

                        case 5: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;
            }
        }
    }

    // Method to print a single account object specified by ID
    private static void viewSingleAccount()
    {
        String id;

        switch( errorCheckWithinRange( new String[] {AccountManagementInterface.newAccountInitialMenu()} , 1, 3 ) )
        {
            case 1: //................................................................................................. staff account
                System.out.print( AccountManagementInterface.viewSingleAccountMenu() );
                id = console.next();

                if( AccountList.searchStaffAccount(id).getFirstName().equals("") )
                {
                    System.out.print( "\nInvalid ID\n" );
                    break;
                }

                System.out.println( AccountList.searchStaffAccount(id) );
                break;

            case 2: //................................................................................................. user account
                System.out.print( AccountManagementInterface.viewSingleAccountMenu() );
                id = console.next();

                if( AccountList.searchUserAccount(id).getFirstName().equals("") )
                {
                    System.out.print( "\nInvalid ID\n" );
                    break;
                }
                System.out.println( AccountList.searchUserAccount(id) );
                break;

            case 3: //................................................................................................. prevous menu
                break;
        }
    }

    // Method to print out all accounts in DB
    private static void viewAllAccounts()
    {
        for( Account account : AccountList.getAllAccounts() ) System.out.println( account );
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
        final int PASSWORD = 1; //.................................................................. just to be sure I access the array elements correctly in return statement

        String[] credentials = new String[ LoginInterface.loginPrompt("").length ];
        String[] displayThisText = LoginInterface.userOrStaffMenu(); //.............................. strings to pass in for prompts

        String answer = "staff"; //( errorCheckWithinRange(displayThisText, 1, 3) == 1 ) ? "user" : "staff"; //. label for the login prompt

        for( int i = 0; i < credentials.length; i++ ) //............................................. loop through the questions to get answers
        {
            System.out.print(LoginInterface.loginPrompt(answer)[i]); //.............................. display question
            try
            {
                credentials[i] = console.next(); //.................................................. get answer
                int testForInt = Integer.parseInt(credentials[0] );
            }
            catch ( NumberFormatException e )
            {
                System.out.println( "\nUserID must be an integer, try again!\n" );
                i--;
            }
        }

        return AccountList.staffLogin( credentials[USERNAME], credentials[PASSWORD] ); //... or call the staffLogin method
    }

    // Method to display welcome or error after user has typed in their credentials
    // calls login() method again if invalid
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

        return errorCheckWithinRange( displayThisText, 1, 3 ); //........................................ display prompts / get response / error check
    }

    // Method to display a menu of choices when creating a new account
    // creates the account in the DB once determined
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

                    AccountList.addStaffAccount( addNewStaff ); //............................................................. add staff object to DB
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

                AccountList.addUserAccount( addNewUser ); //................................................................... add the user to the DB
                break;

            case 3:
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
    private static int errorCheckWithinRange( String[] displayText, int low, int high )
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
        String[] answers = new String[ AccountManagementInterface.newAccountUserMenu().length ]; //................... creates answers array the same size as the questions to match size

        System.out.println( generateHeader( "New User Account" ) ); //................................................. prints header

        for( int i = 0; i < answers.length; i++ ) //................................................................... loop through each question
        {
            try
            {
                System.out.print( AccountManagementInterface.newAccountUserMenu()[i] ); //............................. print question
                answers[i] = ( i == 4 || i == 8 || i == 10) ? Integer.toString(console.nextInt()) : console.next(); //. some of the questions accept int's as the only response

                if( i == 3 ) //........................................................................................ third element is the second time asking for a password
                {
                    if( !answers[2].equals(answers[3]) ) //............................................................ if the passwords don't match
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

    // Method to delete an account from the DB
    // returns the account object it deletes
    public static Account deleteAccount( Account accountIn )
    {
        Account deleteThisAccount = accountIn;

        while( true ) //....................................................................... loop for error catching
        {
            if( deleteThisAccount.getFirstName().equals("") ) //............................... if its an account that doesnt exist
            {
                System.out.print( "\nInvalid ID\n" );
                break;
            }

            try
            {
                System.out.print(AccountManagementInterface.deleteAccountConfirmation()); //... confirm they want to delete the account
                String answer = console.next().toLowerCase(); //............................... get answer
                String type = deleteThisAccount.getAccountType(); //........................... get the account type to call appropriate delete method

                if (answer.equals("y") && type.equals("U")) //................................. if its a user
                {
                    AccountList.deleteUserAccount( deleteThisAccount.getId() );
                    break;
                }
                else if (answer.equals("y") && (type.equals("SA") || type.equals("A"))) //.... if its a staff member or admin
                {
                    AccountList.deleteStaffAccount( deleteThisAccount.getId() );
                    break;
                }
                else if( answer.equals("n") ) //.............................................. if they dont want to delete the account
                    break;
                else if( type.equals("SM") ) //............................................... a weird entry in the DB when it was an invalid ID
                {
                    System.out.println("\nInvalid ID\n");
                    break;
                }
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

    // Method to delete an account from an admin account
    // other options
    public static Account deleteAccountAdmin( )
    {
        String[][] questions = AccountManagementInterface.adminDeleteAccountMenu();
        int userOrStaff = 0;
        int accountID = 0;

        Account deleteThisAccount = null;

        for( int i = 0; i < questions.length; i++ )
        {
            switch( i )
            {
                case 0: //........................................................ first menu
                    userOrStaff = errorCheckWithinRange( questions[i], 1, 2 );
                    if( userOrStaff == 1 ) //..................................... pick to delete a staff account
                        i++;
                    break;

                case 1: //........................................................ delete user account
                    while( true )
                    {
                        try
                        {
                            System.out.print( questions[i][0] );
                            accountID = console.nextInt();
                            break;
                        }
                        catch( InputMismatchException e )
                        {
                            System.out.println( "Integer only, please try again\n");
                            Object chomp = console.next();
                        }
                        catch( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                    i++;
                    break;

                case 2: //........................................................ delete staff account
                    while( true )
                    {
                        try
                        {
                            System.out.print( questions[i][0] );
                            accountID = console.nextInt();
                            break;
                        }
                        catch( InputMismatchException e )
                        {
                            System.out.println( "Integer only, please try again\n");
                            Object chomp = console.next();
                        }
                        catch( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                    break;

                case 3: //......................................................... password
                    deleteThisAccount = ( userOrStaff == 1 ) ? AccountList.searchStaffAccount( "" + accountID ) : AccountList.searchUserAccount( "" + accountID );
                    deleteAccount( deleteThisAccount );
                    break;
            }
        }

        return deleteThisAccount;
    }

    private static int afterLoginReservationManagementChoice( Account person )
    {
        String[] displayThisText = { generateHeader( String .format("Welcome %s",person.getFirstName())),
                ReservationManagementInterface.initialMenu( person.getAccountType() )}; //............ array to hold strings to display

        int max = ( person.getAccountType().equals("U") ) ? 4 : ( person.getAccountType().equals("A") ) ? 5 : 4; //.... sets the max for response based on account questions

        return errorCheckWithinRange( displayThisText, 1, max );
    }

    private static void viewReservationByAccount( Account person)
    {
        int type = ( person.getAccountType().equals("U") ) ? 0 : ( person.getAccountType().equals("A") ) ? 1 : 2; //..... sets the type of person for the switch
        if (type ==0)
        {
            for( Reservation reservation : ReservationListJDBC.searchByAccount(person.getId()) ) System.out.println( reservation );
        }
        else
        {
            System.out.println("Please enter the ID of the user whose reservations you wish to view");
            int searchID = console.nextInt();
            for( Reservation reservation : ReservationListJDBC.searchByAccount(searchID) ) System.out.println( reservation );
        }
    }

    private static void viewAllReservations()
    {
        for( Reservation reservation : ReservationListJDBC.viewAllReservations()) System.out.println( reservation );
    }

    private static void afterLoginReservationManagement( Account person )
    {
        int type = ( person.getAccountType().equals("U") ) ? 0 : ( person.getAccountType().equals("A") ) ? 1 : 2; //..... sets the type of person for the switch
        boolean stopLoop = false;

        while( !stopLoop )
        {
            switch( type )
            {
                case 0: //............................................................. user
                    switch( afterLoginReservationManagementChoice(person) )
                    {
                        case 1: //..................................................... view my r9eservation

                            viewReservationByAccount(person);
                            break;

                        case 2: //..................................................... update my reservation

                            break;

                        case 3: //..................................................... create a reservation
                            newReservationMenu(person);
                            break;
                        case 4: //..................................................... create a reservation
                            deleteReservation(person);
                            stopLoop = true;
                            break;
                        case 5: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;


                case 1: //............................................................ admin
                    switch( afterLoginReservationManagementChoice(person) )
                    {
                        case 1: //..................................................... view a reservation
                            System.out.println( person );
                            break;

                        case 2: //..................................................... view a reservation
                            viewAllReservations();
                            break;

                        case 3: //..................................................... update a reservation

                            break;
                        case 4: //..................................................... create new reservation
                            newReservationMenu(person);
                            break;

                        case 5: //..................................................... delete a reservation
                            Reservation deleted = deleteReservation(person);
                            break;

                        case 6: //..................................................... check in a reservation
                            Reservation checkedIn = checkInReservation();
                            break;
                        case 7: //..................................................... check out a reservation
                            Reservation checkedOut = checkOutReservation();
                            break;
                        case 8: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;

                case 2: //............. staff
                    switch( afterLoginReservationManagementChoice(person) )
                    {
                        case 1: //..................................................... view a reservation
                            System.out.println( person );
                            break;

                        case 2: //..................................................... view a reservation
                            viewAllReservations();
                            break;

                        case 3: //..................................................... update a reservation

                            break;
                        case 4: //..................................................... create new reservation
                            newReservationMenu(person);
                            break;

                        case 5: //..................................................... delete a reservation
                            Reservation deleted = deleteReservation(person);
                            break;

                        case 6: //..................................................... check in a reservation
                            Reservation checkedIn = checkInReservation();
                            break;
                        case 7: //..................................................... check out a reservation
                            Reservation checkedOut =checkOutReservation();
                            break;
                        case 8: //..................................................... go to previous menu
                            stopLoop = true;
                            break;
                    }
                    break;
            }
        }
    }

    private static void newReservationMenu(Account person)
    {
        int type = ( person.getAccountType().equals("U") ) ? 0 : ( person.getAccountType().equals("A") ) ? 1 : 2; //..... sets the type of person for the switch

        if( account != null && type == 0) //................................................... make sure an admin is logged in
        {
            String [] answers = newReservationMenuAnswers(); //........................................................... get answers to staff creation questions
            int roomAmount = Integer.parseInt(answers[3]);
            Reservation addNewReservation = new Reservation( person.getId(), 0, 0, answers[1], answers[2], roomAmount, "Card", 0); //.. create the new staff object
            System.out.println(addNewReservation.toString());

            ReservationListJDBC.createReservation( addNewReservation ); //............................................................. add staff object to DB
        }
        else
        {
            System.out.println("Please enter the ID of the user who reservation will belong to");
            int newID = console.nextInt();
            String [] answers = newReservationMenuAnswers(); //........................................................... get answers to staff creation questions
            int roomAmount = Integer.parseInt(answers[3]);
            Reservation addNewReservation = new Reservation( newID, 0, 0, answers[1], answers[2], roomAmount, "Card", 0); //.. create the new staff object
            System.out.println(addNewReservation.toString());

            ReservationListJDBC.createReservation( addNewReservation ); //............................................................. add staff object to DB
        }
    }

    // Method to ask and return the answers in creating a new staff account
    public static String[] newReservationMenuAnswers()
    {
        String[] answers = new String[ ReservationManagementInterface.newReservationMenu().length ]; //.. array the length of questions to store the answers

        System.out.println( generateHeader( "New Reservation" ) ); //.............................. prints header

        for( int i = 0; i < answers.length; i++ ) //................................................. loop through each question
        {
            try
            {
                System.out.print( ReservationManagementInterface.newReservationMenu()[i] ); //.......... ask question
                answers[i] = console.next(); //...................................................... get answer
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());

                Object chomp = console.next(); //............................................... captures new line
                i --;

            }
        }

        return answers;
    }


    public static Reservation deleteReservation( Account person )
    {
        int type = ( person.getAccountType().equals("U") ) ? 0 : ( person.getAccountType().equals("A") ) ? 1 : 2; //..... sets the type of person for the switch

        Reservation deleteThisReservation = new Reservation();
        System.out.println("Enter the ID of the reservation you wish to delete");
        int reservationID = console.nextInt();
        deleteThisReservation = ReservationListJDBC.search(reservationID);


        while( true ) //....................................................................... loop for error catching
        {
            if( deleteThisReservation.getReservationId() == 0 ) //............................... if its an account that doesnt exist
            {
                System.out.print( "\nInvalid ID\n" );
                break;
            }

            try
            {
                System.out.print(ReservationManagementInterface.deleteReservationConfirmation()); //... confirm they want to delete the account
                String answer = console.next().toLowerCase(); //............................... get answer


                if (answer.equals("y")) //................................. if its a user
                {
                    ReservationListJDBC.deleteReservation( deleteThisReservation, person, type );
                    break;
                }
                else if( answer.equals("n") ) //.............................................. if they dont want to delete the account
                    break;
                else
                    throw new Exception("\nInvalid entry, try again\n\n");
            }
            catch (Exception e)
            {
                System.out.print( e.getMessage() );
            }
        }

        return deleteThisReservation;
    }

    public static Reservation checkInReservation()
    {
        Reservation R = new Reservation();
        System.out.println("Please input the ID of the reservation you want to check in.");
        int checkInID = console.nextInt();

        R = ReservationListJDBC.search(checkInID);
        ReservationListJDBC.checkIn(R);
        return R;
    }

    public static Reservation checkOutReservation()
    {
        Reservation R = new Reservation();
        System.out.println("Please input the ID of the reservation you want to check in.");
        int checkOutID = console.nextInt();

        R = ReservationListJDBC.search(checkOutID);
        ReservationListJDBC.checkOut(R);
        return R;
    }
}
