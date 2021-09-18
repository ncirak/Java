package si.um.opj.cirak.logic.facility;

import java.io.Serializable;

public class BusinessFacilitiy implements Serializable {
    private String name;
    private Location location;

    /**
     *
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter of location
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * setter of name of  object
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Default Constructor
     */
    public BusinessFacilitiy(){ }

    /**
     * Basic Constructor with parameters
     * @param name
     * @param location
     */
    public BusinessFacilitiy(String name, Location location){
        this.name=name;
        this.location=location;
    }

    @Override
    public String toString() {
        return "BusinessFacilitiy{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}
