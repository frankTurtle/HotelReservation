import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *this class allows the staff member to interact with a one room entity at a 
 * time
 * @author karee_000
 */
public class Room {


    private int roomId;
    private char status;
    private double price;
    private String description;
    private int capacity;
    private String roomType;

    public Room(int r, char s, double p, String d, int c,String t)
    {
        roomId = r;
        status = s;
        price = p;
        description = d;
        capacity = c;
        roomType = t;
    }
    public Room()
    {
        roomId = 0;
        status = ' ';
        price = 0.0;
        description = "";
        capacity = 0;
        roomType = "";
    }

    /** this class collects a single room number at a time from the 
     * room table
     * @param <ResultSet>
     * @return roomNum
     */
    public static int getRoomNumber(ResultSet rs) throws SQLException
    {
        int roomId = rs.getInt("room_id");

        return roomId;
    }

    public static boolean setRoomStatus(int input,String new_status, Statement stmt, String query) throws SQLException
    {
        int rs2 = 0;
        rs2 = stmt.executeUpdate(query);
        return true;

    }
    /** this class collects the guest entity from the room table
     * for a single room
     * @return guestname
     */
    public static double getRoomPrice(ResultSet rs) throws SQLException
    {
        double price = rs.getDouble("roomPrice");

        return price;
    }
    /** this class collects the status of the room from the 
     * room table
     * @return status
     */
    public static String getRoomDescription(ResultSet rs) throws SQLException
    {
        String description = rs.getString("description");

        return description;

    }

    public static int getRoomCapacity(ResultSet rs) throws SQLException
    {
        int capacity = rs.getInt("capacity");

        return capacity;

    }
    public static String getRoomStatus(ResultSet rs) throws SQLException
    {
        String roomStatus = rs.getString("Status");

        return roomStatus;

    }


    public void setPrice(double p)
    {
        price = p;
    }

    public void setRoomId(int r)
    {
        roomId = r;
    }
    public void setDescription(String s)
    {
        description = s;
    }

    public void setCapacity(int c)
    {
        capacity = c;
    }

    public void setRoomType(String t)
    {
        roomType = t;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public void setStatus(char c)
    {
        status = c;
    }
    public int getRoomId()
    {
        return roomId;
    }
    public char getStatus()
    {
        return status;
    }

    public double getRoomPrice()
    {
        return price;
    }

    public String getDescription()
    {
        return description;
    }

    public int getRoomCapacity()
    {
        return capacity;
    }
}