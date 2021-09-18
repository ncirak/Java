package si.um.opj.cirak.logic.ui;


import si.um.opj.cirak.logic.FoodItem;

import javax.swing.*;
import java.io.*;

//I-> Item
public class Serializer<I> {

    private DefaultListModel<I> Model;
    private String fileName;
    public Serializer(DefaultListModel<I> Model, String fileName){
        setModel(Model);
        this.fileName=fileName;
    }
    public void write(DefaultListModel<I> ModelX){
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            for (int i = 0; i < ModelX.size(); i++) {
                oos.writeObject(ModelX.get(i));
            }
            oos.flush();
            oos.close();
            System.out.println("Serialization is successful");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DefaultListModel<I> read(){

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));

            while(true) {
                I x = (I) ois.readObject();
                Model.addElement(x);
            }
        }catch(EOFException e) {

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
        }
        return Model;
    }

    public void setModel(DefaultListModel<I> model) {
        Model = model;
    }

    public DefaultListModel<I> getModel() {
        return Model;
    }
}
