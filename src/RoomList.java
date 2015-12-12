/**The RoomList class is a class that holds the collection of Rooms that we will have in the hotel class.*/
public class roomList {

    /**this method allows the staff member to update the
     * status of a room
     * @param newRoom
     * @return
     */
    public static void updateRoom(int input, String new_status)
    {
        boolean update = false;

        update = roomList_JDBC.updateRoom_JDBC(input, new_status);

        if(update == true)
            System.out.println("Update successful");
        else
            System.out.println("Not successful");
    }

    public static void deleteRoom(int input)
    {
        boolean delete = false;

        delete = roomList_JDBC.deleteRoom(input);
    }

    /**this method allows the staff member to view the
     * room info
     */
    public static  void viewRoom()
    {
        roomList_JDBC.viewRoom_JDBC();
    }


    /**
     * this addRoom method
     * this method adds a room to the current collection of rooms.
     * @param aRoom this room is the room to be added.
     * @return boolean telling us if it was succesful or not.
     */
    private Room[] roomList;
    private int index;
    public roomList()
    {
    }
    /**
     * this addRoom method
     * this method adds a room to the current collection of rooms.
     * @param aRoom this room is the room to be added.
     * @return boolean telling us if it was succesful or not.
     */
    public boolean addRoom(Room aRoom)
    {
        roomList[index] = aRoom;
        index++;
        return roomList_JDBC.addRoom(aRoom);
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