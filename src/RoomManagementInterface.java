import java.util.Scanner;

/**
 * the RoomManagementInterface class
 * this class creates the interface where the user can input the data they want for certain actions in the room
 * such as addRoom.
 */
public class RoomManagementInterface {


    /**
     * getAddRoomInput method
     * this method shows the user an interface where they can insert the data required to make a
     * room object to add to the hotel
     *
     * @return a room object which will be added to the hotel.
     */
    public static void GetAddRoomInput()
    {
        Scanner scans = new Scanner(System.in);
        int Num = 0;

        Room aRoom = new Room();

        System.out.println("Input the room Number");
        aRoom.setRoomId(scans.nextInt());
        System.out.println("input the room Type");
        aRoom.setRoomType(scans.next());
        System.out.println("input the status for the room");
        aRoom.setStatus(scans.next().charAt(0));
        System.out.println("input the room price");
        aRoom.setPrice(scans.nextDouble());
        System.out.println("input the room descriptions");
        aRoom.setDescription(scans.next());
        System.out.println("input the capacity for the room");
        aRoom.setCapacity(scans.nextInt());
        scans.close();

        RoomList.addRoom(aRoom);

        //return aRoom;
    }

    /** this method shows the an interface where they can view all the
     * rooms in the hotel
     */
    public static void userviewroomform()
    {
        RoomList.browseRoom();
    }

    public static void getdeleteroominput()
    {
        Scanner in = new Scanner(System.in);
        int input=0;

        System.out.println("Please enter the room number for the room you wish to delete:");
        input = in.nextInt();

        RoomList.deleteRoom(input);
    }
    /** this method shows an interface where the user can enter the
     * data needed tp update the status of a room
     * @return room
     */
    public static void getupdateroominput()
    {
        int input=0;
        String new_status = null;

        RoomList.updateRoom(input, new_status);



    }

}