package FunctionLayer.Exception;

public class MakeOrderException extends Exception {

    /**
     * This method is used as an exception for issues regarding Making an order
     * It is a custom exception that is thrown by methods in the CreateOrder method 
     * It is mostly used to check that a customer is not making an order with invalid lengths, widths ect.
     * @param msg Contains the error message from the method that is throwing this exception
     */
    public MakeOrderException(String msg) {
        super(msg);
        System.out.println(msg);
    }

}
