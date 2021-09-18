package si.um.opj.cirak.logic;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *  This is second assignment of OOPJ
 *     FoodItem  class
 *     @author Nurullah Cirak
 */
public class FoodItem implements Serializable {
    private String label;
    private double volume;
    private transient int weight;
    private LocalDate expirationDate;
    private FoodItemType foodItemType;

    //LocalDate date=LocalDate.now();

    /**
     *
     * @return type of food item
     */
    public FoodItemType getFoodItemType() {
        return foodItemType;
    }

    /**
     *  set type of food item
     * @param foodItemType
     */
    public void setFoodItemType(FoodItemType foodItemType) {
        this.foodItemType = foodItemType;
    }

    /**
     * setter of weight
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * setter of expirationDate
     * @param expirationDate
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Setter of label
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * setter of volume
     * @param volume
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * getter of label
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * getter of weight
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * getter of expirationDate
     * @return expirationDate
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * volume
     * @return volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Default Constructor
     */
    public FoodItem(){ }

    /**
     * Basic Constructor with label parameter
     * @param label
     */
    public FoodItem(String label){
        this.label=label;
    }

    /**
     * basic constructor with all parameter
     * @param label
     * @param volume
     * @param weight
     * @param expirationDate
     */
    public FoodItem(String label,double volume,int weight, LocalDate expirationDate){
         this(label);
         this.volume=volume;
         this.weight=weight;
         this.expirationDate=expirationDate;
    }
    /**
     *
     * @param label
     * @param volume
     * @param weight
     * @param expirationDate
     * @param foodItemType
     */
    public FoodItem(String label,double volume,int weight, LocalDate expirationDate, FoodItemType foodItemType){
        this(label, volume, weight, expirationDate);
        this.foodItemType=foodItemType;
    }

    /**
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "FoodItem{" +
                "label='" + label + '\'' +
                ", volume=" + volume +
                ", weight=" + weight +
                ", expirationDate=" + expirationDate +
                ", foodItemType=" + foodItemType +
                '}';
    }
}
