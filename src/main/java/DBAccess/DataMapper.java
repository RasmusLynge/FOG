package DBAccess;

import FunctionLayer.GeneralException;
import FunctionLayer.Order;
import FunctionLayer.User;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

    public static void createOrder(Order order) {
        try {
            Connection con = Connector.connection();
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(d);
            String SQL = "INSERT INTO `Order` (`Width`, `Length`, `Flat_Roof`, `Date`) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getWidth());
            ps.setInt(2, order.getLength());
            ps.setInt(3, 1); // Fladt tage er sat til true
            ps.setString(4, currentTime);
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            String id = ids.getString(1);
            order.setId(id);

            String SQL2 = "INSERT INTO `User_Info` (`fk_Order_Id`, `Name`, `Email`, `Phone`, `Zip`)"
                    + "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps2 = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, order.getId());
            ps2.setString(2, order.getName());
            ps2.setString(3, order.getEmail());    
            ps2.setString(4, order.getPhone());
            ps2.setString(5, order.getZip());
            ids.next();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
    }

}
