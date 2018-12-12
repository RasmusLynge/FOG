package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static final String URL = "jdbc:mysql://188.166.16.16:3306/FOG";
    private static final String USERNAME = "datamatiker2";
    private static final String PASSWORD = "gimig12";

    private static Connection singleton;

    /**
     * This method sets the connection to the one given in the parameter
     * @param con the connection.
     */
    public static void setConnection(Connection con) {
        singleton = con;
    }

    /**
     * This method checks if there is a connection already, and returns it. If there is not a connection instance already, it makes one and returns it.
     * Method is synchronized so there can never be 2 Connections
     * @return The connection instance
     * @throws ClassNotFoundException if the connection cannot be instantiated
     * @throws SQLException if there is no connections to the database
     */
    public static synchronized Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }
        return singleton;
    }

}
