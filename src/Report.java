/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package report;

/**
 *
 * @author Jay
 */



public class Report
{
    /**
     *Account is Report
     * @author Jay Kumar
     */
    private String report;
    private String total;
    /***************************************************************************
     *
     * Default for the Report
     *
     */
    Report ()
    {
        this.total = "";
        this.report = "";

    }
    /***************************************************************************
     *
     * @param String report Generates Report Object
     *
     */
    Report (String reports)
    {
        this.total = "";

    }

    /***************************************************************************
     *
     *It generates report for the year
     *
     * @param year
     * @return double total
     */
    public static String generateYearly (int year)
    {
        return ReportJDBC.generateYearly(year);
    }


    /***************************************************************************
     *
     *It generates report for the Month
     *
     * @param month
     * @return double total
     */
    public static String generateMonthly (int month)
    {
        return ReportJDBC.generateMonthly(month);
    }


    /***************************************************************************
     *
     * It generates report for the Day
     *
     * @param currentDay
     * @return double total for the day
     */
    public static String generateDaily (int currentDay)
    {
        return ReportJDBC.generateDaily(currentDay);
    }
    /***************************************************************************
     *
     * It generates report for the Day
     * @return String Object
     */
    @Override
    public String toString ()
    {
        return "Report" + report + "\n" + "Totals" + total ;
    }
    public static void main (String [] ARGS)

    {
        Report fuck = new Report ();
        System.out.println(fuck.generateYearly(2015));
    }
}