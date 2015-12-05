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
        //TODO: ask user for DB login credentials

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
        createReservationTable( connection );
        createAmenityTable( connection );
        createRoomTable( connection );
    }

    // Method to create the account tables
    // creates Account ( super 'class' )
    // creates user account
    // creates staff account
    // creates admin account
    // creates staff member account
    private static void createAccountTables( Connection connection )
    {
        String createUserAccountTable = "CREATE TABLE User_Account " + //.................................. creates user_account table
                                        "(user_account_id INTEGER NOT NULL AUTO_INCREMENT, " + //............... PK
                                        " account_type CHAR(2) NOT NULL, " + //................ sets default account type to U
                                        " UNIQUE( user_account_id ), " + //....................................... makes sure the type and id are unique
                                        " PRIMARY KEY( user_account_id ), " + //................................ sets the PK
                                        " street VARCHAR(255), " + //...................................... attribute for street
                                        " city VARCHAR(255), " + //........................................ attribute for city
                                        " state VARCHAR(255), " + //....................................... attribute for state
                                        " zip INTEGER, " + //.............................................. attribute for zip
                                        " email VARCHAR(255), " + //....................................... attribute for email
                                        " phone INTEGER," + //.............................................. attribute for phone number
                                        " fname VARCHAR(255), " + //........................................... attribute for first name
                                        " lname VARCHAR(255), " + //........................................... attribute for last name
                                        " username VARCHAR(255), " + //........................................ attribute for username
                                        " password VARCHAR(255))";


        String createStaffAccountTable = "CREATE TABLE Staff_Account " + //................................ creates staff_account table
                                         "(staff_account_id INTEGER NOT NULL AUTO_INCREMENT, " + //.............. PK
                                         " account_type CHAR(2) NOT NULL, " + //.............. sets default to staff account
                                         " UNIQUE( staff_account_id ), " + //...................................... makes sure the id and type are unique
                                         " PRIMARY KEY( staff_account_id ), " + // +;//............................... sets PK
                                         " fname VARCHAR(255), " + //........................................... attribute for first name
                                         " lname VARCHAR(255), " + //........................................... attribute for last name
                                         " username VARCHAR(255), " + //........................................ attribute for username
                                         " password VARCHAR(255))";

        try
        {
            Statement statement = connection.createStatement(); //.......................................... create a new statement

            statement.executeUpdate( createUserAccountTable ); //........................................... execute the statement to create the user account table
            System.out.println( "User account table created successfully!");

            statement.executeUpdate( createStaffAccountTable ); //.......................................... execute the statement to create the staff account table
            System.out.println( "Staff account table created successfully!");
        }
        catch ( SQLException se )
        {
            se.printStackTrace();
        }
    }

    private static void createReservationTable( Connection connection )
    {
        String createReservationTable = "CREATE TABLE Reservation" + //
                                        "(reservationID INTEGER NOT NULL, " + //Primary key
                                        "checkInTime CHAR(20), " + //check in time represented as a string
                                        "checkOutTime CHAR(20), " + //check out time represented as a string
                                        "paymentMethod CHAR(6), " + //"cash" or "credit"
                                        "user_account_id INTEGER NOT NULL, " + //Foreign key 1
                                        "staff_account_id INTEGER NOT NULL, "+ //Foreign key 2
                                        "roomAmount INTEGER, " + //number of rooms reserved
                                        "roomNumber INTEGER, " + //the physical room number
                                        "PRIMARY KEY (reservationID), " +
                                        "FOREIGN KEY ( user_account_id ) REFERENCES user_account( user_account_id ), " +
                                        "FOREIGN KEY ( staff_account_id ) REFERENCES staff_account( staff_account_id ) "+
                                        "ON UPDATE CASCADE ON DELETE CASCADE)";

        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate( createReservationTable );
            System.out.println( "Reservation table created successfully!");
        }
        catch ( SQLException se )
        {
            se.printStackTrace();
        }
    }

    private static void createAmenityTable( Connection connection )
    {
        String createamenityTable = "CREATE TABLE Amenity_Table" +
                                    "(amenityid INTEGER NOT NULL," +
                                    "PRIMARY KEY (amenityid)," +
                                    "amenityname VARCHAR(50), " +
                                    "status VARCHAR(50))";

        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate( createamenityTable );
            System.out.println( "Amenity table created successfully!");
        }
        catch ( SQLException se )
        {
            se.printStackTrace();
        }
    }

    private static void createRoomTable( Connection connection )
    {
        String createRoomTable = "CREATE TABLE Room " + //...................... creates RoomList table
                                 "(room_id INTEGER NOT NULL, " + //,,,,,,,,,,,,,,,,,,,,,,,,,............ PK
                                 " room_type VARCHAR(255) NOT NULL, " + //.............................. attribute for room type
                                 " Status char(2), " + //............................................... attrubute for status
                                 " UNIQUE(room_id), " + //............................. makes sure the id and account type are unique
                                 " PRIMARY KEY( room_id ), " + //....................................... sets the account_id as the PK
                                 " roomPrice float8, " + //............................................. attribute for roomprice
                                 " description VARCHAR(255), " + //..................................... attribute for description
                                 " capacity Integer)"; //........................................ attribute for capacity

        try
        {
            Statement statement = connection.createStatement();

            statement.executeUpdate( createRoomTable );
            System.out.println( "RoomList table created successfully!");
        }
        catch ( SQLException se )
        {
            se.printStackTrace();
        }
    }
}