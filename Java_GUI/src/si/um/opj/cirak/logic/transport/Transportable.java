package si.um.opj.cirak.logic.transport;

import si.um.opj.cirak.logic.CapacityExceededException;
import si.um.opj.cirak.logic.FoodItemTypeException;
import si.um.opj.cirak.logic.facility.VolumeExceededException;

import java.io.Serializable;

public interface Transportable extends Serializable {


    public void acceptVehicle(Vehicle vehicle) throws CapacityExceededException, VolumeExceededException, FoodItemTypeException;

}
