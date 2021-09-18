package si.um.opj.cirak.logic.facility;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

import com.sun.jdi.ThreadReference;
import si.um.opj.cirak.logic.CapacityExceededException;
import si.um.opj.cirak.logic.FoodItem;
import si.um.opj.cirak.logic.FoodItemTypeException;
import si.um.opj.cirak.logic.transport.Transportable;
import si.um.opj.cirak.logic.transport.Truck;
import si.um.opj.cirak.logic.transport.Van;
import si.um.opj.cirak.logic.transport.Vehicle;

/**
 *  This is second assignment of OOPJ
 *     Warehouse class
 *     @author Nurullah Cirak
 */
public class Warehouse extends BusinessFacilitiy implements Transportable , Serializable {

    private FoodItem foodItems[];

    /**
     * Setter of foodItem
     * @param foodItems
     */
    public void setFoodItems(FoodItem[] foodItems) {
        this.foodItems = foodItems;
    }

    /**
     * getter of foodItem
     * @return foodItem
     */
    public FoodItem[] getFoodItems() {
        return foodItems;
    }

    /**
     * Default Constructor
     */
    private Warehouse (){

    }

    /**
     * Basic Constructor with name and location parameter
     * @param name
     * @param location
     */
    private Warehouse(String name, Location location){
        super(name,location);
    }

    /**
     * Basic Constructor with name, capacity and location parameter
     * @param name
     * @param location
     * @param capacity
     */
    public Warehouse(String name, Location location, int capacity){
        this(name,location);
        setFoodItems(new FoodItem[capacity]);
    }

    /**
     *
     * @param foodItem
     * @return food item is still valid or expiration date will end in 3 days
     */
    private boolean stillValid(FoodItem foodItem){
        LocalDate checkDate=LocalDate.now().plusDays(3);
        if ((foodItem.getExpirationDate()).isBefore(checkDate) ) {return false;}
        else {return true;}
    }
    /**
     * addItem methods to food array
     * @param foodItem
     */
    public void addItem(FoodItem foodItem){
        boolean foundEmpty=false;
        if(stillValid(foodItem)) {
            for (int i = 0; i < this.foodItems.length && foundEmpty == false; i++) {
                if (foodItems[i] == null) {
                    foodItems[i] = foodItem;
                    foundEmpty = true;
                }
            }
        }
        else
            System.out.println("This food item is not valid! It will expire in 3 days.");

        if (foundEmpty==false)
            System.out.println("Not enough capacity! Can not add item!");
    }

    /**
     * remove item from food array
     * @param foodItem
     */
    public void removeItem(FoodItem foodItem) {
        int index = -1;
        for (int i = 0; i < this.foodItems.length; i++) {
            if (foodItems[i]!=null && foodItems[i].equals(foodItem) ) {
                index = i;
            }
        }
        if (index!=-1)//if this item with label exist, make equalize to null
        {
            foodItems[index]=null;
        }
        else
            System.out.println("Can not found this  item in array!");
    }
    /**
     *
     * @return number of food item in the array
     */
    public int returnTheNumberOfFoodItems() {
        int counter = 0;
        for (int i = 0; i < this.foodItems.length; i++) {
            if(foodItems[i]!=null) {
                counter++;
            }
        }
        return counter;
    }

    /**
     *
     * @param label
     * @return check with this label item exist in array or not
     */
    public boolean foodItemExists(String label){
        for (int i=0; i<foodItems.length;i++){
            if (foodItems[i]!=null && foodItems[i].getLabel()==label) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void acceptVehicle(Vehicle vehicle) throws CapacityExceededException, VolumeExceededException, FoodItemTypeException {

        if (vehicle instanceof Truck){
            vehicle.loadFoodItem(foodItems);

        }
        else if (vehicle instanceof Van)
        {
            boolean isAdded=false;
            for (int i = 0; i < foodItems.length && isAdded==false; i++) {
                vehicle.loadFoodItem(foodItems[i]);
                isAdded=true;
            }
        }
    }
    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return super.toString()+"Warehouse{" +
                "foodItems=" + Arrays.toString(foodItems) +
                '}';
    }
}
/*
for (int i = 0; i < foodItems.length; i++) {
    foodItems[i]=null;
}
*/
//foodItems[0]=null;
