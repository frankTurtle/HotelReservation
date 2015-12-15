import java.util.*;
/** this is the class that interacts with the
 * allows changes to me made to the amenity table
 * @author Other
 *
 */
public class AmenityList {

    /**this is the class that allows the staff member to update
     * the status an amenity
     * @param amenityname
     * @param status
     * @return
     */
    public static boolean UpdateAmenity(int input, char status)
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;

        System.out.println("Please enter amenity id for amenity you wish to update:");
        input = in.nextInt();

        System.out.println("Please enter the status you wish for it to now have:");
        status = in.next().charAt(0);

        done =AmenityListJDBC.UpdateAmenityJDBC(input, status);

        return done;

    }

    /**this is the class that allows the user to view all the amnities
     * offered by the hotel
     */
    public static void ViewAmenity()
    {
        AmenityListJDBC.ViewAmenityJDBC();

    }

}