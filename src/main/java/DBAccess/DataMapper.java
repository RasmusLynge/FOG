package DBAccess;

import FunctionLayer.Entity.Material;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Entity.Order;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.LoginException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DataMapper {

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws LoginException
     */
    public static User login(String email, String password) throws LoginException {
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
                throw new LoginException("Forkert email eller adgangskode");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new LoginException(ex.getMessage());
        }
    }

    /**
     *
     * @param orderid
     * @return
     * @throws DMException
     */
    public Order getOrderByID(int orderid) throws DMException {
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `Order` "
                    + "INNER JOIN `User_Info` "
                    + "ON `Order`.Id_Order = User_Info.fk_Order_Id "
                    + "WHERE `Order`.Id_Order = " + orderid + ";";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("width"), rs.getInt("length"), rs.getString("name"), rs.getString("email"), rs.getString("zip"), rs.getString("phone"), rs.getString("evt"));
                if (rs.getInt("Flat_Roof") == 1) {
                    order.setFlat_roof(true);
                } else {
                    order.setFlat_roof(false);
                }
                order.setState(rs.getString("State"));
                order.setOrderdate(rs.getDate("Date").toString());
                order.setId(String.valueOf(orderid));
                return order;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
        return null;
    }

    /**
     *
     * @return
     * @throws DMException
     */
    public ArrayList<Order> getAllOrders() throws DMException {
        ArrayList<Order> ol = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `Order` "
                    + "INNER JOIN `User_Info` "
                    + "ON `Order`.Id_Order = User_Info.fk_Order_Id;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("width"), rs.getInt("length"), rs.getString("name"), rs.getString("email"), rs.getString("zip"), rs.getString("phone"), "evt");
                order.setId(String.valueOf(rs.getInt("Id_Order")));
                order.setOrderdate(rs.getString("Date"));
                order.setState(rs.getString("State"));
                ol.add(order);
            }
            return ol;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }

    /**
     *
     * @param state
     * @return
     * @throws DMException
     */
    public ArrayList<Order> getSpecificOrders(String state) throws DMException {
        ArrayList<Order> ol = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "select * \n"
                    + "FROM `Order`\n"
                    + "INNER JOIN `User_Info` ON `Order`.Id_Order = User_Info.fk_Order_Id\n"
                    + "WHERE `State` =?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, state);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(rs.getInt("width"), rs.getInt("length"), rs.getString("name"), rs.getString("email"), rs.getString("zip"), rs.getString("phone"), "evt");
                order.setId(String.valueOf(rs.getInt("Id_Order")));
                order.setOrderdate(rs.getString("Date"));
                order.setState(rs.getString("State"));
                ol.add(order);
            }
            return ol;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }

    /**
     *
     * @param order
     * @throws FunctionLayer.Exception.DMException
     */
    public void createOrder(Order order) throws DMException {
        try {
            Connection con = Connector.connection();
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(d);
            String SQL = "INSERT INTO `Order` (`Width`, `Length`, `Flat_Roof`, `Date`, `Shed`, `ShedLength`, `Evt`)  VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getWidth());
            ps.setInt(2, order.getLength());
            ps.setInt(3, order.isFlat_roof() ? 1 : 0); // Fladt tage er sat til true
            ps.setString(4, currentTime);
            ps.setInt(5, order.isShed() ? 1 : 0);
            ps.setInt(6, order.getShedLength());
            ps.setString(7, order.getEvt());

            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            order.setId("" + id);

            SQL = "INSERT INTO `User_Info` (`fk_Order_Id`, `Name`, `Email`, `Phone`, `Zip`) "
                    + "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement ps2 = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps2.setInt(1, id);
            ps2.setString(2, order.getName());
            ps2.setString(3, order.getEmail());
            ps2.setString(4, order.getPhone());
            ps2.setString(5, order.getZip());
            ps2.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }

    /**
     *
     * @param orderId
     * @param desiredLength
     * @param desiredWidth
     * @param flatRoof
     * @param state
     * @return
     * @throws DMException
     */
    public Order EditOrder(int orderId, int desiredLength, int desiredWidth, int flatRoof, String state) throws DMException {
        try {
            Connection con = Connector.connection();
            String SQL = "Update `Order`\n"
                    + "SET Length = ?, Width = ?, Flat_Roof = ?, State = ?\n"
                    + "WHERE `Id_Order` = ?;";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, desiredLength);
            ps.setInt(2, desiredWidth);
            ps.setInt(3, flatRoof);
            ps.setString(4, state);
            ps.setInt(5, orderId);
            ps.executeUpdate();

            Order o = getOrderByID(orderId);
            return o;

        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }

    /**
     *
     * @return
     * @throws DMException
     */
    public ArrayList<Material> getMaterials() throws DMException {
        ArrayList<Material> ml = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `Name`, `Length`, `Price` FROM `Material`";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                int length = rs.getInt("Length");
                int amount = 0;
                double price = rs.getDouble("Price");
                ml.add(new Material(name, length, amount, price));
            }
            return ml;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }
}
