/**
 * Created by Barret J. Nobel on 12/1/2015.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelReservationApplication
{
    public static void main( String[] args )
    {
        login();
    }

    public static void login()
    {
        Scanner console = new Scanner( System.in );
        int choice = 0;

        do
        {
            System.out.print( LoginInterface.initialMenu() );

            try
            {
                choice = console.nextInt();
                if( choice < 1 || choice > 2) throw new Exception("\nChoice must be between 1 and 2, try again!");
            }
            catch ( InputMismatchException e )
            {
                System.out.println("\nValue must be an integer, try again!");
                Object temp = console.next();
            }
            catch ( Exception e )
            {
                System.out.println( e.getMessage() );

            }
        }while ( choice != 1 && choice != 2 );
    }
}
