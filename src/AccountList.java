//package Team1 Group1;

/**
 * The AccountsListAccount.java \
 * @author Amarjit Singh, Barret Nobel , Jay Kumar
 */
public class AccountList {

    /**
     *
     * @param user
     */
    public static void addUserAccountMethod(UserAccount user )
    {
        AccountsListJDBC.addUserAccountMethod(user);

    }// end addUserAccount

    /***************************************************************************
     * this method adds the Staff Account to the DB
     * @param staff instance of the StaffAccount class
     */
    public static void AddStaffAccountMethod (StaffAccount staff)
    {

        AccountsListJDBC.AddStaffAccount(staff);
    }//end AddStaffAccountMethod



    /***************************************************************************
     *
     * @param userId
     * @param passwd
     * @param FName
     * @param LName
     * @param Address
     * @param City
     * @param State
     * @param ZipCode
     * @param PhoneNo
     * @param email
     * @param AccountType
     */
    public static  void updateAccountByStaffMethod(String userId , String passwd,
                                                   String FName , String LName , String Address ,String City ,
                                                   String State , String ZipCode ,String PhoneNo , String email , String Accounttype)
    {


        AccountsListJDBC.updateUserAccountByStaffMethod( userId, passwd , FName , LName ,
                passwd , Address , State , ZipCode , PhoneNo, email);

    }//end updateAccountByUserMethod

    /***************************************************************************
     * updateStaffAccount allows the staff to update the staff information
     * @param staffId  String type
     * @param passwd   String type
     * @param FName    String type
     * @param LName    String type
     */
    public static void updateStaffAccountMethod (String staffId ,String passwd, String FName , String LName , String Accounttype
                                                 String Username)

    {

        AccountsListJDBC.updateStaffAccountMethod(staffId , passwd ,  FName, LName , Accounttype , Username);

    }// end updateStaffAccountMethod


    /**
     * searchUserAccount searches the user account by the userID
     * or the LName
     * @param userID String
     * @return instance of UserAccountMethod Class
     */
    public static UserAccount searchUserAccountMethod ( String userID)
    {

        UserAccount user = AccountsListJDBC.searchUserAccountMethod(userID);
        return user;
    }
    /*******************************************************************************
     * searchStaffAccount searches the user account by the staffID
     * or the LName
     * @param staffID String
     * @return instance of StaffAccountMethod class
     */
    public static StaffAccount searchStaffAccountMethod ( String staffID)
    {

        StaffAccountMethod staff = AccountsListJDBC.searchStaffAccountMethod(staffID);

        return staff;
    }// end searchStaffAccountMethod

    /***************************************************************************
     * deleteUserAccount takes the desired userID and deletes it from the database
     *
     * @param userID
     */
    public static void deleteUserAccountMethod(String userID)
    {

        AccountsListJDBC.deleteUserAccountMethod(userID);
    }//end deleteUserAccountMethod


    /***************************************************************************
     *deleteStaffAccount takes the desired staffID and deletes it from the database
     * it connects to DB via the AccountsManagementJDBC methods.
     * @param staffID
     */
    public static void deleteStaffAccountMethod(String staffID)
    {

        AccountsListJDBC.deleteStaffAccountMethod(staffID);
    }//end deleteStaffAccountMethod


    /***************************************************************************
     * staffLogin functions allows the staff to login to the LMS to perform
     * different tasks
     * @param staffId String
     * @param passwd  String
     * @return Object of the Staff Account Method
     */
    public static StaffAccountMethod staffLogin(String staffId , String passwd)
    {

        StaffAccount staff = AccountsListJDBC.staffLoginMethod(staffId, passwd);

        return staff;
    }// end staffLoginMethod


    /***************************************************************************
     * userLogin functions allows the staff to login to the LMS to perform
     * different tasks
     * @param userId  String
     * @param passwd  String
     * @return instance of the UserAccount Class Method
     */
    public static  UserAccount userLogin(String userId , String passwd)
    {
        //connects to DB with staff credentials , returns true if credentials are
        //accurate and false if not accurate
        UserAccount user = AccountsListJDBC.userLoginMethod(userId, passwd);

        return user;
    }//end userLoginMethod


    /***************************************************************************
     * staffLogout functions allows the staff to logout of LMS
     *
     * @return True when successfully logged out
     */
    public static boolean staffLogoutMethod()
    {
        boolean result = AccountsListJDBC.staffLogoutMethod();
        return result ;
    }//end staffLogoutMethod


    /***************************************************************************
     * userLogout function allows user to logout of the LMS
     * @return True when successfully logged out
     */
    public static  boolean userLogoutMethod()
    {
        boolean result = AccountsListJDBC.userLogoutMethod();

        return result;
    }// end userLogoutMethod


}//end AccountsManagement.java
