import java.util.Scanner;

/**
 * the RoomManagementInterface class
 * this class creates the interface where the user can input the data they want for certain actions in the room
 * such as addRoom.
 */
public class roomManagementInterface {


    /**
     * getAddRoomInput method
     * this method shows the user an interface where they can insert the data required to make a
     * room object to add to the hotel
     *
     * @return a room object which will be added to the hotel.
     */
    public Room GetAddRoomInput()
    {
        Scanner scans = new Scanner(System.in);

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

        return aRoom;
    }

    /** this method shows the an interface where they can view all the
     * rooms in the hotel
     */
    public static void userviewroomform()
    {
        roomList.viewRoom();
    }

    public static void getdeleteroominput()
    {
        Scanner in = new Scanner(System.in);
        int input=0;
        String new_ststuses;

        System.out.println("Please enter the room number for the room you wish to delete:");
        input = in.nextInt();

        roomList.deleteRoom(input);
    }
    /** this method shows an interface where the user can enter the
     * data needed tp update the status of a room
     * @return room
     */
    public static void getupdateroominput()
    {
        Scanner in = new Scanner(System.in);
        int input=0;
        String new_status;

        System.out.println("Please enter the room number for the room you wish to update:");
        input = in.nextInt();

        System.out.println("Please enter the status you wish for it to now have:");
        new_status = in.next();

        roomList.updateRoom(input, new_status);



    }

}