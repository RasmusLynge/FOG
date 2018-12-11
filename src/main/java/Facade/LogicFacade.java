package Facade;

import DBAccess.DataFacade;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Calculate.PriceCalculator;
import FunctionLayer.Entity.User;
import FunctionLayer.Entity.Order;
import DBAccess.DataMapper;
import FunctionLayer.Exception.LoginException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;

public class LogicFacade {

    PriceCalculator p = new PriceCalculator();
    DataMapper dm = new DataMapper();
    DataFacade df = new DataFacade();

    /**
     *
     * @param state
     * @return
     * @throws DMException
     */
    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    public User login(String email, String password) throws LoginException {
        return DataMapper.login(email, password);
    }

    /**
     *
     * @return @throws DMException
     */
    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }

//    public static ArrayList<Order> getOrdersByUserID(String id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    /**
     *
     * @param width
     * @param length
     * @param name
     * @param email
     * @param zip
     * @param phone
     * @param evt
     * @param shed
     * @param highRoof
     * @param shedLength
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public Order makeOrder(int width, int length, String name, String email, String zip, String phone, String evt, boolean shed, boolean highRoof, int shedLength) throws DMException, MakeOrderException {
        Order o = new Order(width, length, name, email, zip, phone, evt);
        o.setPrice(p.priceCalculator(length, width, highRoof, shed));
        o.setCarport(p.getCarport());
        o.setShed(shed);
        o.setShedLength(shedLength);
        o.setFlat_roof(highRoof);
        df.makeOrder(o);
        return o;
    }

    /**
     *
     * @param orderid
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        Order o = df.getOrderByID(orderid);

        o.setPrice(p.priceCalculator(o.getLength(), o.getWidth(), o.isShed(), o.isFlat_roof()));
        o.setCarport(p.getCarport());
        return o;
    }

    /**
     *
     * @param orderId
     * @param desiredWidth
     * @param desiredLength
     * @param flatRoof
     * @param state
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public Order EditOrder(int orderId, int desiredWidth, int desiredLength, int flatRoof, String state) throws DMException, MakeOrderException {
        Order o = df.EditOrder(orderId, desiredLength, desiredWidth, flatRoof, state);
        o.setPrice(p.priceCalculator(o.getLength(), o.getWidth(), o.isShed(), o.isFlat_roof()));
        o.setCarport(p.getCarport());
        return o;
    }
}
