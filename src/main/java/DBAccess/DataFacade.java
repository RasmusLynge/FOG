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
     * @param state the state of the order
     * @return Returns an arraylist of orders with the corresponding state
     * @throws DMException if the query fails
     */
    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values.
     * @param email email of the user
     * @param password password of the user
     * @return returns the user if found
     * @throws LoginException
     */
    public User login(String email, String password) throws LoginException {
        return dm.login(email, password);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @return ArrayList of orders from the query
     * @throws DMException if the query fails
     */
    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }

    /**
     * This method calls the corresponding DataMapper method.
     * @param order the order that is to be inserted in the database
     * @throws DMException if the query fails
     * @throws MakeOrderException if it fails to make the order
     */
    public void makeOrder(Order order) throws DMException, MakeOrderException {
        dm.createOrder(order);
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @param orderid the ID of the order
     * @return Returns the order with the specified ID
     * @throws DMException if the query fails
     * @throws MakeOrderException if it fails to make the order
     */
    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        return dm.getOrderByID(orderid);

    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @param orderId the ID of the order
     * @param desiredWidth the new width of the order
     * @param desiredLength the new length of the order
     * @param flatRoof the new boolean value for roof 
     * @param state the new String state
     * @return returns the order after it has been updated in the database
     * @throws DMException if the query fails
     * @throws MakeOrderException if it fails to make the order
     */
    public Order EditOrder(int orderId, int desiredWidth, int desiredLength, int flatRoof, String state) throws DMException, MakeOrderException {
        Order o = dm.EditOrder(orderId, desiredLength, desiredWidth, flatRoof, state);
        return o;
    }

    /**
     * This method calls the corresponding DataMapper method and returns the return values. 
     * @return returns an ArrayList of Materials
     * @throws DMException if the query fails
     */
    public ArrayList<Material> getMaterials() throws DMException {
        ArrayList<Material> list = dm.getMaterials();
        return list;
    }

}
