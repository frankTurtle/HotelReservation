/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package report;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Jay
 */
public class ReportJDBC
{
    // JDBC driver name and database URL
    /**
     *
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     *
     */
    static final String DB_URL = "jdbc:mysql://localhost:3306/reservation";

    //  Database credentials
    /**
     *
     */
    static final String USER = "root"; // Username
    /**
     *
     */
    static final String PASS = "1234567890"; // Password
    /**
     *
     */
    protected static Connection connection; //................. connection to DB
    protected static Statement statement;//.................... way to execute SQL



    /**
     *
     * @return
     */
    public static boolean makeConnection()
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL , USER, PASS);

            statement = connection.createStatement();
            return true;
        }
        catch (ClassNotFoundException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
        catch (SQLException error)
        {
            System.out.println(error.getMessage());
            return false;
        }
    }
    /**
     *
     * @return
     */
    public static boolean closeConnection()
    {

        try
        {
            statement.close();
            connection.close();
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    String report;
    /***************************************************************************
     *
     * Default for the Report
     *
     */
    ReportJDBC ()
    {

    }
    /***************************************************************************
     *
     * @param String report Generates Report Object
     *
     */
    ReportJDBC (String report)
    {

    }

    /***************************************************************************
     *
     *It generates report for the year
     *
     * @param year
     * @return
     */
    public static String generateYearly (int year)
    {

        String sqlGetYear = String.format("SELECT.Cost,Date FROM reservation.new_table;");

        double returnTC = 0;



        makeConnection();

        try
        {
            ResultSet rs = statement.executeQuery(sqlGetYear);
            while (rs.next())
            {

                returnTC += rs.getDouble(1);
            }
        }
        catch (SQLException error)
        {
            System.out.println(error.getMessage());
            return null;
        }
        String BN = " "+ returnTC;
        return BN;

    }


    /***************************************************************************
     *
     *It generates report for the Month
     *
     *@param query
     * @return
     */
    public static String generateMonthly (int month)
    {
        String sqlGetMon = String.format("SELECT.Cost FROM reservation.new_table;");
        double returnTC = 0;


        makeConnection();

        try
        {
            ResultSet rs = statement.executeQuery(sqlGetMon);
            while (rs.next())
            {
                returnTC += rs.getDouble(1);
            }

        }
        catch (SQLException error)
        {
            System.out.println(error.getMessage());
            return null;
        }
        String LH = " "+ returnTC;
        return LH;
    }


    /***************************************************************************
     *
     * It generates report for the Day
     *
     * @param currentDay
     * @param query
     * @return
     */
    public static String generateDaily (int currentDay)
    {
        String sqlGetDaily = String.format("SELECT.Cost FROM reservation.new_table;");
        double returnTC = 0;

        makeConnection();

        try
        {
            ResultSet rs = statement.executeQuery(sqlGetDaily);
            while (rs.next())
            {
                returnTC += rs.getDouble(1);
            }

        }
        catch (SQLException error)
        {
            System.out.println(error.getMessage());
            return null;
        }
        String AS = " "+ returnTC;
        return AS;
    }
    /***************************************************************************
     *
     * It generates report for the Day
     * @return String Object
     */

    //Do  I really need this in the JDBC
    // @Override
    // public String toString ()
    //{
    //  return "";
    //}
}

