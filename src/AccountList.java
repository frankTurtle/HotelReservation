//package Team1 Group1;


import java.util.ArrayList;

/**


 * The AccountListAccount.java \
 * @author Amarjit Singh, Barret Nobel , Jay Kumar
 */

public class AccountList {

    /**
     *
     * @param user
     */

    public static void addUserAccount(UserAccount user )
    {

        AccountListJDBC.addUserAccount(user);

    }// end addUserAccount

    /***************************************************************************
     * this method adds the Staff Account to the DB
     * @param staff instance of the StaffAccount class
     */

    public static void addStaffAccount(StaffAccount staff)
    {


        AccountListJDBC.addStaffAccount(staff);

    }//end AddStaffAccount



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


        AccountListJDBC.updateAccountByStaff( userId, passwd , FName , LName ,
                passwd , Address , State , ZipCode , PhoneNo, email, Accounttype );


    }//end updateAccountByUser

    /***************************************************************************
     * updateStaffAccount allows the staff to update the staff information
     * @param staffId  String type
     * @param passwd   String type
     * @param FName    String type
     * @param LName    String type
     */
    public static void updateStaffAccountMethod (String staffId ,String passwd, String FName , String LName , String Accounttype,
                                                 String Username)

    {



        AccountListJDBC.updateStaffAccount(staffId , passwd ,  FName, LName , Accounttype , Username);



    }// end updateStaffAccount


    /**
     * searchUserAccount searches the user account by the userID
     * or the LName
     * @param userID String
     * @return instance of UserAccountMethod Class
     */
    public static UserAccount searchUserAccount ( String userID)
    {



        UserAccount user = AccountListJDBC.searchUserAccount(userID);
        return user;
    }
    /*******************************************************************************
     * searchStaffAccount searches the user account by the staffID
     * or the LName
     * @param staffID String
     * @return instance of StaffAccountMethod class
     */
    public static StaffAccount searchStaffAccount ( String staffID)
    {


        StaffAccount staff = AccountListJDBC.searchStaffAccount(staffID);

        return staff;
    }// end searchStaffAccount

    /***************************************************************************
     * deleteUserAccount takes the desired userID and deletes it from the database
     *
     * @param userID
     */
    public static void deleteUserAccount(int userID)
    {

        AccountListJDBC.deleteUserAccount(userID);
    }//end deleteUserAccoun


    /***************************************************************************
     *deleteStaffAccount takes the desired staffID and deletes it from the database
     * it connects to DB via the AccountsManagementJDBC methods.
     * @param staffID
     */
    public static void deleteStaffAccount(int staffID)
    {

        AccountListJDBC.deleteStaffAccount(staffID);
    }//end deleteStaffAccount


    /***************************************************************************
     * staffLogin functions allows the staff to login to the LMS to perform
     * different tasks
     * @param staffId String
     * @param passwd  String
     * @return Object of the Staff Account
     */
    public static StaffAccount staffLogin(String staffId , String passwd)
    {

        StaffAccount staff = AccountListJDBC.staffLogin(staffId, passwd);

        return staff;
    }// end staffLogin


    /***************************************************************************
     * userLogin functions allows the staff to login to the LMS to perform
     * different tasks
     * @param userId  String
     * @param passwd  String
     * @return instance of the UserAccount Class
     */
    public static  UserAccount userLogin(String userId , String passwd)
    {
        //connects to DB with staff credentials , returns true if credentials are
        //accurate and false if not accurate
        UserAccount user = AccountListJDBC.userLogin(userId, passwd);

        return user;
    }//end userLogin


    /***************************************************************************
     *
     * @return True when successfully logged out
     */
    public static boolean logout()
    {
        return (AccountListJDBC.logout() == null) ? true : false;
    }//end logout

//.......................................................................................

    public static ArrayList<Account> getAllAccounts()
    {
        return AccountListJDBC.getAllAccounts();
    }


}//end AccountList.java
