package FunctionLayer.Exception;

public class LoginException extends Exception {

    /**
     * This method is used as an exception for login issues in this project
     * it is a custom exception that is thrown by methods in the DataMapper and Login class 
     * @param msg Contains the error message from the method that is throwing a LoginException.
     */
    public LoginException(String msg) {
        super(msg);
        System.out.println(msg);

    }
}
