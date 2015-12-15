public class Amenity
{
    private int amenityId;
    private String amenityname;
    private char status;

    public Amenity()
    {

    }

    public int getAmenityId()
    {
        return amenityId;
    }

    public String getAmenityName()
    {
        return amenityname;
    }

    public char getAmenityStatus()
    {
        return status;
    }

    public void setAmenityStatus(char n_status)
    {
        status = n_status;
    }

    public String toString()
    {
        return "Amenity ID: " + amenityId + " - Amenity Name: " + amenityname + " - Amenity Status: " + status + "\n";

    }
}