package si.um.opj.cirak.logic;

import si.um.opj.cirak.logic.facility.Location;
import si.um.opj.cirak.logic.facility.Store;
import si.um.opj.cirak.logic.facility.VolumeExceededException;
import si.um.opj.cirak.logic.facility.Warehouse;
import si.um.opj.cirak.logic.transport.Route;
import si.um.opj.cirak.logic.transport.Truck;
import si.um.opj.cirak.logic.transport.Van;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *  This is second assignment of OOPJ
 *     Launcher class (class that all the things run for test purpose, currently everything works for GUI)
 *     @author Nurullah Cirak
 */
public class Launcher {


    public static void main(String[] args) throws CapacityExceededException, VolumeExceededException, FoodItemTypeException {
        Location loc_store = new Location("Maribor", "Slovenia");
        Location loc_ware = new Location("Celje", "Slovenia");

        Store str = new Store("New Store", loc_store);

        Warehouse wrh = new Warehouse("New Warehouse", loc_ware, 10);

        FoodItem apple = new FoodItem("apple",2.0,5, LocalDate.now().plusMonths(3), FoodItemType.FRESH);
        FoodItem cheese = new FoodItem("cheese",2.5,10, LocalDate.now().plusMonths(5),FoodItemType.FRESH);
        FoodItem rice = new FoodItem("rice",3.0,9, LocalDate.now().plusMonths(6),FoodItemType.FRESH);
        FoodItem pasta = new FoodItem("pasta",2.0,6, LocalDate.now().plusMonths(7),FoodItemType.FRESH);
        FoodItem banana = new FoodItem("banana",4.0,12, LocalDate.now().plusMonths(8),FoodItemType.FRESH);

        FoodItem cucumber = new FoodItem("cucumber",3,12, LocalDate.now().plusDays(2),FoodItemType.FRESH);//expires in 2 day

        FoodItem peach = new FoodItem("peach",8,12, LocalDate.now().plusDays(10),FoodItemType.FROZEN);
        FoodItem berries = new FoodItem("berries",5,9, LocalDate.now().plusWeeks(6) ,FoodItemType.FROZEN);
        FoodItem strawberry = new FoodItem("strawberry",7,9, LocalDate.now().plusMonths(18) ,FoodItemType.FROZEN);

        System.out.println(cucumber.toString());
        FoodItem [] array= {apple,cheese,rice,pasta,banana,cucumber,peach,berries,strawberry};
        for (int i = 0; i < array.length; i++) {
            wrh.addItem(array[i]);
        }
        /*System.out.println("\nApple exist ? "+wrh.foodItemExists("apple"));
        System.out.println("Number of item in warehouse: "+ wrh.returnTheNumberOfFoodItems());

        wrh.removeItem(apple);

        System.out.println("Apple exist ? "+wrh.foodItemExists("apple"));
        System.out.println("After removing Apple, Number of item in warehouse: "+ wrh.returnTheNumberOfFoodItems());
        Route rot= new Route(str, wrh,1000);*/

        Truck truck1= new Truck(35,75,1500,1,15,5);
        FoodItem [] wrh_food=wrh.getFoodItems();

        wrh.acceptVehicle(truck1);


        System.out.println("\nNumber of item truck cargo: "+truck1.returnTheNumberOfFoodItems());
        System.out.println("Taken space percentage of truck1: "+truck1.getTakenSpace());
        System.out.println("Max volume  of truck1: "+truck1.getVehiclesMaxVolume());


        for (int i = 0; i < wrh.returnTheNumberOfFoodItems(); i++) {
            truck1.unloadFoodItems();
        }
        /*
        int speed=0;
        Scanner scanX = new Scanner(System.in);  // Create a Scanner object
        try{
            System.out.println("Enter van details of average speed: ");

            speed= scanX.nextInt();  // Read user input
            if (speed < 0 || speed > 100)
                throw new IllegalArgumentException("Value must be non-negative and above 100");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Average speed can not be negative or greater than 100");
        }

        int weight=0;
        Scanner scanY = new Scanner(System.in);  // Create a Scanner object
        try{
            System.out.println("Enter van details of weight: ");

            weight= scanY.nextInt();  // Read user input
            if (weight < 0 || weight > 3000)
                throw new IllegalArgumentException("Value must be non-negative and above 3000");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Weight can not be negative or greater than 3000");
        }

        int volume =0;
        Scanner scanZ = new Scanner(System.in);  // Create a Scanner object
        try{
            System.out.println("Enter van details of volume: ");

            volume= scanZ.nextInt();  // Read user input
            if (volume < 0 || volume > 5000)
                throw new IllegalArgumentException("Value must be non-negative and above 5500");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("volume can not be negative or greater than 100");
        }
        */

        int speed =50, weight= 500, volume=750;
        Van van1= new Van(56,speed,weight,volume,7,FoodItemType.FRESH);
        /*for (int i = 0; i < wrh.returnTheNumberOfFoodItems(); i++) {
            van1.loadFoodItem(wrh_food[i]);
        }*/
        wrh.acceptVehicle(van1);
        System.out.println("\nNumber of item van cargo: "+van1.returnTheNumberOfFoodItems());
        System.out.println("Taken space percentage of van1: "+van1.getTakenSpace());
        System.out.println("Max volume  of van1: "+van1.getVehiclesMaxVolume());
    }
}

