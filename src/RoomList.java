import java.util.*;
/**The RoomList class is a class that holds the collection of Rooms that we will have in the hotel class.*/
public class RoomList {

    /**this method allows the staff member to update the
     * status of a room
     * @param newRoom
     * @return
     */
    public static void updateRoom(int input, String new_status)
    {
        boolean update = false;
        Scanner in = new Scanner(System.in);

        RoomListJDBC.ViewRoom();

        System.out.println("Please enter the room number for the room you wish to update:");
        input = in.nextInt();

        System.out.println("Please enter the status you wish for it to now have:");
        new_status = in.next();

        update = RoomListJDBC.UpdateRoomJDBC(input, new_status);

        if(update == true)
            System.out.println("Update successful");
        else
            System.out.println("Not successful");
    }

    public static void deleteRoom(int input)
    {
        boolean delete = false;

        delete = RoomListJDBC.DeleteRoomJDBC(input);
    }

    /**this method allows the staff member to view the
     * room info
     */
    public static  void browseRoom()
    {
        RoomListJDBC.BrowseRoomJDBC();
    }


    /**
     * this addRoom method
     * this method adds a room to the current collection of rooms.
     * @param aRoom this room is the room to be added.
     * @return boolean telling us if it was succesful or not.
     */
    private static Room[] roomList;
    private static int index;
    public RoomList()
    {
    }
    /**
     * this addRoom method
     * this method adds a room to the current collection of rooms.
     * @param aRoom this room is the room to be added.
     * @return boolean telling us if it was succesful or not.
     */
    public static boolean addRoom(Room aRoom)
    {
        boolean update = false;

        System.out.println("In RoomList");

        //roomList[index] = aRoom;
        //index++;

        update = RoomListJDBC.AddRoomJDBC(aRoom);

        if(update == true)
            System.out.println("Update successful");
        else
            System.out.println("Not successful");

        return true;
    }
    /**
     * toString method
     * 	this tostring method returns the rooms in the hotel.
     * @return
     */
    public String toString()
    {
        return null;

    }
}