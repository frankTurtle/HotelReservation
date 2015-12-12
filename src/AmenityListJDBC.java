import java.sql.*;

/**this class allows the interaction between 
 * the amenitylist and the amenity database
 * @author Other
 *
 */
public class amenityList_JDBC {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "HOTEL_RESERVATION6";
    static final String UPDATED_URL = DB_URL + DB_NAME;

    static final String user = "root";
    static final String pass = "123456789";

    /**this method allows for the interaction between the
     * updateamenity method and the amenity database
     * @param input
     * @param new_status
     * @return
     */
    public static boolean updateamenity_JDBC(int input, String new_status)
    {
        boolean update = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String sql = "SELECT amenityid, status FROM Amenity_Table";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("AmenityID:				Status:");
            while(rs.next())
            {
                System.out.print(Amenity.getamenityid(rs) + "				");
                System.out.println(Amenity.getamenitystatus(rs));
            }rs.close();


            String query = "UPDATE amenity_table SET status = '"+ new_status + "' WHERE amenityid = " + input + ";";
            //"update Amenity_Table" + "set status = new_status" + "where amenityid = input" ;
            stmt = conn.createStatement();
            update=Amenity.setamenitystatus(input, new_status, stmt, query);

            conn.close();

        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se)
            {
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

        }
        return update;
    }

    /**this method allows for the interaction between the
     * viewamenity method and the amenity database
     * @param DatabaseCreation2
     * @param conn
     * @throws SQLException
     */
    public static void viewamenity_JDBC()
    {
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database");
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            System.out.println("Connected database successfully");

            System.out.println("Creating statement");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Amenity_Table";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("AmenityID:		Amenity Name:			Status:");
            while(rs.next())
            {
                System.out.print(Amenity.getamenityid(rs) + "				");
                System.out.print(Amenity.getamenityname(rs) + "				");
                System.out.println(Amenity.getamenitystatus(rs));
            }rs.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se)
            {
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }

        }
    }
}	