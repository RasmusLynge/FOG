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
     * Takes the login credentials as parameters and checks if there is a
     * corresponding user in the database.
     *
     * @param email the email of the user
     * @param password the password of the user
     * @return Returns the user created from the retrieved values in the
     * database.
     * @throws LoginException If there is not a matching user it throws a login
     * exception.
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
     * Creates a user with the given password and email.
     *
     * @throws DMException If there is not a matching user it throws a login
     * exception.
     */
    public static void createUser(User user) throws DMException {
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
            throw new DMException(ex.getMessage());
        }
    }

    /**
     * Queries the database for an order with the ID specified in the parameter.
     * Makes an order instance with the retrieved data and returns it.
     *
     * @param orderid The id of the order
     * @return Returns the order
     * @throws DMException Throws DMException if the query fails
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
     * Queries the database for everything in the order table with corresponding
     * user info, makes new orders for each and returns them in an ArrayList
     *
     * @return returns an ArrayList of Orders with the values retrieved from the
     * database
     * @throws DMException if the query fails
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
     * Queries the database for all orders with the state specified in the
     * parameter. Makes new order for each and returns them in an ArrayList
     *
     * @param state the state the database is queried for
     *
     * @return An ArrayList of orders
     * @throws DMException if the query fails
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
     * Takes an order as parameter and inserts the data fromn it into the
     * database with 2 SQL queries, 1 for the `order ` table and 1 for the
     * `user_info`
     *
     *
     * @param order the order that is inserted in the database
     * @throws FunctionLayer.Exception.DMException if the query fails
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
     * This method updates an order specified by the ID in the parameter with
     * the values from the other parameters
     *
     * @param orderId The ID of the order that is to be edited
     * @param desiredLength the length the order should be updated to
     * @param desiredWidth the width the order should be updated to
     * @param flatRoof the boolean value the order should be updated to
     * @param state the String state that the order should be updated to
     * @return Returns the new order
     * @throws DMException if the query fails
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
     * Queries the database for all the materials in the table, makes new
     * Material instances for each, and returns them in an ArrayList
     *
     * @return An ArrayList of Material
     * @throws DMException if the query fails
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

     /**
     * Creates a list of all users from the database.
     * 
     * @return An ArrayList of User
     * @throws DMException If the query fails
     */
    public ArrayList<User> getAllEmployeeUsers() throws DMException {
        ArrayList<User> userl = new ArrayList();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT `email`, `User_Id`"
                    + "FROM `User_Login`"
                    + "WHERE `Role` = 'employee';";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("Email"), "123", "employee");
                user.setId(String.valueOf(rs.getInt("User_Id")));

                userl.add(user);
            }
            return userl;
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DMException(ex.getMessage());
        }
    }

     /**
     * Creates a list of all users from the database.
     * 
     * @param userId the user_Id og the user that should be deleted
     * @throws DMException If the query fails
     */
    void deleteUser(int userId) throws DMException{
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `User_Login` WHERE `User_Id` = ?;";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DMException(ex.getMessage());
        }
    }
}
