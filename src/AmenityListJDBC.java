import java.sql.*;

public class AmenityListJDBC {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/";
    static final String DB_NAME = "HOTEL_RESERVATION";
    static final String UPDATED_URL = DB_URL + DB_NAME;

    static final String user = "root";
    static final String pass = "1234567890";

    public static boolean UpdateAmenityJDBC(int input,char n_status )
    {
        boolean update = false;
        Connection conn = null;
        Statement stmt = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(UPDATED_URL, user, pass);
            stmt = conn.createStatement();

            String sql = "UPDATE amenity_table SET status = '"+ n_status + "' WHERE amenityid = " + input + ";";
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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

        return update=true;
    }
    public static void ViewAmenityJDBC()
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

            System.out.println("ID:\tName:\tStatus:");
            while(rs.next())
            {
                int id = rs.getInt("amenityId");
                String name = rs.getString("amenityname");
                String s = rs.getString("status");

                System.out.print(id + "\t");
                System.out.print(name + "\t");
                System.out.println(s);

				/*System.out.print(((Amenity) rs).getAmenityId() + "				");
				System.out.print(((Amenity) rs).getAmenityName() + "				");
				System.out.println(((Amenity) rs).getAmenityStatus());
			*/}rs.close();
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