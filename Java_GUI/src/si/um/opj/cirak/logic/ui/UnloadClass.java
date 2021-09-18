package si.um.opj.cirak.logic.ui;

import si.um.opj.cirak.logic.transport.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnloadClass implements ActionListener {

    private JComboBox<Vehicle> VehicleList;

    public UnloadClass(JComboBox<Vehicle> VehicleList){
        this.VehicleList=VehicleList;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //DefaultListModel<Vehicle> vehicleListModel = (DefaultListModel<Vehicle>) this.VehicleList.getModel();
        DefaultComboBoxModel <Vehicle> vehicleModel = (DefaultComboBoxModel<Vehicle>) this.VehicleList.getModel();

        Vehicle vehicleY =(Vehicle) this.VehicleList.getSelectedItem();
        vehicleY.unloadFoodItems();
        //System.out.println(vehicleY.getCargo()[0]);
    }
}
