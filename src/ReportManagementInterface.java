/**
 * Created by Barret J. Nobel on 12/14/2015.
 */
public class ReportManagementInterface
{
    public static String menu()
    {
        String returnString =  "Please choose from the following:\n";
               returnString += "1. Display Yearly report\n";
               returnString += "2. Display Monthly report\n";
               returnString += "3. Display Daily report\n";
               returnString += "4. Previous Menu\n:>";

        return returnString;
    }

}
