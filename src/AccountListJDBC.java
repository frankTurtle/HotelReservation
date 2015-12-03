/**
 * Created by Barret J. Nobel on 12/2/2015.
 */

import java.util.ArrayList;
import java.sql.*;

public class AccountListJDBC
{
    ArrayList< Account > accountArrayList; // = new ArrayList<>();

    final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //... JDBC driver
    final String DB_URL = "jdbc:mysql://localhost:3306/hotel_reservation"; //. URL to locate the DB
    final String USER = "root"; //........................... database login credentials
    final String PASS = "1234567890";
    Connection connection;
    Statement statement;

    AccountListJDBC()
    {
        accountArrayList = new ArrayList<>();

        try
        {
            Class.forName(JDBC_DRIVER); //.................................... register the JDBC driver

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //........ initial connect to the DB

            System.out.println("Creating statement...");
            statement = connection.createStatement();

            //TODO: get data from DB!

            String[] sqlStrings = {
                    "SELECT * FROM admin",
                    "SELECT * FROM staff_account",
                    "SELECT * FROM user_account"
            };


//            String sqlAdmin = "SELECT * FROM admin";
//            String sqlStaff = "SELECT * FROM staff_account";
//            String sqlUser  = "SELECT * FROM user_account;";

            for( String sqlCommand : sqlStrings )
            {
                ResultSet rs = statement.executeQuery( sqlCommand );

                while ( rs.next() )
                {
                    if( sqlCommand.equals("SELECT * FROM user_account") )
                        accountArrayList.add( new UserAccount(rs.getString("fname"), rs.getString("lname"), rs.getString("account_type"), rs.getString("password"), rs.getInt("account_id"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getInt("zip"), rs.getString("email"), rs.getInt("phone")) );
                    else
                        accountArrayList.add( new StaffAccount(rs.getString("fname"), rs.getString("lname"), rs.getString("account_type"), rs.getString("password"), rs.getInt("account_id")) );
                }

//                while( rs.next() )
            }
//            ResultSet rs = statement.executeQuery(sqlAdmin);
//            while( rs.next() )
//            {
//               accountArrayList.add( new StaffAccount(rs.getString("fname"), rs.getString("lname"), rs.getString("account_type"), rs.getString("password"), rs.getInt("account_id")) );
//            }

            for( Account item : accountArrayList )
                System.out.println(item);
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally //............................................................ closes resources
        {
            try
            {
                if(statement!=null)
                    statement.close();
            }
            catch(SQLException se2)
            {
            }// nothing we can do
            try
            {
                if(connection!=null)
                    connection.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }
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
