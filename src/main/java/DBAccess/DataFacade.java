package DBAccess;

import FunctionLayer.Calculate.PriceCalculator;
import FunctionLayer.Entity.Material;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.LoginException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;

public class DataFacade {

    PriceCalculator p = new PriceCalculator();
    DataMapper dm = new DataMapper();

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @param state
     * @return
     * @throws DMException
     */
    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values.
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    public User login(String email, String password) throws LoginException {
        return dm.login(email, password);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @return @throws DMException
     */
    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }

    /**
     * This method calls the corresponding DataMapper method.
     * @param o
     * @throws DMException
     * @throws MakeOrderException
     */
    public void makeOrder(Order o) throws DMException, MakeOrderException {
        dm.createOrder(o);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @param orderid
     * @return
     * @throws DMException
     * @throws MakeOrderException
     */
    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        return dm.getOrderByID(orderid);

    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
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
        Order o = dm.EditOrder(orderId, desiredLength, desiredWidth, flatRoof, state);
        return o;
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @return @throws DMException
     */
    public ArrayList<Material> getMaterials() throws DMException {
        ArrayList<Material> list = dm.getMaterials();
        return list;
    }

}
