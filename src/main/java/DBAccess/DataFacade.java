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

    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        return dm.getSpecificOrders(state);
    }

    public User login(String email, String password) throws LoginException {
        return dm.login(email, password);
    }

    public ArrayList<Order> getAllOrders() throws DMException {
        return dm.getAllOrders();
    }

    public void makeOrder(Order o) throws DMException, MakeOrderException {
        dm.createOrder(o);
    }

    public Order getOrderByID(int orderid) throws DMException, MakeOrderException {
        return dm.getOrderByID(orderid);

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
