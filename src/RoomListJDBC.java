import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class roomList_JDBC {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "HOTEL_RESERVATION6";
    static final String UPDATED_URL = DB_URL + DB_NAME;

    static final String user = "root";
    static final String pass = "123456789";
    /**this method updates the room entity in
     * database
     *
     * @param new_Room
     * @return
     */
    public static boolean updateRoom_JDBC(int roomNum, String new_status)
    {
        boolean update = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String sql = "SELECT room_id, Status FROM RoomList";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Room Number:				Status:");
            while(rs.next())
            {
                System.out.print(Room.getRoomNumber(rs) + "				");
                System.out.println(Room.getRoomStatus(rs));
            }rs.close();

            String query = "UPDATE RoomList SET Status = '"+ new_status + "' WHERE room_id = " + roomNum + ";";
            stmt = conn.createStatement();
            update=Room.setRoomStatus(roomNum, new_status, stmt, query);

            conn.close();

        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se)
            {
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

        }
        return update;
    }

    /**this method shows all the rooms in the
     * room database
     *
     */
    public static void viewRoom_JDBC()
    {
        boolean update = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String sql = "SELECT room_id, Status, description, roomPrice, capacity FROM roomList WHERE status = 'A'";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Room Number:				Status:");
            while(rs.next())
            {
                System.out.print(Room.getRoomNumber(rs) + "				");
                System.out.print(Room.getRoomStatus(rs)+"	");
                System.out.print(Room.getRoomDescription(rs)+"	");
                System.out.print(Room.getRoomPrice(rs)+"	");
                System.out.println(Room.getRoomCapacity(rs));
            }rs.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se)
            {
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

        }
    }

    /**
     * the AddRoom method
     * 	this method adds a room to the database.
     * @param aRoom the room to be added
     * @return Boolean a boolean that tells us if it was successful or not.
     */
    public static boolean addRoom(Room aRoom)
    {
        boolean update = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {

            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO roomlist " + "VALUES( %d, \'%s\', \'%c\', \'%f\', \'%s\', %d )",  aRoom.getRoomId(), aRoom.getRoomType(), aRoom.getStatus(), aRoom.getRoomPrice(), aRoom.getDescription(),aRoom.getRoomCapacity() );

            stmt.executeUpdate( sql ); //................................................... RUN SQL CODE!
            return true;
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        return true;

    }
    /**
     * the deleteRoom
     * this deletes a room in the database
     * @return room which is the room that was deleted
     */
    public static boolean deleteRoom(int room_Id)
    {
        boolean delete = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String query = "DELETE from RoomList WHERE room_id = " + room_Id + ";";
            stmt.executeUpdate(query);

            conn.close();

        }
        catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se)
            {
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

        }
        return delete;
    }

}