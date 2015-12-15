/**
 * @author Sayem Shahrier
 * @version 1.0
 * Group 1 Team 3
 * Reservation Class
 */
public class Reservation
{
    private int accountId;
    private int reservationId;
    private String roomId;
    private String checkInTime;
    private String checkOutTime;
    private int roomAmount;
    private String paymentMethod;
    private double totalCost;

    /**
     * Non-default constructors
     */

    public Reservation()
    {

    }

    /**
     * Constructors with parameters
     * @param accountId Account ID associated with the reservation
     * @param reservationId ID of the reservation
     * @param roomId The "room number" of the rooms of reservation
     * @param checkInTime Time the reservation is checked into
     * @param checkOutTime Time the reservation is checked out of
     * @param roomAmount Amount of rooms associated with the reservation
     * @param paymentMethod Method of payment for the reservation
     * @param totalCost of all room, amenity, and reservation fees
     */
    public Reservation (int accountId, int reservationId, String roomId, String checkInTime, String checkOutTime,
                        int roomAmount, String paymentMethod, double totalCost)
    {
        this.setAccountId (accountId);
        this.setReservationId (reservationId);
        this.setRoomId (roomId);
        this.setCheckInTime (checkInTime);
        this.setCheckOutTime (checkOutTime);
        this.setRoomAmount (roomAmount);
        this.setPaymentMethod (paymentMethod);
        this.setTotalCost (totalCost);
    }

    /**
     * Setter method for the accountId
     * @param accountId Account ID to set
     * @return void
     */
    public void setAccountId (int accountId)
    {
        this.accountId = accountId;

    }

    /**
     * Setter method for the Reservation ID
     * @param reservationId Reservation ID to set
     * @return void
     */
    public void setReservationId (int reservationId)
    {
        this.reservationId = reservationId;
    }

    /**
     * Setter method for the Room ID
     * @param roomId Room ID to set
     * @return boolean
     */
    public void setRoomId (String roomId)
    {
        this.roomId = roomId;
    }

    /**
     * Sets the checkInTime to currentTime.
     * @return void
     */
    public void setCheckInTime (String checkInTime)
    {
        this.checkInTime = checkInTime;

    }
    /**
     * Sets the checkOutTime to currentTime.
     * @return void
     */
    public void setCheckOutTime (String checkOutTime)
    {
        this.checkOutTime = checkOutTime;
    }
    /**
     * Sets the roomAmount
     * @return void
     */
    public void setRoomAmount (int roomAmount)
    {
        this.roomAmount = roomAmount;
    }
    /**
     * Sets the paymentMethod
     * @return boolean
     */
    public void setPaymentMethod (String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }
    /**
     * Setter method for the totalCost
     * @param totalCost Total cost to set
     * @return void
     */
    public void setTotalCost (double totalCost)
    {
        this.totalCost = totalCost;
    }
    //__________________________


    /**
     * Getter method for the accountId
     * @param accountId Account ID to set
     * @return Account ID
     */
    public int getAccountId()
    {
        return this.accountId;
    }

    /**
     * Getter method for the Reservation ID
     * @param reservationId Reservation ID to set
     * @return Reservation ID
     */
    public int getReservationId ()
    {
        return this.reservationId;
    }
    /**
     * Getter method for the Room ID
     * @param roomId Room ID to set
     * @return Room ID (Room Number)
     */
    public String getRoomId ()
    {
        return this.roomId;
    }
    /**
     * Getter method of checkInTime
     * @return Check in time
     */
    public String getCheckInTime ()
    {
        return this.checkInTime;
    }
    /**
     *Getter method of the checkOutTime
     *@return Check out time
     */
    public String getCheckOutTime ()
    {
        return this.checkOutTime;
    }
    /**
     * Getter method of roomAmount
     * @return
     */
    public int getRoomAmount ()
    {
        return this.roomAmount;
    }
    /**
     * Getter method of paymentMethod
     * @return
     */
    public String getPaymentMethod ()
    {
        return this.paymentMethod;
    }

    /**
     * Getter method for the Total Cost
     * @param totalCost Total cost to set
     * @return Total Cost
     */
    public double getTotalCost ()
    {
        return this.totalCost;
    }

    //________________________

    /**
     * Override to the toString method
     * @return String
     */
    public String toString()
    {
        return String.format("User Account Number: %d\n" +
                        "User Reservation Number: %d\n" +
                        "User Room Number: %s\n" +
                        "User Check in: %s\n" +
                        "User Check out: %s\n" +
                        "Number of Rooms Reserved:%d\n" +
                        "Method of payment:%s" +
                        "Total Cost: %f\n",
                this.getAccountId(), this.getReservationId(), this.getRoomId(), this.getCheckInTime(), this.getCheckOutTime(), this.getRoomAmount(),this.getPaymentMethod(),this.getTotalCost() );
    }

}
