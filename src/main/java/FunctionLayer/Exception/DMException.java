package FunctionLayer.Exception;

public class DMException extends Exception {

    /**
     *
     * @param msg
     */
    public DMException(String msg) {

        super(msg);
        System.out.println(msg);
    }

}
