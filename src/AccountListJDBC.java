/**
 * Created by Barret J. Nobel on 12/2/2015.
 */

import java.util.ArrayList;
import java.sql.*;

public class AccountListJDBC
{
    ArrayList< Account > accountArrayList; // = new ArrayList<>();

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //... JDBC driver
    static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_reservation"; //. URL to locate the DB
    static final String USER = "root"; //........................... database login credentials
    static final String PASS = "1234567890";
//    Connection connection;

    // Default Constructor
    // sets up connection
    // loads accountArrayList with all Accounts from the DB
    AccountListJDBC()
    {
        accountArrayList = new ArrayList<>();

        try
        {
            Statement statement = connectToDatabase().createStatement();

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
//            for( Account item : accountArrayList )
//                System.out.println(item);
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

    private static Connection connectToDatabase()
    {
        Connection returnConnection = null;

        try
        {
            Class.forName(JDBC_DRIVER); //....................................................................... register the JDBC driver
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

//    public static void addStaffAccount( StaffAccount staff )
//    {
//
//    }
//
//    public static void addUserAccount( UserAccount user )
//    {
//
//    }
//
//    public static StaffAccount deleteStaffAccount( String staffID )
//    {
//
//    }
//
//    public static UserAccount deleteUserAccount( String userID )
//    {
//
//    }
//
//    public StaffAccount searchStaffAccount( String staffID )
//    {
//
//    }
//
//    public UserAccount searchUserAccoount( String userID )
//    {
//
//    }
//
//    public static void updateStaffAccount()
//    {
//
//    }
//
//    public static void updateUserAccount()
//    {
//
//    }


    public static void main( String[] args )
    {
        AccountListJDBC list = new AccountListJDBC();
    }
}
