package si.um.opj.cirak.logic.facility;

import si.um.opj.cirak.logic.transport.Transportable;
import si.um.opj.cirak.logic.transport.Truck;
import si.um.opj.cirak.logic.transport.Van;
import si.um.opj.cirak.logic.transport.Vehicle;

import java.io.Serializable;

/**
 *  This is second assignment of OOPJ
 *     Store class
 *     @author Nurullah Cirak
 */
public class Store extends BusinessFacilitiy implements Transportable, Serializable {

    public Store(){ }

    /**
     * Basic Constructor with parameters
     * @param name
     * @param location
     */
    public Store(String name, Location location){
      super(name,location);
    }

    @Override
    public void acceptVehicle(Vehicle vehicle) {

        if (vehicle instanceof Truck){
            vehicle.unloadFoodItems();
        }
        else if (vehicle instanceof Van)
        {
            vehicle.unloadFoodItems();
        }
    }

    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
