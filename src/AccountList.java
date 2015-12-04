/**
 * The AccountsListAccount.java \
 * @author Amarjit Singh, Barret Nobel , Jay Kumar
 */
public class AccountList
{
//    /**
//     *
//     * @param user
//     */
//    public static void addUserAccount(UserAccount user )
//    {
//        AccountsList JDBC.addUserAccountJD(user);
//
//    }// end addUserAccount
//
//    /***************************************************************************
//     * this method adds the Staff Account to the DB
//     * @param staff instance of the StaffAccount class
//     */
//    public static void AddStaffAccount (StaffAccount staff)
//    {
//        AccountsListJDBC.AddStaffAccountJD(staff);
//    }//end AddStaffAccount
//
//
//
//    /***************************************************************************
//     *
//     * @param userId
//     * @param passwd
//     * @param FName
//     * @param LName
//     * @param Address
//     * @param City
//     * @param State
//     * @param ZipCode
//     * @param PhoneNo
//     * @param email
//     */
//    public static  void updateAccountByStaff(String userId , String passwd,
//                                             String FName , String LName , String Address ,String City ,
//                                             String State , String ZipCode ,String PhoneNo , String email)
//    {
//
//        AccountsList JDBC.updateUserAccountByStaffJD( userId, passwd , FName , LName ,
//            passwd , Address , State , ZipCode , PhoneNo, email);
//
//    }//end updateAccountByUser
//
//    /***************************************************************************
//     * updateStaffAccount allows the staff to update the staff information
//     * @param staffId  String type
//     * @param passwd   String type
//     * @param FName    String type
//     * @param LName    String type
//     */
//    public static void updateStaffAccount (String staffId ,String passwd, String FName , String LName)
//    {
//
//        AccountsListJDBC.updateStaffAccount(staffId , passwd ,  FName, LName);
//
//    }// end updateStaffAccount
//
//
//    /**
//     * searchUserAccount searches the user account by the userID
//     * or the LName
//     * @param userID String
//     * @return instance of UserAccount Class
//     */
//    public static UserAccount searchUserAccount ( String userID)
//    {
//        UserAccount user = AccountsListJDBC.searchUserAccountJD(userID);
//        return user;
//    }
//    /*******************************************************************************
//     * searchStaffAccount searches the user account by the staffID
//     * or the LName
//     * @param staffID String
//     * @return instance of StaffAccount class
//     */
//    public static StaffAccount searchStaffAccount ( String staffID)
//    {
//
//        StaffAccount staff = AccountsListJDBC.searchStaffAccount(staffID);
//
//        return staff;
//    }// end searchStaffAccount
//
//    /***************************************************************************
//     * deleteUserAccount takes the desired userID and deletes it from the database
//     *
//     * @param userID
//     */
//    public static void deleteUserAccount(String userID)
//    {
//        AccountsListJDBC.deleteUserAccountJD(userID);
//    }//end deleteUserAccount
//
//
//    /***************************************************************************
//     *deleteStaffAccount takes the desired staffID and deletes it from the database
//     * it connects to DB via the AccountsManagementJDBC methods.
//     * @param staffID
//     */
//    public static void deleteStaffAccount(String staffID)
//    {
//        AccountsListJDBC.deleteStaffAccountJD(staffID);
//    }//end deleteStaffAccount
//
//
//    /***************************************************************************
//     * staffLogin functions allows the staff to login to the LMS to perform
//     * different tasks
//     * @param staffId String
//     * @param passwd  String
//     * @return Object of the Staff Account
//     */
//    public static StaffAccount staffLogin(String staffId , String passwd)
//    {
//
//        StaffAccount staff = AccountsListJDBC.staffLogin(staffId, passwd);
//
//        return staff;
//    }// end staffLogin
//
//
//    /***************************************************************************
//     * userLogin functions allows the staff to login to the LMS to perform
//     * different tasks
//     * @param userId  String
//     * @param passwd  String
//     * @return instance of the UserAccount Class
//     */
//    public static  UserAccount userLogin(String userId , String passwd)
//    {
//        //connects to DB with staff credentials , returns true if credentials are
//        //accurate and false if not accurate
//        UserAccount user = AccountsListJDBC.userLogin(userId, passwd);
//
//        return user;
//    }//end userLogin
//
//
//    /***************************************************************************
//     * staffLogout functions allows the staff to logout of LMS
//     *
//     * @return True when successfully logged out
//     */
//    public static boolean staffLogout()
//    {
//        boolean result = AccountsListJDBC.staffLogout();
//        return result ;
//    }//end staffLogout
//
//
//    /***************************************************************************
//     * userLogout function allows user to logout of the LMS
//     * @return True when successfully logged out
//     */
//    public static  boolean userLogout()
//    {
//        boolean result = AccountsListJDBC.userLogout();
//
//        return result;
//    }// end userLogout
//

}//end AccountsManagement.java
