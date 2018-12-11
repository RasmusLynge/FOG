/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Calculate.PriceCalculator;
import FunctionLayer.Entity.Material;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.LoginException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;

/**
 * Dette skal bruges til at lave lavere kobling 
 * @author Rasmu
 */
public class DataFacade {
    PriceCalculator p = new PriceCalculator();
    DataMapper dm = new DataMapper();

    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }


    public User login(String email, String password) throws LoginException {
        return dm.login(email, password);
    }

//    public static User createUser(String email, String password) throws DMException {
//        User user = new User(email, password, "customer");
//        DataMapper.createUser(user);
//        return user;
//    }

    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }

//    public static ArrayList<Order> getOrdersByUserID(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public void makeOrder(Order o) throws DMException, MakeOrderException {
        dm.createOrder(o);
    }

    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        Order o = dm.getOrderByID(orderid);
        return o;
    }

    public Order EditOrder(int orderId, int desiredWidth, int desiredLength, int flatRoof, String state) throws DMException, MakeOrderException {
        Order o = dm.EditOrder(orderId, desiredLength, desiredWidth, flatRoof, state);
        return o;
    }
    public ArrayList<Material> getMaterials() throws DMException {
        ArrayList<Material> list = dm.getMaterials();
        return list;
    }

}
