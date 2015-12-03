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
    Connection connection;
    Statement statement;

    AccountListJDBC()
    {
        try
        {
            Class.forName(JDBC_DRIVER); //.................................... register the JDBC driver

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS); //........ initial connect to the DB

            System.out.println("Creating statement...");
            statement = connection.createStatement();

            //TODO: get data from DB!

            String sql = "SELECT * FROM staff_member, admin, user_account ";
            ResultSet rs = statement.executeQuery(sql);
            while( rs.next() )
            {
                System.out.println( rs.toString() );
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
