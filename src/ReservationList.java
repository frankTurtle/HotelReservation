/**
 * The collection of Reservations, and the methods to access and modify them.
 * @author Frank Rusinovich
 * @version 1.0
 */

public class ReservationList {

    /**
     * The array containing all of the reservations
     */
    public Reservation[] resList;

    /***************Constructors**************************************

     /**
     * Default constructor that sets the array size to 10.
     */
    public ReservationList(){
        resList = new Reservation[10];
    }

    /**
     * Overloaded constructor that sets the number of reservations in the array.
     * @param num The number of reservations in the array
     */
    public ReservationList(int num){
        if (num >= 0){
            resList = new Reservation[num];
        }
        else{
            System.out.println("Error: Could not create reservation list because the number of reservations is invalid.\n");
        }
    }

    /****************Methods******************************************

     /**
     * Checks the ReservationList to see if a Reservation with the specified ID exists. If it does, the Reservation is returned. If not, then the user is told that no such Reservation exists.
     *@param ID The Reservation ID specified to be viewed.
     *@return Reservation, The Reservation object with the ID specified. If no existing Reservation has that ID, then it tells the user that no such Reservation exists and returns null.
     */
    public Reservation viewReservation(int ID){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                return resList[i];
            }
        }
        System.out.println("Could not find a reservation with the specified ID.\n");
        return null;
    }

    /**
     * Method to update the check-in time of a specific reservation.
     * @param ID The Reservation ID
     * @param checkin The String with the check-in time
     * @return True if the time is updated correctly, False if not updated.
     */
    public boolean updateCheckInTime(int ID, String checkin){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setCheckInTime(checkin);
                System.out.println("Successfully updated the check-in time of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }

    /**
     * Method to update the check-out time of a specific reservation.
     * @param ID The Reservation ID
     * @param checkout The String with the check-out time
     * @return True if the time is updated correctly, False if not updated.
     */
    public boolean updateCheckOutTime(int ID, String checkout){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setCheckOutTime(checkout);
                System.out.println("Successfully updated the check-out time of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }

    /**
     * Method to update the room number of a specific reservation.
     * @param ID The Reservation ID
     * @param roomNum The new room number
     * @return True if the room number is updated correctly, False if not updated.
     */
//    public boolean updateRoomNumber(int ID, int roomNum){
//        for(int i = 0; i < resList.length; i++){
//            if(resList[i].getReservationId() == ID){
//                resList[i].setRoomId(roomNum);
//                System.out.println("Successfully updated the room number of reservation with ID " + ID + "\n");
//                return true;
//            }
//        }
//        System.out.println("Could not find a reservation with the specified ID to update.\n");
//        return false;
//    }

    /**
     * Method to update the amount of rooms of a specific reservation.
     * @param ID The Reservation ID
     * @param roomAmt The new room amount
     * @return True if the amount of rooms is updated correctly, False if not updated.
     */
    public boolean updateRoomAmount(int ID, int roomAmt){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setRoomAmount(roomAmt);
                System.out.println("Successfully updated the room amount of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }

    /**
     * Method to update the associated account ID of a specific reservation.
     * @param ID The Reservation ID
     * @param accoutNum The new Account ID
     * @return True if the account ID is updated correctly, False if not updated.
     */
    public boolean updateAccountNumber(int ID, int acctNum){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setAccountId(acctNum);
                System.out.println("Successfully updated the account ID of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }

    /**
     * Method to update the payment method of a specific reservation.
     * @param ID The Reservation ID
     * @param payMethod The new payment method
     * @return True if the payment method is updated correctly, False if not updated.
     */
    public boolean updatePaymentMethod(int ID, String payMethod){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setPaymentMethod(payMethod);
                System.out.println("Successfully updated the payment method of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }

    /**
     * Method to update the total cost of a specific reservation.
     * @param ID The Reservation ID
     * @param totalCost The new total cost
     * @return True if the payment method is updated correctly, False if not updated.
     */
    public boolean updateTotalCost(int ID, int totalCost){
        for(int i = 0; i < resList.length; i++){
            if(resList[i].getReservationId() == ID){
                resList[i].setTotalCost(totalCost);
                System.out.println("Successfully updated the total cost of reservation with ID " + ID + "\n");
                return true;
            }
        }
        System.out.println("Could not find a reservation with the specified ID to update.\n");
        return false;
    }
}
