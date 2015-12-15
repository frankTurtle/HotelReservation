/**this class allows the
 *
 * @author Other
 *
 */
public class AmenityManagementInterface {

    public static void browseamenityform()
    {
        AmenityList.ViewAmenity();
    }

    public static void getupdateamenityinput()
    {
        int input = 0;
        char new_status = 0;
        boolean update;

        update = AmenityList.UpdateAmenity(input, new_status);


        if(update == true)
            System.out.println("Update successful");
        else
            System.out.println("Not successful");
    }

}