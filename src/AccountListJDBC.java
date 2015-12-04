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
    protected static Connection connection = connectToDatabase();
    protected static Statement statement = connectStatement();

    // Default Constructor
    // setup connection
    // loads accountArrayList with all Accounts from the DB
    AccountListJDBC()
    {
        accountArrayList = new ArrayList<>();

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

    protected static Connection connectToDatabase()
    {
        Connection returnConnection = null;

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

    protected static Statement connectStatement()
    {
        Statement returnStatement = null;
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

    public static void addStaffAccount( Account staff )
    {
        String insertInto = ( staff.getAccountType().equals("A") ) ? "admin" : "staff_account";
        String sqlStatement = String.format( "INSERT INTO %s " +
                                             "VALUES( %d, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\' )", insertInto, 0, staff.getAccountType(), staff.getFirstName(), staff.getLastName(), staff.getUsername(), staff.getPassword() );

        try
        {
            statement.executeUpdate( sqlStatement );
            System.out.print("Success");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static void addUserAccount( UserAccount user )
    {
        String sqlStatement = String.format( "INSERT INTO user_account " +
                                             "VALUES( %d, \'%s\', '%s', '%s', '%s', %d, '%s', %d, \'%s\', \'%s\', \'%s\', \'%s\' )", 0, user.getAccountType(), user.getStreet(), user.getCity(), user.getState(), user.getZipCode(), user.getEmailAddress(), user.getPhoneNumber(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword() );

        try
        {
            statement.executeUpdate( sqlStatement );
            System.out.print("Success");
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static void deleteStaffAccount( String staffID )
    {
        String sqlStatement = String.format("DELETE FROM staff_account WHERE account_id = %d", Integer.parseInt(staffID) );

        try
        {
            statement.executeUpdate( sqlStatement );
            System.out.print("Successfully deleted staffID: " + staffID);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static void deleteUserAccount( String userID )
    {
        String sqlStatement = String.format("DELETE FROM user_account WHERE account_id = %d", Integer.parseInt(userID) );

        try
        {
            statement.executeUpdate( sqlStatement );
            System.out.print("Successfully deleted staffID: " + userID);
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }

    public static StaffAccount searchStaffAccount( String staffID )
    {
        String sqlStatement = String.format( "SELECT * FROM staff_account WHERE account_id = %d", Integer.parseInt(staffID) );
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
        String sqlStatement = String.format( "SELECT * FROM user_account WHERE account_id = %d", Integer.parseInt(userID) );
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

//    public static boolean staffLogout()
//    {
//
//    }
//
//    public static  boolean userLogout()
//    {
//
//    }


    public static void main( String[] args )
    {
//        AccountListJDBC.addStaffAccount(new StaffAccount("barret", "nobel", "SA", "frank", "0234", 0) );
//        System.out.print(AccountListJDBC.searchStaffAccount( "3" ).getId());
//
//        if( staffLogin("7", "0234") != null )
//            System.out.print( staffLogin("3", "0234") );
//        else
//            System.out.print("NO!");

//        AccountListJDBC.addUserAccount( new UserAccount("bigb", "guuy", "U", "turtle", "1234", 80, "street", "city", "state", 1234, "email", 5151) );
        System.out.print(AccountListJDBC.searchUserAccount( "3" ).getId());

//        AccountListJDBC list = new AccountListJDBC();
//
//        list.addStaffAccount( new StaffAccount("staffFirst", "staffLast", "SA", "staffUsername", "0234", 0));
//        list.addStaffAccount( new StaffAccount("staffFirst", "staffLast", "A", "staffUsername", "0000", 0));
//        list.addUserAccount( new UserAccount("userFirst", "userLast", "U", "username", "password", 0, "street", "city", "state", 1234, "email", 12345));
////        list.deleteStaffAccount( "3" );
//        list.deleteUserAccount( "1" );
////        System.out.println( list.searchStaffAccount( "5" ) );
//        System.out.println( list.searchUserAccount( "5" ) );
    }
}
