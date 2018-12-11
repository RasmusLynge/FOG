package FunctionLayer.Exception;

/**
 * The purpose of LoginSampleException is to...
 * @author Rasmu
 */
public class DMException extends Exception {
    
    //Denne fejlbesked blive kun brog til login -- Skal muligvis skifte navn?

    public DMException(String msg) {
        
        super(msg);
        System.out.println(msg);
    }
    
    

}
