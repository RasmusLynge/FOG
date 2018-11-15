package DBAccess;

import FunctionLayer.GeneralException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The purpose of UserMapper is to...
 *
 * @author Rasmus
 */
public class DataMapper {

    public static void createUser(User user) throws GeneralException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO User_Login (Email, Password, Role) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            String id = ids.getString(1);
            user.setId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new GeneralException(ex.getMessage());
        }
    }

    public static User login(String email, String password) throws GeneralException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT User_Id, Role FROM User_Login "
                    + "WHERE Email=? AND Password=?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String id = rs.getString("User_Id");
                User user = new User(email, password, role);
                user.setId(id);
                return user;
            } else {
                throw new GeneralException("Could not validate user");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new GeneralException(ex.getMessage());
        }
    }

    public static HashMap<String, Double> getPrices() throws GeneralException {
        try {
            Connection con = Connector.connection();
            String SQL = "select `name`, `price` from Material";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            HashMap<String, Double> map = new HashMap<>();
            while (rs.next()) {
                String name = rs.getString("Name");
                double price = rs.getDouble("Price");
                map.put(name, price);
            }
            return map;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new GeneralException(ex.getMessage());
        }
    }
    
    public static ArrayList<Order> getAllOrders() throws GeneralException {
        ArrayList<Order> ol = new ArrayList<>();
         try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `Order` "
                       + "INNER JOIN `User_Info` "
                       + "ON `Order`.Id_Order = User_Info.fk_Order_Id;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("width"), rs.getInt("length"), rs.getString("name"), rs.getString("email"), rs.getString("zip"), rs.getString("phone"), rs.getString("evt"));
                order.setId(String.valueOf(rs.getInt("Id_Order")));
                ol.add(order);
            }
            return ol;
        } catch ( ClassNotFoundException | SQLException ex ) {
            throw new GeneralException(ex.getMessage());
        }
    }

}
