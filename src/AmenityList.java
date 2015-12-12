/** this is the class that interacts with the
 * allows changes to me made to the amenity table
 * @author Other
 *
 */
public class amenityList {

    /**this is the class that allows the staff member to update
     * the status an amenity
     * @param amenityname
     * @param status
     * @return
     */
    public static void updateamenity(int input, String status)
    {
        boolean update = false;

        update = amenityList_JDBC.updateamenity_JDBC(input, status);

        if(update == true)
            System.out.println("Update successful");
        else
            System.out.println("Not successful");

    }

    /**this is the class that allows the user to view all the amnities
     * offered by the hotel
     */
    public static void viewamenity()
    {
        amenityList_JDBC.viewamenity_JDBC();

    }

}