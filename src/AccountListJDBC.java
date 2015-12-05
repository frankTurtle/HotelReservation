/**
 * Created by Barret J. Nobel on 12/2/2015.
 */

import java.util.ArrayList;
import java.sql.*;

public class AccountListJDBC
{
    ArrayList< Account > accountArrayList; // = new ArrayList<>();

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //.................... JDBC driver
    static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_reservation"; //. URL to locate the DB
    static final String USER = "root"; //............................................ database login credentials
    static final String PASS = "1234567890";

    protected static Connection connection = connectToDatabase(); //................. connection to DB
    protected static Statement statement = connectStatement(); //.................... way to execute SQL

    // Default Constructor
    // setup connection
    // loads accountArrayList with all Accounts from the DB
    AccountListJDBC()
    {
        accountArrayList = new ArrayList();

        try
        {
            connection = connectToDatabase();
            statement = connection.createStatement();

            String[] sqlStrings = { "SELECT * FROM admin",
                                    "SELECT * FROM staff_account",
                                    "SELECT * FROM user_account"
                                    };

            for( String sqlCommand : sqlStrings )
            {
                ResultSet rs = statement.executeQuery( sqlCommand );

                while ( rs.next() )
                {
                    if( sqlCommand.equals("SELECT * FROM user_account") )
                        accountArrayList.add( new UserAccount(rs.getString("fname"), rs.getString("lname"), rs.getString("account_type"), rs.getString("username"), rs.getString("password"), rs.getInt("account_id"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("email"), rs.getInt("phone")) );
                    else
                        accountArrayList.add( new StaffAccount(rs.getString("fname"), rs.getString("lname"), rs.getString("account_type"), rs.getString("username"), rs.getString("password"), rs.getInt("account_id")) );
                }
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    // Helper Method to setup the connection to the DB
    // returns Connection object
    protected static Connection connectToDatabase()
    {
        Connection returnConnection = null; //............................................ connection object to return

        try
        {
            Class.forName(JDBC_DRIVER); //................................................. register the JDBC driver
            returnConnection =  DriverManager.getConnection(DB_URL, USER, PASS); //........ initial connect to the DB
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return returnConnection;
    }

    // Helper Method to hookup the statement object to the connection to the DB
    // returns Statement object
    protected static Statement connectStatement()
    {
        Statement returnStatement = null; //..................... statement object

        try
        {
            returnStatement = connection.createStatement();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return returnStatement;
    }

    protected static int getUserID( String accountType )
    {
        String sqlGetMaxID = String.format( " SELECT MAX(%s_account_id) FROM %s_account ", accountType, accountType);
        int returnID = 0;

        try
        {
            ResultSet rs = statement.executeQuery( sqlGetMaxID );
            while( rs.next() ) { returnID = rs.getInt(1) + 1; }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return returnID;
    }

    // Method to add a staff member to the DB
    public static void addStaffAccount( Account staff )
    {
        String sqlStatement = String.format( "INSERT INTO %s " +
                                             "VALUES( %d, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\' )", "staff_account", 0, staff.getAccountType(), staff.getFirstName(), staff.getLastName(), staff.getUsername(), staff.getPassword() );

        try
        {
            int userID = getUserID( "staff" );

            statement.executeUpdate( sqlStatement ); //................................................... RUN SQL CODE!
            System.out.printf( "%nSuccessfully added %s", staff.getUsername() );
            System.out.printf( "StaffID: %d%nPassword: %s%n", userID, staff.getPassword() );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    // Method to add a user to the DB
    public static void addUserAccount( UserAccount user )
    {
        String sqlStatement = String.format( "INSERT INTO user_account " +
                                             "VALUES( %d, \'%s\', '%s', '%s', '%s', %d, '%s', %d, \'%s\', \'%s\', \'%s\', \'%s\' )", 0, user.getAccountType(), user.getStreet(), user.getCity(), user.getState(), user.getZipCode(), user.getEmailAddress(), user.getPhoneNumber(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword() );

        try
        {
            int userID = getUserID( "user" );

            statement.executeUpdate( sqlStatement );
            System.out.printf( "%nSuccessfully added %s%n", user.getUsername() );
            System.out.printf( "UserID: %d%nPassword: %s%n", userID, user.getPassword() );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static void deleteStaffAccount( int staffID )
    {
//        String insertInto = ( searchStaffAccount(staffID).getAccountType().equals("A") ) ? "admin" : "staff_account" ;
//        int id = Integer.parseInt( staffID );
        String sqlStatement = String.format("DELETE FROM staff_account WHERE staff_account_id = %d", staffID );

        try
        {
            System.out.println( ( statement.executeUpdate( sqlStatement ) == 1 ) ? String.format("Successfully deleted StaffID: %d", staffID ) : String.format("Sorry, StaffID: %d is not found", staffID) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static void deleteUserAccount( int userID )
    {
//        int id = Integer.parseInt( userID );
        String sqlStatement = String.format("DELETE FROM user_account WHERE user_account_id = %d", userID );

        try
        {
            System.out.println( ( statement.executeUpdate( sqlStatement ) == 1 ) ? String.format("Successfully deleted UserID: %d", userID ) : String.format("Sorry, UserID: %d is not found", userID) );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static StaffAccount searchStaffAccount( String staffID )
    {
        String sqlStatement = String.format( "SELECT * FROM staff_account WHERE staff_account_id = %d", Integer.parseInt(staffID) );
        StaffAccount returnStaffMember = new StaffAccount();

        try
        {
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next())
            {
                returnStaffMember.setAccountType( rs.getString("account_type") );
                returnStaffMember.setFirstName( rs.getString("fname") );
                returnStaffMember.setLastName( rs.getString("lname") );
                returnStaffMember.setUsername( rs.getString("username") );
                returnStaffMember.setPassword( rs.getString("password") );
                returnStaffMember.setId( rs.getInt("staff_account_id") );
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return returnStaffMember;
    }

    public static UserAccount searchUserAccount( String userID )
    {
        String sqlStatement = String.format( "SELECT * FROM user_account WHERE user_account_id = %d", Integer.parseInt(userID) );
        UserAccount returnUserAccount = new UserAccount();

        try
        {
            ResultSet rs = statement.executeQuery(sqlStatement);
            while( rs.next() )
            {
                returnUserAccount.setAccountType( rs.getString("account_type") );
                returnUserAccount.setStreet( rs.getString("street") );
                returnUserAccount.setCity( rs.getString("city") );
                returnUserAccount.setState( rs.getString("state") );
                returnUserAccount.setZipCode( rs.getInt("zip") );
                returnUserAccount.setEmailAddress( rs.getString("email") );
                returnUserAccount.setPhoneNumber( rs.getInt("phone") );
                returnUserAccount.setFirstName( rs.getString("fname") );
                returnUserAccount.setLastName( rs.getString("lname") );
                returnUserAccount.setUsername( rs.getString("username") );
                returnUserAccount.setPassword( rs.getString("password") );
                returnUserAccount.setId( rs.getInt("user_account_id") );
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return returnUserAccount;
    }

//    public static void updateUserAccountByStaff()
//    {
//
//    }
//
//    public static void updateStaffAccount()
//    {
//
//    }
//
    public static StaffAccount staffLogin( String staffID , String password )
    {
        return ( searchStaffAccount(staffID).getPassword().equals(password) ) ? searchStaffAccount( staffID ) : null;
    }

    public static  UserAccount userLogin( String userID , String password )
    {
        return ( searchUserAccount(userID).getPassword().equals(password) ) ? searchUserAccount( userID ) : null;
    }

    public static Object logout() { return null; }

    public static void main( String[] args )
    {
//        AccountListJDBC.addStaffAccount(new StaffAccount("barret", "nobel3", "SA", "frank", "0234", 0) );
//        System.out.print(AccountListJDBC.searchStaffAccount( "5" ).getId());
//        AccountListJDBC.deleteStaffAccount("4");
//        AccountListJDBC.deleteUserAccount("10");

        if( staffLogin("4", "0234") != null )
            System.out.print( staffLogin("3", "0234") );
        else
            System.out.print("NO!");

//        AccountListJDBC.addUserAccount( new UserAccount("bigb", "guuy", "U", "turtle", "1234", 80, "street", "city", "state", 1234, "email", 5151) );
//        System.out.print(AccountListJDBC.searchUserAccount( "2" ).getId());
    }
}
