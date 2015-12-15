import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RoomListJDBC {

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
    public static boolean UpdateRoomJDBC(int roomNum, String new_status)
    {
        boolean update = true;
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

            String query = "UPDATE RoomList SET Status = '"+ new_status + "' WHERE room_id = " + roomNum + ";";
            stmt = conn.createStatement();
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
        return update;
    }

    /**this method shows all the rooms in the
     * room database
     *
     */
    public static void ViewRoom()
    {
        //boolean update = false;
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

            String sql = "SELECT room_id, Status, description, room_type, roomPrice, capacity FROM roomList";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Number:\tStatus:\tType\tDescription\tPrice\tCapacity");
            while(rs.next())
            {
                System.out.print(rs.getInt("room_Id") + "\t");
                System.out.print(rs.getString("Status") + "\t");
                System.out.print(rs.getString("room_type") + "\t");
                System.out.print(rs.getString("description") + "\t\t");
                System.out.print(rs.getDouble("roomPrice") + "\t");
                System.out.println(rs.getInt("capacity") + "\t");
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
    public static void BrowseRoomJDBC()
    {
        //boolean update = false;
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

            String sql = "SELECT room_id, Status, description, room_type, roomPrice, capacity FROM roomList WHERE status = 'A'";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Number:\tStatus:\tType\tDescription\tPrice\tCapacity");
            while(rs.next())
            {
                System.out.print(rs.getInt("room_Id") + "\t");
                System.out.print(rs.getString("Status") + "\t");
                System.out.print(rs.getString("room_type") + "\t");
                System.out.print(rs.getString("description") + "\t\t");
                System.out.print(rs.getDouble("roomPrice") + "\t");
                System.out.println(rs.getInt("capacity") + "\t");
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
    public static boolean AddRoomJDBC(Room aRoom)
    {
        //boolean update = false;
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

            String sql = String.format("INSERT INTO roomlist " + "VALUES( %d, '%s', '%c', %f, '%s', %d )",  aRoom.getRoomId(), aRoom.getRoomType(), aRoom.getStatus(), aRoom.getRoomPrice(), aRoom.getDescription(),aRoom.getRoomCapacity() );

            stmt.executeUpdate(sql); //................................................... RUN SQL CODE!
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
    public static boolean DeleteRoomJDBC(int room_Id)
    {
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

            return true;

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
        return true;

    }

}