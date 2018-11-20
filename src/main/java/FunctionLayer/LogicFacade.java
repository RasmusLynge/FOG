/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import DBAccess.DataMapper;
import java.util.ArrayList;

public class LogicFacade {

    PriceCalculator p = new PriceCalculator();
    DataMapper dm = new  DataMapper();
    

    public static User login(String email, String password) throws GeneralException {
        return DataMapper.login(email, password);
    }

    public static User createUser(String email, String password) throws GeneralException {
        User user = new User(email, password, "customer");
        DataMapper.createUser(user);
        return user;
    }

    public static ArrayList<Order> getAllOrders() throws GeneralException {
        return DataMapper.getAllOrders();
    }

    public static ArrayList<Order> getOrdersByUserID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Order makeOrder(int width, int length, String name, String email, String zip, String phone, String evt) throws GeneralException{
        Order o = new Order(width, length, name, email, zip, phone, evt);
        o.setPrice(p.priceCalculator(length, width));
        System.out.println("order -------------------------------------" +o);
        dm.createOrder(o);
        return o;
    }
    
    public static Order getOrderByID(int orderid) throws GeneralException {
        Order o = DataMapper.getOrderByID(orderid);
        return o;
    }
}