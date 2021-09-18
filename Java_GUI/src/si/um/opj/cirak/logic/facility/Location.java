package si.um.opj.cirak.logic.facility;

import java.io.Serializable;

/**
    This is second assignment of OOPJ
    Location class
    @author Nurullah Cirak

*/
public class Location implements Serializable {
    private String city;
    private String country;

    /**
     *default constructor
     */
    public Location(){
        city= "";
        country="";
    }

    /**
     * basic  constructor with parameter
     * @param city
     * @param country
     */
    public Location(String city, String country){
        this.city=city;
        this.country=country;
    }

    /**
     *
     * @return city of location
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return country of location
     */
    public String getCountry() {
        return country;
    }

    /**
     * Setter of city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * setter of country
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * tostring method
     * @return string representation of the object
     */

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

