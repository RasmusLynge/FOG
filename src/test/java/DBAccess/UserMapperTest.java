package DBAccess;

import FunctionLayer.Exception.DMException;
import FunctionLayer.Entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

    //Test negativ positiv grænseværdier lav ikke mere end en test pr. metode. 

//public class UserMapperTest {
////    Test date in the UsersTest table
////    INSERT INTO `UsersTest` VALUES 
////    (1,'jens@somewhere.com','jensen','customer'),
////    (2,'ken@somewhere.com','kensen','customer'),
////    (3,'robin@somewhere.com','batman','employee'),
////    (4,'someone@nowhere.com','sesam','customer');
//
//    private static Connection testConnection;
//    private static String USER = "datamatiker2";
//    private static String USERPW = "gimig12";
//    private static String DBNAME = "FOG_USER_TEST";
//    private static String HOST = "188.166.16.16";
//
//    @Before
//    public void setUp() {
//        try {
//            // awoid making a new connection for each test
//            if ( testConnection == null ) {
//                String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
//                Class.forName( "com.mysql.cj.jdbc.Driver" );
//
//                testConnection = DriverManager.getConnection( url, USER, USERPW );
//                // Make mappers use test 
//                Connector.setConnection( testConnection );
//            }
//            // reset test database
//            try ( Statement stmt = testConnection.createStatement() ) {
//                stmt.execute( "drop table if exists User_Login" );
//                stmt.execute( "create table User_Login like User_Login" );
//                stmt.execute( "insert into User_Login select * from UsersTest" );
//            }
//
//        } catch ( ClassNotFoundException | SQLException ex ) {
//            testConnection = null;
//            System.out.println( "Could not open connection to database: " + ex.getMessage() );
//        }
//    }
//
//    @Test
//    public void testSetUpOK() {
//        // Just check that we have a connection.
//        assertNotNull( testConnection );
//    }
//
//    @Test
//    public void testLogin01() throws GeneralException {
//        // Can we log in
//        User user = DataMapper.login( "jens@somewhere.com", "jensen" );
//        assertTrue( user != null );
//        
//        
//    }
//
//    @Test( expected = GeneralException.class )
//    public void testLogin02() throws GeneralException {
//        // We should get an exception if we use the wrong password
//        User user = DataMapper.login( "jens@somewhere.com", "larsen" );
//    }
//
//    @Test
//    public void testLogin03() throws GeneralException {
//        // Jens is supposed to be a customer
//        User user = DataMapper.login( "jens@somewhere.com", "jensen" );
//        assertEquals( "customer", user.getRole() );
//    }
//
//    @Test
//    public void testCreateUser01() throws GeneralException {
//        // Can we create a new user - Notice, if login fails, this will fail
//        // but so would login01, so this is OK
//        User original = new User( "king@kong.com", "uhahvorhemmeligt", "konge" );
//        DataMapper.createUser( original );
//        User retrieved = DataMapper.login( "king@kong.com", "uhahvorhemmeligt" );
//        assertEquals( "konge", retrieved.getRole());
//    }
//}