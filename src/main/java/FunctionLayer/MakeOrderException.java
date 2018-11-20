/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 *
 * @author Mathias
 */
public class MakeOrderException extends Exception {

    public MakeOrderException(String msg) {
        super(msg);
        System.out.println(msg);
    }
    
    

}
