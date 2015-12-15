/**The ReservationListJDBC class handles the connection and requests to the database in regards to the ReservationList.
 *
 *@author Joshua Williams - Group 1 Team 3
 */
import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;

public class ReservationListJDBC {

    /** The name of the MySQL account to use (or empty for anonymous) */
    private static final String userName = "root";

    /** The password for the MySQL account (or empty for anonymous) */
    private static final String password = "1234567890";

    /** The name of the computer running MySQL */
    private static final String serverName = "localhost";

    /** The port of the MySQL server (default is 3306) */
    private static final int portNumber = 3306;

    /** The name of the database we are testing with (this default is installed with MySQL) */
    private static final String dbName = "hotel_reservation";

    /** The name of the table we are testing with */
    //private final String tableName = "JDBC_TEST";

    /**
     * Get a new database connection
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        conn = DriverManager.getConnection("jdbc:mysql://"
                        + serverName + ":" + portNumber + "/" + dbName,
                connectionProps);

        return conn;
    }

    /**
     * Run a SQL command which does not return a recordset:
     * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
     *
     * @throws SQLException If something goes wrong
     */
    public static boolean executeUpdate(Connection conn, String command) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(command); // This will throw a SQLException if it fails
            return true;
        } finally {

            // This will run whether we throw an exception or not
            if (stmt != null) { stmt.close(); }
        }
    }

    /**
     * Connect to MySQL and do some stuff.
     */
    public static Connection run() {

        // Connect to MySQL
        Connection conn = null;
        try {
            conn = getConnection();
            //System.out.println("Connected to database");
            return conn;
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
            return conn;
        }
    }
    public static void createReservation(Reservation R) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {
            System.out.println(getReservationID());
            String createString = String.format( "INSERT INTO reservation " +
                    "VALUES ( " + getReservationID() + " , '" + R.getCheckInTime() + "' , '" + R.getCheckOutTime()+ "' , '" +
                    R.getPaymentMethod()+ "' , " + R.getAccountId()+ " , " +
                    10+ " , " + R.getRoomAmount()+ " , " + 0 + " ) ");

//			    String sqlStatement = String.format( "INSERT INTO %s " +
            //                      "VALUES( %d, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\' )", "staff_account", 0, staff.getAccountType(), staff.getFirstName(), staff.getLastName(), staff.getUsername(), staff.getPassword() );

            //public Reservation (int accountId, int reservationId, int roomId, String checkInTime, String checkOutTime,
            //int roomAmount, String paymentMethod, int totalCost)
            executeUpdate(conn, createString);
            conn.close();
            System.out.println("Reservation created");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not create the reservation");
            e.printStackTrace();
            return;
        }


    }

    public static ArrayList<Reservation> searchByAccount(int accountID) {


        ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();

        Statement stmt = null;
        // Connect to MySQL
        Connection conn = run();
        // Create a table
        try {
            stmt = conn.createStatement();
            String createString = "SELECT * FROM reservation " +
                    "WHERE user_account_id = " + accountID;

            System.out.println(createString);
            ResultSet rs = stmt.executeQuery(createString);
            while (rs.next())
            {
                reservationArrayList.add( new Reservation(rs.getInt("user_account_id"), rs.getInt("reservationID"),
                        rs.getInt("roomNumber"),rs.getString("checkInTime"), rs.getString("checkOutTime"),
                        rs.getInt("roomAmount"), rs.getString("paymentMethod"), 0) );
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return reservationArrayList;
    }

    public static Reservation search(int reservationID) {
        Statement stmt = null;
        // Connect to MySQL
        Connection conn = run();
        Reservation result = new Reservation();
        // Create a table
        try {
            stmt = conn.createStatement();
            String createString = "SELECT * FROM reservation " +
                    "WHERE reservationID = " + reservationID;

            System.out.println(createString);
            ResultSet rs = stmt.executeQuery(createString);
            while (rs.next())
            {
                result.setReservationId( rs.getInt("reservationID") );
                result.setRoomId( rs.getInt("roomNumber") );
                result.setAccountId( rs.getInt("user_account_id") );
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return result;
    }

    public static Reservation checkIn(Reservation R) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {
            String updateReservationString =
                    "UPDATE reservation " +
                            "SET checkInTime = " + System.currentTimeMillis() +
                            " WHERE reservationID = " + R.getReservationId();
            String updateRoomStatus =
                    "UPDATE room " +
                            "SET Status = 'U' " +
                            "WHERE room_id = " + R.getRoomId();
            executeUpdate(conn, updateReservationString);
            executeUpdate(conn, updateRoomStatus);
            conn.close();
            System.out.println("Reservation checked in.");
            return R;
        } catch (SQLException e) {
            System.out.println("ERROR: Could not check in the reservation");
            e.printStackTrace();
            return R;
        }
    }


    public static Reservation checkOut(Reservation R) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {
            String updateReservationString =
                    "UPDATE reservation " +
                            "SET checkOutTime = " + System.currentTimeMillis() +
                            " WHERE reservationID = " + R.getReservationId();
            String updateRoomStatus =
                    "UPDATE room " +
                            "SET Status = 'A' " +
                            "WHERE room_id = " + R.getRoomId();
            executeUpdate(conn, updateReservationString);
            executeUpdate(conn, updateRoomStatus);
            conn.close();
            System.out.println("Reservation checked out.");
            return R;
        } catch (SQLException e) {
            System.out.println("ERROR: Could not check out the reservation");
            e.printStackTrace();
            return R;
        }





    }

    protected static int getReservationID()
    {

        Statement stmt = null;
        Connection conn = run();
        String sqlGetMaxID = String.format( " SELECT MAX(reservationID) FROM reservation ");
        int returnID = 0;

        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( sqlGetMaxID );
            while( rs.next() ) { returnID = rs.getInt(1) + 1; }
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        return returnID;
    }

    public static Reservation deleteReservation(Reservation R, Account person, int type) {

        // Connect to MySQL
        Connection conn = run();
        if (R.getAccountId() != person.getId() && type == 0)
        {
            System.out.println("This reservation does not belong to this account");
            return null;
        }
        else
        {
            // Create a table
            try {
                System.out.println(getReservationID());
                String createString = String.format( "DELETE FROM reservation " +
                        "WHERE  reservationID = " + R.getReservationId());
                executeUpdate(conn, createString);
                conn.close();
                System.out.println("Reservation deleted");
                return R;
            } catch (SQLException e) {
                System.out.println("ERROR: Could not delete the reservation");
                e.printStackTrace();
                return null;
            }
        }
    }

    public static ArrayList<Reservation> viewAllReservations() {


        ArrayList<Reservation> reservationArrayList = new ArrayList<Reservation>();

        Statement stmt = null;
        // Connect to MySQL
        Connection conn = run();
        // Create a table
        try {
            stmt = conn.createStatement();
            String createString = "SELECT * FROM reservation ";


            System.out.println(createString);
            ResultSet rs = stmt.executeQuery(createString);
            while (rs.next())
            {
                reservationArrayList.add( new Reservation(rs.getInt("user_account_id"), rs.getInt("reservationID"),
                        rs.getInt("roomNumber"),rs.getString("checkInTime"), rs.getString("checkOutTime"),
                        rs.getInt("roomAmount"), rs.getString("paymentMethod"), 0) );
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return reservationArrayList;
    }

		/*
		// Create a table
		try {
		    String createString =
			        "CREATE TABLE " + this.tableName + " ( " +
			        "ID INTEGER NOT NULL, " +
			        "NAME varchar(40) NOT NULL, " +
			        "STREET varchar(40) NOT NULL, " +
			        "CITY varchar(20) NOT NULL, " +
			        "STATE char(2) NOT NULL, " +
			        "ZIP char(5), " +
			        "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}

		// Drop the table
		try {
		    String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}
*/



    /**
     * Connect to the DB and do some stuff
     */

/*
public class ReservationListJDBC {
public Reservation[] reservationList = new Reservation[1] ;

	/**Requests to set check in time and set room status
	 * @param reservationID
	 */
//	public boolean checkIn(int reservationID)
//	{
//		return true;
///	}
    /** Requests to set check out time and set room status
     * @param reservationID
     */
//	public boolean checkOut(int reservationID)
//	{
//		return true;
//	}
    /**Method to request a cancellation of a reservation
     *
     * @param reservationID Reservation ID
     */
    //public Reservation	cancelReservation(int reservationID)
    //{

//	}


    /**Method to request the creation of a reservation
     *
     * @param reservationID Reservation ID
     * @param checkIn Check In Time
     * @param checkOut Check Out Time
     * @param roomNum Room Number
     * @param numGuests Number of Guests
     * @param userAcctNum User Account Number
     */
    public boolean	createReservation(int reservationID, java.lang.String checkIn, java.lang.String checkOut, int roomNum, int numGuests, int userAcctNum)
    {
        return true;
    }
    /** Method to request the update of a reservation
     *
     * @param reservationID Reservation ID
     * @param checkIn Check In Time
     * @param checkOut Check Out Time
     * @param roomNum Room Number
     * @param numGuests Number of Guests
     * @param userAcctNum User Account Number
     */
//	public boolean	updateReservation(int reservationID, java.lang.String checkIn, java.lang.String checkOut, int roomNum, int numGuests, int userAcctNum)
//	{
//		return true;
//	}
    /**Method to make a request to view a reservation's information
     *
     * @param reservationID Reservation ID
     */
//	public Reservation	viewReservation(int reservationID)
//	{
//		return Reservation;
//	}


    /**Returns value of reservation as a string
     */
//	public String toString()
//	{
//	}


}

