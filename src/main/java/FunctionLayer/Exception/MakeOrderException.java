package FunctionLayer.Exception;

public class MakeOrderException extends Exception {

    /**
     *
     * @param msg
     */
    public MakeOrderException(String msg) {
        super(msg);
        System.out.println(msg);
    }

}
