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
    public String toString()
    {
        return "Room Number: " + roomId + " - Room Status: " + status + " - Room Price: " +
                price + " - Room Description: " + description + " - Room Capacity: " + capacity +
                " - Room Type: " + roomType + "\n";
    }
}