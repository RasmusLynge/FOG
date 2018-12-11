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
     *
     * @param con
     */
    public static void setConnection(Connection con) {
        singleton = con;
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static synchronized Connection connection() throws ClassNotFoundException, SQLException {
        if (singleton == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            singleton = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        }
        return singleton;
    }

}
