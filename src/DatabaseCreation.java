// Class to create the Database in MySQL
// Written by Barret J. Nobel

import java.sql.*;

public class DatabaseCreation
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //... JDBC driver
    static final String DB_URL = "jdbc:mysql://localhost:3306/"; //. URL to locate the DB
    static final String DB_NAME = "HOTEL_RESERVATION"; //........... name for the DB
    static final String UPDATED_URL = DB_URL + DB_NAME; //.......... after the DB is created it adds the name to the full URL path

    static final String USER = "root"; //........................... database login credentials
    static final String PASS = "1234567890";

    public static void main(String[] args)
    {
        Connection conn = null; //............................................ connection to the DB
        Statement stmt = null; //............................................. statement to execute

        try
        {
            Class.forName(JDBC_DRIVER); //.................................... register the JDBC driver

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS); //........ initial connect to the DB

            stmt = conn.createStatement(); //................................. register the connection for the statement
            createDatabase( stmt, DB_NAME ); //............................... create the DB

            conn = DriverManager.getConnection( UPDATED_URL, USER, PASS ); //. establish the new connection to the hotel_reservation DB
            System.out.println("\n\nCreating tables...");
            createTables( conn ); //.......................................... create all the tables
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
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2)
            {
            }// nothing we can do
            try
            {
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }

        System.out.println("\n\n****Goodbye!****");
    }

    // Method to create the database
    // database name is hotel_reservation
    // takes in the statement and the string with the name for the DB to be created with
    private static void createDatabase( Statement statement, String dbName )
    {
        System.out.println("Creating database...");

        try
        {
            String sql = "CREATE DATABASE " + dbName; //...................... SQL statement to create the DB
            statement.executeUpdate(sql); //.................................. execute SQL statement
            System.out.println("Database created successfully!"); //.......... let user know it was successful
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

    // Method to create the tables and relationships between them
    // this method calls helper methods to create each individual table
    private static void createTables( Connection connection )
    {
        createAccountTables( connection ); //................... create the account table
    }

    // Method to create the account tables
    // creates Account ( super 'class' )
    // creates user account
    // creates staff account
    // creates admin account
    // creates staff member account
    private static void createAccountTables( Connection connection )
    {
        String createAccountTable = "CREATE TABLE Account " + //........................................... creates account table
                                    "(account_id INTEGER NOT NULL, " + //.................................. PK
                                    " account_type CHAR(2) NOT NULL, " + //................................ to determine if its a User or Staff account
                                    " CHECK(account_type IN('SA', 'U')), " + //............................ checks to make sure its a User or Staff account
                                    " UNIQUE(account_id, account_type), " + //............................. makes sure the id and account type are unique
                                    " PRIMARY KEY( account_id ), " + //.................................... sets the account_id as the PK
                                    " fname VARCHAR(255), " + //........................................... attribute for first name
                                    " lname VARCHAR(255), " + //........................................... attribute for last name
                                    " username VARCHAR(255), " + //........................................ attribute for username
                                    " password VARCHAR(255))"; //.......................................... attribute for password

        String createUserAccountTable = "CREATE TABLE User_Account " + //.................................. creates user_account table
                                        "(account_id INTEGER NOT NULL AUTO_INCREMENT, " + //............... PK
                                        " account_type CHAR(2) DEFAULT 'U' NOT NULL, " + //................ sets default account type to U
                                        " CHECK(account_type = 'U'), " + //................................ checks that the type is set to U
                                        " UNIQUE(account_id, account_type), " + //......................... makes sure the type and id are unique
                                        " PRIMARY KEY( account_id ), " + //................................ sets the PK
                                        " FOREIGN KEY(account_id, account_type)" + //...................... set the FKs
                                        " REFERENCES Account(account_id, account_type) " + //.............. sets the referencing tables
                                        " ON UPDATE CASCADE " + //......................................... command to update all values if updated
                                        " ON DELETE CASCADE, " + //........................................ command to update all values if deleted
                                        " street VARCHAR(255), " + //...................................... attribute for street
                                        " city VARCHAR(255), " + //........................................ attribute for city
                                        " state VARCHAR(255), " + //....................................... attribute for state
                                        " zip INTEGER, " + //.............................................. attribute for zip
                                        " email VARCHAR(255), " + //....................................... attribute for email
                                        " phone INTEGER)"; //.............................................. attribute for phone number

        String createStaffAccountTable = "CREATE TABLE Staff_Account " + //................................ creates staff_account table
                                         "(account_id INTEGER NOT NULL AUTO_INCREMENT, " + //.............. PK
                                         " account_type CHAR(2) DEFAULT 'SA' NOT NULL, " + //.............. sets default to staff account
                                         " CHECK(account_type IN('A', 'SM')), " + //....................... checks to see if the type is admin or staff member
                                         " UNIQUE(account_id, account_type), " + //........................ makes sure the id and type are unique
                                         " PRIMARY KEY( account_id ), " + //............................... sets PK
                                         " FOREIGN KEY(account_id, account_type)" + //..................... sets FKs
                                         " REFERENCES Account(account_id, account_type) " + //............. sets referencing tables
                                         " ON UPDATE CASCADE " + //........................................ command to update all values if updated
                                         " ON DELETE CASCADE )"; //........................................ command to update all values if deleted

        String createAdminAccountTable = "CREATE TABLE Admin " + //........................................ creates admin table
                                        "(account_id INTEGER NOT NULL AUTO_INCREMENT, " + //............... PK
                                        " account_type CHAR(2) DEFAULT 'A' NOT NULL, " + //................ sets default account type to A
                                        " CHECK(account_type = 'A'), " + //................................ checks the type is admin
                                        " UNIQUE(account_id, account_type), " + //......................... makes sure the id and type are unique
                                        " PRIMARY KEY( account_id ), " + //................................ sets PK
                                        " FOREIGN KEY(account_id, account_type)" + //...................... sets FKs
                                        " REFERENCES Staff_Account(account_id, account_type) " + //........ sets referencing tables
                                        " ON UPDATE CASCADE " + //......................................... command to update all values if updated
                                        " ON DELETE CASCADE )"; //......................................... command to update all values if deleted

        String createStaffMemberAccountTable = "CREATE TABLE Staff_Member " + //........................... creates staff_member table
                                               "(account_id INTEGER NOT NULL AUTO_INCREMENT, " + //........ PK
                                               " account_type CHAR(2) DEFAULT 'SM' NOT NULL, " + //........ sets default to staff member
                                               " CHECK(account_type = 'SM'), " + //........................ checks to see if the type is staff member
                                               " UNIQUE(account_id, account_type), " + //.................. makes sure the id and type are unique
                                               " PRIMARY KEY( account_id ), " + //......................... set PK
                                               " FOREIGN KEY(account_id, account_type)" + //................ sets FKs
                                               " REFERENCES Staff_Account(account_id, account_type) " + // . sets referencing tables
                                               " ON UPDATE CASCADE " + //................................... command to update all values if updated
                                               " ON DELETE CASCADE )"; //................................... command to update all values if deleted

        try
        {
            Statement statement = connection.createStatement(); //.......................................... create a new statement

            statement.executeUpdate( createAccountTable ); //............................................... execute the statement to create the account table
            System.out.println( "Account table created successfully!");

            statement.executeUpdate( createUserAccountTable ); //........................................... execute the statement to create the user account table
            System.out.println( "User account table created successfully!");

            statement.executeUpdate( createStaffAccountTable ); //.......................................... execute the statement to create the staff account table
            System.out.println( "Staff account table created successfully!");

            statement.executeUpdate( createAdminAccountTable ); //.......................................... execute the statement to create the admin account table
            System.out.println( "Admin account table created successfully!");

            statement.executeUpdate( createStaffMemberAccountTable ); //.................................... execute the statement to create the staff member table
            System.out.println( "Staff member account table created successfully!");
        }
        catch ( SQLException se )
        {
            se.printStackTrace();
        }
    }
}