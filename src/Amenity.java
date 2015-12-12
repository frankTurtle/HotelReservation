import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Amenity
{
    public static int getamenityid(ResultSet rs) throws SQLException
    {
        int amenityid = rs.getInt("amenityid");

        return amenityid;
    }
    public static String getamenityname(ResultSet rs) throws SQLException
    {
        String amenityName = rs.getString("amenityname");


        return amenityName;
    }

    public static String getamenitystatus(ResultSet rs) throws SQLException
    {
        String amenitystatus = rs.getString("status");

        return amenitystatus;
    }

    public static boolean setamenitystatus(int input,String new_status, Statement stmt, String query) throws SQLException
    {
        System.out.println("in amenity");
        int rs2 = 0;
        rs2 = stmt.executeUpdate(query);
        return true;

    }
}