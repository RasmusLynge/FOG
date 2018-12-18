package FunctionLayer.Exception;

public class DMException extends Exception {

    /**
     * This method is used as an exception for most SQL calls in this project it
     * is a custom exception that is thrown by methods in the DataMapper class
     *
     * @param msg That contains the error message from the SQL Exception catched
     * in the method throwing DMException.
     */
    public DMException(String msg) {

        super(msg);
    }

}
