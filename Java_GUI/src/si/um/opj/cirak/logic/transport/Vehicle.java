package si.um.opj.cirak.logic.transport;

import java.io.Serializable;
import java.util.Arrays;

import si.um.opj.cirak.logic.CapacityExceededException;
import si.um.opj.cirak.logic.FoodItem;
import si.um.opj.cirak.logic.FoodItemTypeException;
import si.um.opj.cirak.logic.facility.VolumeExceededException;

/**
 *  This is second assignment of OOPJ
 *     Vehicle class
 *     @author Nurullah Cirak
 */
public abstract class Vehicle implements Serializable {

    private int registrationNumber;
    private int volume;
    private int maxWeight;
    private int averageSpeed;
    protected FoodItem[] cargo;

    /**
     * getter of averageSpeed
     * @return averageSpeed
     */
    public int getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * getter of maxWeight
     * @return maxWeight
     */
    public int getMaxWeight() {
        return maxWeight;
    }

    /**
     * getter of registrationNumber
     * @return registrationNumber
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * getter of volume
     * @return volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     * setter of averageSpeed
     * @param averageSpeed
     */
    public void setAverageSpeed(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * setter of maxWeight
     * @param maxWeight
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * setter of registrationNumber
     * @param registrationNumber
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * setter of volume
     * @param volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public FoodItem[] getCargo() {
        return cargo;
    }

    public void setCargo(FoodItem[] cargo) {
        this.cargo = cargo;
    }

    public void allSetterVehicle(int registrationNumber,int  averageSpeed,int maxWeight,int volume, int capacity){
        setAverageSpeed(averageSpeed);
        setCargo(new FoodItem[capacity]);
        setMaxWeight(maxWeight);
        setVolume(volume);
        setRegistrationNumber(registrationNumber);
    }
    /**
     * Default constructor
     */
    public Vehicle(){
    }

    /**
     * Basic Constructor with registrationNumber and averageSpeed parameter
     * @param registrationNumber
     * @param averageSpeed
     */
    public Vehicle(int registrationNumber,int  averageSpeed){
        this.registrationNumber=registrationNumber;
        this.averageSpeed=averageSpeed;
    }

    /**
     * basic constructor with all parameter
     * @param registrationNumber
     * @param averageSpeed
     * @param maxWeight
     * @param volume
     */
    public Vehicle(int registrationNumber,int  averageSpeed,int maxWeight,int volume){
        this(registrationNumber,averageSpeed);
        this.maxWeight=maxWeight;
        this.volume=volume;
    }

    /**
     *
     * @param registrationNumber
     * @param averageSpeed
     * @param maxWeight
     * @param volume
     * @param capacity
     */
    public Vehicle(int registrationNumber,int  averageSpeed,int maxWeight,int volume, int capacity) {
        this(registrationNumber,averageSpeed, maxWeight,volume);
        setCargo(new FoodItem[capacity]);
    }

    public void capacityPrint(){

    }
    /**
     *
     * @param foodItem
     */
    public void loadFoodItem(FoodItem foodItem) throws CapacityExceededException, VolumeExceededException, FoodItemTypeException {
        try {
            boolean foundEmpty = false;

            for (int i = 0; i < this.cargo.length && foundEmpty == false; i++) {
                if (cargo[i] == null) {
                    cargo[i] = foodItem;
                    foundEmpty = true;
                    //System.out.println("Food item added.");
                }
            }
            if (!foundEmpty) {
                CapacityExceededException x = new CapacityExceededException("Food item could not add since empty slot did not find.");
                throw x;
            }
            if (this.getTakenSpace()>100){
                VolumeExceededException y= new VolumeExceededException("Food item could not add since max volume of vehicle exceeded.");
                throw y;
            }

        }
        catch (CapacityExceededException e){
            e.printStackTrace();
            System.out.println("Food item could not add since empty slot did not find.");
            if (this instanceof Truck){this.unloadFoodItems();}
        }
        catch (VolumeExceededException e){
            e.printStackTrace();
            System.out.println("Food item could not add since max volume of vehicle exceeded.");
            if (this instanceof Truck){this.unloadFoodItems();}
        }
    }

    /**
     * load food items array to vehicle
     * @param foodItems
     */
    public void loadFoodItem(FoodItem[] foodItems) throws CapacityExceededException, VolumeExceededException, FoodItemTypeException {

        for (int i = 0; i <foodItems.length ; i++) {
            this.loadFoodItem(foodItems[i]);
            //if not found space throw exception
        }
    }
    /**
     * This function unloads all food item from vehicle
     */
    public void unloadFoodItems() {
        for (int i = 0; i < this.cargo.length; i++) {
            if (cargo[i] != null) {
                cargo[i] = null;
            }
        }
    }
    public abstract double getVehiclesMaxVolume();

    /**
     *
     * @return the percentage of the volume taken up by loaded food items in a vehicle
     */
    public double getTakenSpace(){
        double totalVolume=0;

        for (int i = 0; i < this.cargo.length; i++) {
            if (cargo[i] != null){
                totalVolume += cargo[i].getVolume();
            }
        }
        return (totalVolume/getVehiclesMaxVolume()) *100;
    }

    public int returnTheNumberOfFoodItems() {
        int counter = 0;
        for (int i = 0; i < this.cargo.length; i++) {
            if(cargo[i]!=null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * calculates the number of travel days based on the averageSpeed and distance of that route
     * @param route
     * @return
     */
    public double calculateTravelTime (Route route){

        double hours= route.getDistance()/getAverageSpeed();

        return Math.ceil(hours/24);
    }

    /**
     *
     * @return string representation of the object
     */

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber=" + registrationNumber +
                ", volume=" + volume +
                ", maxWeight=" + maxWeight +
                ", averageSpeed=" + averageSpeed +
                ", cargo=" + Arrays.toString(cargo) +
                '}';
    }
}
