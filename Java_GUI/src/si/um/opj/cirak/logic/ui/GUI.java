package si.um.opj.cirak.logic.ui;

import si.um.opj.cirak.logic.*;
import si.um.opj.cirak.logic.facility.Location;
import si.um.opj.cirak.logic.facility.Store;
import si.um.opj.cirak.logic.facility.VolumeExceededException;
import si.um.opj.cirak.logic.facility.Warehouse;
import si.um.opj.cirak.logic.transport.Truck;
import si.um.opj.cirak.logic.transport.Van;
import si.um.opj.cirak.logic.transport.Vehicle;

import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUI extends JFrame implements ActionListener, Serializable {
    private JPanel MainPanel;
    private JPanel cards;
    private JButton CREATEButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JList Choose;
    private JTabbedPane tabbedPane1;
    private JPanel WarehouseCard;
    private JButton addFoodItemToButton;
    private JPanel Vehicle;
    private JButton LOADButton;
    private JButton UNLOADButton;
    private JTextArea youCanLoadAndTextArea;
    private JTabbedPane tabbedPaneMan;
    private JComboBox facilitiesType;
    private JPanel business;
    private JComboBox vehicleType;
    private JTextField registrationNum;
    private JTextField volume;
    private JTextField maxWeight;
    private JTextField speed;
    private JTextField vehicleCapacity;
    private JTextField numTrailer;
    private JTextField facilityLocationCity;
    private JTextField labelFood;
    private JTextField foodVol;
    private JTextField foodWeight;
    private JTextField expDate;
    private JComboBox foodItemTypeSelect;
    private JList wrhFoodList;
    private JButton addItemToWarehouseButton;
    private JComboBox vehicleComboListName;
    private JComboBox vanFoodType;
    private JComboBox wrhNameList;
    private JList foodListVehicle;
    private JList warehouseName;
    private JList storeNames;
    private JTextField facilitiesName;
    private JList truckList;
    private JList vanList;
    private JList foodListItem;
    private JList manWrhFood;
    private JTextField facilityCountry;
    private JTextField wrhCapacity;
    private JButton deleteEverythingButton;

    //food item list for each Jlist
    private DefaultListModel <FoodItem> foodItemsModel =new DefaultListModel<FoodItem>();
    //Warehouse list for warehouse combo Box only
    private DefaultComboBoxModel <Warehouse> wrhModel =new DefaultComboBoxModel<Warehouse>();
    //vehicle combo box list model
    private DefaultComboBoxModel <Vehicle> vehicleModel =new DefaultComboBoxModel<Vehicle>();

    private DefaultListModel <Warehouse> warehouseModel =new DefaultListModel<Warehouse>();
    private DefaultListModel <Store> storeModel =new DefaultListModel<Store>();
    private DefaultListModel <Truck> truckModel =new DefaultListModel<Truck>();
    private DefaultListModel <Van> vanModel =new DefaultListModel<Van>();

    Map foodItemTypeMap =new HashMap();

    Serializer<FoodItem> serializerFood= new Serializer<FoodItem>(foodItemsModel ,"foodItem.ser");
    Serializer<Truck> serializerTruck= new Serializer<Truck>(truckModel,"truck.ser");
    Serializer<Van> serializerVan= new Serializer<Van>(vanModel,"van.ser");
    Serializer<Warehouse> serializerWrh= new Serializer<Warehouse>(warehouseModel,"warehouse.ser");
    Serializer<Store> serializerStore= new Serializer<Store>(storeModel,"store.ser");

    public GUI() {
        foodItemTypeMap.put(0,FoodItemType.FRESH);
        foodItemTypeMap.put(1,FoodItemType.FROZEN);

        foodItemsModel=serializerFood.read();
        truckModel=serializerTruck.read();
        vanModel =serializerVan.read();
        warehouseModel=serializerWrh.read();
        storeModel=serializerStore.read();

        foodListVehicle.setModel(foodItemsModel);
        wrhFoodList.setModel(foodItemsModel);
        foodListItem.setModel(foodItemsModel);

        wrhNameList.setModel(wrhModel);

        warehouseName.setModel(warehouseModel);
        storeNames.setModel(storeModel);

        truckList.setModel(truckModel);
        vanList.setModel(vanModel);

        for (int i = 0; i <warehouseModel.size() ; i++) {
            wrhModel.addElement(warehouseModel.elementAt(i));
        }
        for (int i = 0; i <truckModel.size() ; i++) {
            vehicleModel.addElement(truckModel.elementAt(i));
        }
        for (int i = 0; i <vanModel.size() ; i++) {
            vehicleModel.addElement(vanModel.elementAt(i));
        }
        addItemToWarehouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Warehouse willAdd= (Warehouse) wrhNameList.getSelectedItem();
                willAdd.addItem(foodItemsModel.get(wrhFoodList.getSelectedIndex()));
            }
        });
        vehicleComboListName.setModel(vehicleModel);
        LOADButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle vehicleX =(Vehicle) vehicleComboListName.getSelectedItem();
                List indices=  foodListVehicle.getSelectedValuesList();
                try {
                    for (int i = 0; i <indices.size() ; i++) {
                        vehicleX.loadFoodItem((FoodItem) indices.get(i));
                    }
                } catch (CapacityExceededException capacityExceededException) {
                    capacityExceededException.printStackTrace();
                } catch (VolumeExceededException volumeExceededException) {
                    volumeExceededException.printStackTrace();
                } catch (FoodItemTypeException foodItemTypeException) {
                    foodItemTypeException.printStackTrace();
                }
            }
        });
        UNLOADButton.addActionListener(
                new UnloadClass(vehicleComboListName)
        );
        CREATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex= tabbedPaneMan.getSelectedIndex();

                if (tabIndex==0)//business facility
                {
                    String city =facilityLocationCity.getText();
                    String country= facilityCountry.getText();
                    Location loc = new Location (city, country);
                    String name= facilitiesName.getText();

                    //warehouse create
                    if (facilitiesType.getSelectedIndex()==0){
                        int capacity =Integer.parseInt(wrhCapacity.getText());
                        Warehouse wrh = new Warehouse(name, loc, capacity);
                        wrhModel.addElement(wrh);
                        warehouseModel.addElement(wrh);
                    }
                    else if (facilitiesType.getSelectedIndex()==1)//store create
                    {
                        Store str = new Store(name,loc);
                        storeModel.addElement(str);
                    }
                    facilityLocationCity.setText("");
                    facilityCountry.setText("");
                    wrhCapacity.setText("");
                    facilitiesName.setText("");
                }
                else if (tabIndex==1)//vehicle creation
                {
                    int regNum= Integer.parseInt(registrationNum.getText());
                    int avgSpeed=Integer.parseInt(speed.getText());
                    int volVehicle= Integer.parseInt(volume.getText());
                    int maxW =Integer.parseInt(maxWeight.getText());
                    int capacityV = Integer.parseInt(vehicleCapacity.getText());

                    //truck
                    if(vehicleType.getSelectedIndex()==0){
                        int numTra = Integer.parseInt(numTrailer.getText());
                        Truck truck1= new Truck (regNum,avgSpeed,volVehicle,maxW,capacityV,numTra);

                        truckModel.addElement(truck1);
                        vehicleModel.addElement(truck1);
                        numTrailer.setText("");
                    }
                    else if(vehicleType.getSelectedIndex()==1){
                        FoodItemType type = (FoodItemType) foodItemTypeMap.get(vanFoodType.getSelectedIndex());

                        Van van1= new Van(regNum,avgSpeed,volVehicle,maxW,capacityV,type);
                        vanModel.addElement(van1);
                        vehicleModel.addElement(van1);
                    }
                    registrationNum.setText("");
                    speed.setText("");
                    volume.setText("");
                    maxWeight.setText("");
                    vehicleCapacity.setText("");
                }
                else if (tabIndex==2)//food item creation
                {
                    String label= labelFood.getText();
                    double volume= Double.parseDouble(foodVol.getText());
                    int weight= Integer.parseInt(foodWeight.getText());
                    LocalDate expirationDate= LocalDate.parse(expDate.getText());

                    FoodItemType type = (FoodItemType) foodItemTypeMap.get(foodItemTypeSelect.getSelectedIndex());

                    FoodItem foodItem = new FoodItem(label, volume,weight,expirationDate,type);
                    System.out.println("Food Item Created "+foodItem.toString());

                    foodItemsModel.addElement(foodItem);
                    labelFood.setText("");
                    foodVol.setText("");
                    foodWeight.setText("");
                    expDate.setText("");
                }
                serializerStore.write(storeModel);
                serializerWrh.write(warehouseModel);
                serializerFood.write(foodItemsModel);
                serializerTruck.write(truckModel);
                serializerVan.write(vanModel);
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex= tabbedPaneMan.getSelectedIndex();

                if (tabIndex==0)//business facility
                {
                    if (facilitiesType.getSelectedIndex()==0){
                        wrhModel.removeElementAt(warehouseName.getSelectedIndex());
                        warehouseModel.remove(warehouseName.getSelectedIndex());
                    }
                    else if (facilitiesType.getSelectedIndex()==1){
                        storeModel.remove(storeNames.getSelectedIndex());
                    }
                }
                else if (tabIndex==1)//vehicle
                {
                    if(vehicleType.getSelectedIndex()==0){
                        int [] deleteTruckIndices= truckList.getSelectedIndices();
                        for (int i = 0; i < deleteTruckIndices.length; i++) {
                            vehicleModel.removeElementAt(deleteTruckIndices[i]);
                            truckModel.remove(deleteTruckIndices[i]);
                        }
                    }
                    else if(vehicleType.getSelectedIndex()==1){
                        int [] deleteVanIndices= vanList.getSelectedIndices();
                        for (int i = 0; i < deleteVanIndices.length; i++) {
                            vanModel.remove(deleteVanIndices[i]);
                            truckModel.remove(deleteVanIndices[i]);
                        }
                    }
                }
                else if (tabIndex==2)//food item
                {
                   int [] deleteFoodIndices= foodListItem.getSelectedIndices();
                   for (int i = 0; i < deleteFoodIndices.length; i++) {
                        foodItemsModel.remove(deleteFoodIndices[i]);
                    }
                }
                serializerStore.write(storeModel);
                serializerWrh.write(warehouseModel);
                serializerFood.write(foodItemsModel);
                serializerTruck.write(truckModel);
                serializerVan.write(vanModel);
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tabIndex= tabbedPaneMan.getSelectedIndex();
                if (tabIndex==0)//business facility
                {
                    String city, country, name;
                    Location loc;
                    if (!facilityLocationCity.getText().isEmpty() && !facilityCountry.getText().isEmpty() && !facilitiesName.getText().isEmpty()) {
                        city = facilityLocationCity.getText();
                        country = facilityCountry.getText();
                        loc = new Location(city, country);
                        name = facilitiesName.getText();
                        //warehouse create
                        if (facilitiesType.getSelectedIndex() == 0) {
                            int capacity;
                            if (!wrhCapacity.getText().isEmpty()) {
                                capacity = Integer.parseInt(wrhCapacity.getText());
                                Warehouse wrh = warehouseModel.get(warehouseName.getSelectedIndex());
                                wrh.setName(name);
                                wrh.setLocation(loc);
                                wrh.setFoodItems(new FoodItem[capacity]);
                            }
                        } else if (facilitiesType.getSelectedIndex() == 1)//store create
                        {
                            Store str = storeModel.get(storeNames.getSelectedIndex());
                            str.setName(name);
                            str.setLocation(loc);
                        }
                        facilityLocationCity.setText("");
                        facilityCountry.setText("");
                        wrhCapacity.setText("");
                        facilitiesName.setText("");
                    }
                }
                else if (tabIndex==1)//vehicle creation
                {
                    if(!registrationNum.getText().isEmpty() && !speed.getText().isEmpty() && !volume.getText().isEmpty() &&!maxWeight.getText().isEmpty() &&!vehicleCapacity.getText().isEmpty()) {
                        int regNum = Integer.parseInt(registrationNum.getText());
                        int avgSpeed = Integer.parseInt(speed.getText());
                        int volVehicle = Integer.parseInt(volume.getText());
                        int maxW = Integer.parseInt(maxWeight.getText());
                        int capacityV = Integer.parseInt(vehicleCapacity.getText());
                        //truck
                        if (vehicleType.getSelectedIndex() == 0) {
                            if (!numTrailer.getText().isEmpty()) {
                                int numTra = Integer.parseInt(numTrailer.getText());
                                Truck truck1 = truckModel.get(truckList.getSelectedIndex());
                                Truck truck2 = (Truck) serializerTruck.getModel().get(truckList.getSelectedIndex());
                                truck1.setNumberOfTrailers(numTra);
                                truck1.allSetterVehicle(regNum,avgSpeed,maxW,volVehicle,capacityV);
                                truck2.setNumberOfTrailers(numTra);
                                truck2.allSetterVehicle(regNum,avgSpeed,maxW,volVehicle,capacityV);
                                numTrailer.setText("");
                            }
                        } else if (vehicleType.getSelectedIndex() == 1) {
                            FoodItemType type = (FoodItemType) foodItemTypeMap.get(vanFoodType.getSelectedIndex());

                            Van van1 = vanModel.get(vanList.getSelectedIndex());//new Van(regNum, avgSpeed, volVehicle, maxW, capacityV, type);
                            van1.allSetterVehicle(regNum, avgSpeed, volVehicle, maxW, capacityV);
                            van1.setFoodItemType(type);
                            Van van2 = (Van) serializerVan.getModel().get(vanList.getSelectedIndex());
                            van2.allSetterVehicle(regNum, avgSpeed, volVehicle, maxW, capacityV);
                            van2.setFoodItemType(type);
                        }
                        registrationNum.setText("");
                        speed.setText("");
                        volume.setText("");
                        maxWeight.setText("");
                        vehicleCapacity.setText("");
                    }
                }
                else if (tabIndex==2)//food item update
                {

                    if (!labelFood.getText().isEmpty() && !foodVol.getText().isEmpty() && !foodWeight.getText().isEmpty() &&!expDate.getText().isEmpty()) {
                        String label = labelFood.getText();
                        double volume = Double.parseDouble(foodVol.getText());
                        int weight = Integer.parseInt(foodWeight.getText());
                        LocalDate expirationDate = LocalDate.parse(expDate.getText());

                        FoodItemType type = (FoodItemType) foodItemTypeMap.get(foodItemTypeSelect.getSelectedIndex());

                        FoodItem food = foodItemsModel.get(foodListItem.getSelectedIndex());//new FoodItem(label, volume, weight, expirationDate, type);

                        FoodItem serial = (FoodItem) serializerFood.getModel().get(foodListItem.getSelectedIndex());
                        food.setFoodItemType(type);
                        food.setExpirationDate(expirationDate);
                        food.setLabel(label);
                        food.setVolume(volume);
                        food.setWeight(weight);
                        //serialization food item model update
                        serial.setFoodItemType(type);
                        serial.setExpirationDate(expirationDate);
                        serial.setLabel(label);
                        serial.setVolume(volume);
                        serial.setWeight(weight);

                        System.out.println("Food Item Updated " + food.toString());
                        labelFood.setText("");
                        foodVol.setText("");
                        foodWeight.setText("");
                        expDate.setText("");
                    }
                }
                serializerStore.write(storeModel);
                serializerWrh.write(warehouseModel);
                serializerFood.write(foodItemsModel);
                serializerTruck.write(truckModel);
                serializerVan.write(vanModel);
            }
        });
        deleteEverythingButton.addActionListener(
                new anonymousInnerClass()
        );
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java Management GUI");
        frame.setContentPane(new GUI().MainPanel);
        frame.setSize(300,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { new GUI();}
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    class anonymousInnerClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            truckModel.clear();
            foodItemsModel.clear();
            storeModel.clear();
            warehouseModel.clear();
            wrhModel.removeAllElements();
            vanModel.clear();
            vehicleModel.removeAllElements();

            serializerTruck.write(truckModel);
            serializerFood.write(foodItemsModel);
            serializerWrh.write(warehouseModel);
            serializerVan.write(vanModel);
            serializerStore.write(storeModel);
            System.out.println("All model and their elements are deleted!");
        }
    }
}
