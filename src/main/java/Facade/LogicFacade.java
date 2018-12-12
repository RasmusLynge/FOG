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
     *This method is used to create an arraylist of orders for the salesrep to view.
     *it takes a state as parameter because thats how we sort orders.
     *there are 5 different states: requested, awaiting salesrep, paid, shipped and cancelled orders.
     * @param state
     * @return an arraylist with statespecific orders.
     * @throws DMException
     */
    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }

    /**
     *This method is used to login the salesrep so he can handle orders on the website
     * it takes an email and a password to login so we have those as our parameters
     * @param email
     * @param password
     * @return when it has finished logging in the method then returns the user object so we can set it on a session.
     * @throws LoginException if password and or email is wrongly written 
     */
    public User login(String email, String password) throws LoginException {
        return DataMapper.login(email, password);
    }

    /**
     *This method simply takes all orders in the system and puts them in an arraylist.
     * @return an arraylist filled with all orders in the system
     * @throws DMException if it cacthes ClassNotFoundException | SQLException
     */
    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }
    
    /**
     *This method takes a whole bunch of parameters
     * saves them to the created Order object and calculates the price
     * then it saves the order in the database
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
     * @return a finished order object
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
     *This method gets a single order from the orderid which is a unique key.
     * calculates everything and sends it back with return
     * @param orderid
     * @return a specific order object determined by given orderid
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
     *This method is called when the salesrep wants to change some details to a specific order
     * it then changes whatever needs changing, updates the price and the rest of the details and 
     * return the refreshed order object
     * @param orderId
     * @param desiredWidth
     * @param desiredLength
     * @param flatRoof
     * @param state
     * @return an updated and refreshed order object
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
