/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBAccess;

import FunctionLayer.Calculate.PriceCalculator;
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
        return DataMapper.getSpecificOrders(state);
    }


    public static User login(String email, String password) throws LoginException {
        return DataMapper.login(email, password);
    }

//    public static User createUser(String email, String password) throws DMException {
//        User user = new User(email, password, "customer");
//        DataMapper.createUser(user);
//        return user;
//    }

    public static ArrayList<Order> getAllOrders() throws DMException {
        return DataMapper.getAllOrders();
    }

//    public static ArrayList<Order> getOrdersByUserID(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public Order makeOrder(Order o) throws DMException, MakeOrderException {

        dm.createOrder(o);
        return o;
    }

    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        Order o = DataMapper.getOrderByID(orderid);

        o.setPrice(p.priceCalculator(o.getLength(), o.getWidth(), o.isShed(), o.isFlat_roof()));
        o.setCarport(p.getCarport());
        return o;
    }

    public Order EditOrder(int orderId, int desiredWidth, int desiredLength, int flatRoof, String state) throws DMException, MakeOrderException {
        Order o = dm.EditOrder(orderId, desiredLength, desiredWidth, flatRoof, state);
        o.setPrice(p.priceCalculator(o.getLength(), o.getWidth(), o.isShed(), o.isFlat_roof()));
        o.setCarport(p.getCarport());
        return o;
    }

}
