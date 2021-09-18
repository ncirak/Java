package si.um.opj.cirak.logic.transport;

import java.io.Serializable;
import java.util.Arrays;

public class Truck extends Vehicle  implements Serializable {

    private int numberOfTrailers;

    public Truck(){}
    public Truck (int registrationNumber,int  averageSpeed,int maxWeight,int volume, int capacity, int numberOfTrailers){
        super(registrationNumber, averageSpeed, maxWeight, volume, capacity);
        this.numberOfTrailers=numberOfTrailers;
    }

    public int getNumberOfTrailers() {
        return numberOfTrailers;
    }

    public void setNumberOfTrailers(int numberOfTrailers) {
        this.numberOfTrailers = numberOfTrailers;
    }


    @Override
    public double getVehiclesMaxVolume() {

        return (getVolume() *numberOfTrailers);
    }

    @Override
    public String toString() {
        return "Truck{" +
                super.toString() +
                "numberOfTrailers=" + numberOfTrailers ;
    }
}
