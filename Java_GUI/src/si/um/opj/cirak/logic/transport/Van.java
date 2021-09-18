package si.um.opj.cirak.logic.transport;
import si.um.opj.cirak.logic.CapacityExceededException;
import si.um.opj.cirak.logic.FoodItem;
import si.um.opj.cirak.logic.FoodItemType;
import si.um.opj.cirak.logic.FoodItemTypeException;
import si.um.opj.cirak.logic.facility.VolumeExceededException;
//import si.um.opj.cirak.logic.FoodItem;

import java.io.Serializable;
import java.util.Arrays;

public class Van extends Vehicle implements Serializable {

    private FoodItemType foodItemType;

    public Van(){}
    public Van(int registrationNumber,int  averageSpeed,int maxWeight,int volume, int capacity, FoodItemType foodItemType){
        super(registrationNumber, averageSpeed, maxWeight, volume, capacity);
        this.foodItemType=foodItemType;
    }
    @Override
    public double getVehiclesMaxVolume() {
        return (getVolume());
    }

    public void setFoodItemType(FoodItemType foodItemType) {
        this.foodItemType = foodItemType;
    }

    public FoodItemType getFoodItemType() {
        return foodItemType;
    }

    @Override
    public void loadFoodItem(FoodItem foodItem) throws CapacityExceededException, VolumeExceededException , FoodItemTypeException {
        try {
            super.loadFoodItem(foodItem);
            if (!foodItem.getFoodItemType().equals(getFoodItemType())) {
                FoodItemTypeException z = new FoodItemTypeException("Food item types can not accept!");
                throw z;
            }
        }
        catch (FoodItemTypeException e){e.printStackTrace();}
    }

    /**
     *
     * @return string
     */
    @Override
    public String toString() {
        return "Van{" +
                super.toString()+
                "foodItemType=" + foodItemType;
    }
}
