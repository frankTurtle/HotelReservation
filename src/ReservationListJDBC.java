/**The ReservationListJDBC class handles the connection and requests to the database in regards to the ReservationList.
 *
 *@author Joshua Williams - Group 1 Team 3
 */
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                    "VALUES ( " + R.getReservationId() + " , '" + R.getCheckInTime() + "' , '" + R.getCheckOutTime()+ "' , '" +
                    R.getPaymentMethod()+ "' , " + R.getAccountId()+ " , " +
                    10+ " , " + R.getRoomAmount()+ " , '" + R.getRoomId() + "' ) ");

            //			    String sqlStatement = String.format( "INSERT INTO %s " +
            //                      "VALUES( %d, \'%s\', \'%s\', \'%s\', \'%s\', \'%s\' )", "staff_account", 0, staff.getAccountType(), staff.getFirstName(), staff.getLastName(), staff.getUsername(), staff.getPassword() );

            //public Reservation (int accountId, int reservationId, int roomId, String checkInTime, String checkOutTime,
            //int roomAmount, String paymentMethod, int totalCost)
            executeUpdate(conn, createString);

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
                        rs.getString("roomNumber"),rs.getString("checkInTime"), rs.getString("checkOutTime"),
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
                result = new Reservation(rs.getInt("user_account_id"), rs.getInt("reservationID"),
                        rs.getString("roomNumber"),rs.getString("checkInTime"), rs.getString("checkOutTime"),
                        rs.getInt("roomAmount"), rs.getString("paymentMethod"), 0);
            }
        }
        catch( SQLException ex )
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println(result.toString());
        return result;
    }

    public static Reservation checkIn(Reservation R) {

        // Connect to MySQL
        Connection conn = run();
        String S = R.getRoomId();
        System.out.println(S);
        String roomIDs [] = S.split(","); //if the user enter 1123:2342 this method


        int roomNumbers [] = new int [roomIDs.length];
        String trimmed;
        for (int i = 0; i < roomIDs.length; i++)
        {
            trimmed = roomIDs[i].trim();
            roomNumbers[i] = Integer.parseInt(trimmed);
        }


        for (int i = 0; i < roomIDs.length; i++)
        {

            try {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


                String updateReservationString =
                        "UPDATE reservation " +
                                "SET checkInTime = '" + timeStamp +
                                "' WHERE reservationID = " + R.getReservationId();
                String updateRoomStatus =
                        "UPDATE room " +
                                "SET Status = 'U' " +
                                "WHERE room_id = " +roomNumbers[i];
                executeUpdate(conn, updateReservationString);
                executeUpdate(conn, updateRoomStatus);

            } catch (SQLException e) {
                System.out.println("ERROR: Could not check in the reservation");
                e.printStackTrace();
                return R;
            }
        }
        System.out.println("Reservation checked in.");
        return R;
    }


    public static Reservation checkOut(Reservation R) {

        // Connect to MySQL
        Connection conn = run();

        String S = R.getRoomId();
        String roomIDs [] = S.split(","); //if the user enter 1123:2342 this method


        int roomNumbers [] = new int [roomIDs.length];
        String trimmed;
        for (int i = 0; i < roomIDs.length; i++)
        {
            trimmed = roomIDs[i].trim();
            roomNumbers[i] = Integer.parseInt(trimmed);
        }

        for (int i = 0; i < roomIDs.length; i++)
        {
            try {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());


                String updateReservationString =
                        "UPDATE reservation " +
                                "SET checkOutTime =  '" + timeStamp +
                                "' WHERE reservationID = " + R.getReservationId();
                String updateRoomStatus =
                        "UPDATE room " +
                                "SET Status = 'A' " +
                                "WHERE room_id = " + roomNumbers[i];
                executeUpdate(conn, updateReservationString);
                executeUpdate(conn, updateRoomStatus);



            } catch (SQLException e) {
                System.out.println("ERROR: Could not check out the reservation");
                e.printStackTrace();

            }
        }
        System.out.println("Reservation checked out.");
        return R;



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
                        rs.getString("roomNumber"),rs.getString("checkInTime"), rs.getString("checkOutTime"),
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


    public static String getRoomID(String roomType, int roomAmount) {
        Statement stmt = null;
        // Connect to MySQL

        Connection conn = run();
        String result = "";
        // Create a table
        try {
            stmt = conn.createStatement();
            String createString = "SELECT * FROM room " +
                    "WHERE room_type = " + "'" + roomType +"' AND " +
                    "Status = 'A' ";

            int k = 0;
            System.out.println(createString);
            ResultSet rs = stmt.executeQuery(createString);



            for ( int i = 0; i < roomAmount; i++)
            {

                while (rs.next())
                {

                    if (k > 0)
                    {
                        result += ", ";
                        System.out.println(result);
                    }
                    result += rs.getString("room_id");
                    System.out.println(result);
                    k++;

                }
            }
            if (k < roomAmount)
            {

                return "";
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


    public static double calcuateCost(String S)
    {
        double cost = 0;

        Statement stmt = null;
        // Connect to MySQL


        String roomIDs [] = S.split(","); //if the user enter 1123:2342 this method


        int roomNumbers [] = new int [roomIDs.length];
        String trimmed;
        for (int i = 0; i < roomIDs.length; i++)
        {
            trimmed = roomIDs[i].trim();
            roomNumbers[i] = Integer.parseInt(trimmed);
        }


        Connection conn = run();
        String result = "";
        // Create a table
        for(int i =0; i < roomNumbers.length; i++)
        {
            try {
                stmt = conn.createStatement();
                String createString = "SELECT * FROM room " +
                        "WHERE room_id = " + roomNumbers[i];


                System.out.println(createString);
                ResultSet rs = stmt.executeQuery(createString);
                while (rs.next())
                {

                    cost += rs.getDouble("roomPrice");

                }
            }

            catch( SQLException ex )
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }
        return cost;
    }


    public static void update(int reservationID, String newInTime, String newOutTime, String newPayment,  int newAmount, String newRoomID ) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET checkInTime = '" +newInTime + "' , checkOutTime = '" + newOutTime+ "' , paymentMethod = '" +
                    newPayment+ "' " +
                    " , roomAmount = " + newAmount+ " , roomNumber = '" + newRoomID + "' " +
                    "WHERE reservationID = " + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }


    }
    public static void updateCheckInTime(int reservationID, String newInTime) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET checkInTime = '" +newInTime +
                    "' WHERE reservationID =" + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }


    }


    public static void updateCheckOutTime(int reservationID, String newOutTime) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET checkOutTime = '" +newOutTime +
                    "' WHERE reservationID =" + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }



    }

    public static void updatePaymentMethod(int reservationID, String newPayment) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET paymentMethod = '" +newPayment +
                    "' WHERE reservationID =" + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }


    }

    public static void updateRoomAmount(int reservationID, int newAmount) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET roomAmount = " +newAmount +
                    " WHERE reservationID =" + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }


    }

    public static void updateRoomNumber(int reservationID, String newRoomID) {

        // Connect to MySQL
        Connection conn = run();

        // Create a table
        try {

            String createString = String.format( "UPDATE reservation " +
                    "SET roomNumber = '" +newRoomID +
                    "' WHERE reservationID =" + reservationID );

            executeUpdate(conn, createString);

            System.out.println("Reservation updated");
        } catch (SQLException e) {
            System.out.println("ERROR: Could not update the reservation");
            e.printStackTrace();
            return;
        }


    }

    public static boolean reserveAmenity(int rId, int amenId) throws SQLException
    {
        try {
            Connection conn = run();
            String sql = "update Reservation set amenityid=" + amenId + "where reservationID=" + rId;
            executeUpdate(conn, sql);
            return true;
        }
        catch (SQLException e) {
            System.out.println("ERROR: Could not check out the reservation");
            e.printStackTrace();
            return false;
        }
    }

}

