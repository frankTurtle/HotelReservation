import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
/**this class allows the 
 *
 * @author Other
 *
 */
public class amenityManagmentInterface {

    public static void browseamenityform() throws SQLException
    {
        amenityList.viewamenity();
    }

    public static void getupdateamenityinput() throws SQLException
    {
        Scanner in = new Scanner(System.in);
        int input = 0;
        String new_status;

        System.out.println("Please enter amenity id for amenity you wish to update:");
        input = in.nextInt();

        System.out.println("Please enter the status you wish for it to now have:");
        new_status = in.next();

        amenityList.updateamenity(input, new_status);


    }

}