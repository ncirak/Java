package si.um.opj.cirak.logic.transport;

import si.um.opj.cirak.logic.facility.Store;
import si.um.opj.cirak.logic.facility.Warehouse;

import java.io.Serializable;

/**
 *     This is second assignment of OOPJ
 *     Route class
 *     @author Nurullah Cirak
 */
public class Route implements Serializable {
    private Store store;
    private Warehouse warehouse;
    private int distance;

    /**
     * getter of Distance
     * @return Distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * getter of Store
     * @return Store
     */
    public Store getStore() {
        return store;
    }

    /**
     * getter Warehouse
     * @return Warehouse
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * setter of Distance
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * setter of Store
     * @param store
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * setter
     * @param warehouse - warehouse at that route
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Default Constructor
     */
    public Route(){
        /*store= new Store();
        warehouse =new Warehouse();
        distance=0;*/
    }

    /**
     * Basic constructor with parameter
     * @param store
     * @param warehouse
     * @param distance
     */
    public Route(Store store,Warehouse warehouse,int distance){
        this.store=store;
        this.warehouse=warehouse;
        this.distance=distance;
    }

    /**
     *
     * @return string representation of the object
     */
    @Override
    public String toString() {
        return "Route{" +
                "store=" + store +
                ", warehouse=" + warehouse +
                ", distance=" + distance +
                '}';
    }
}
