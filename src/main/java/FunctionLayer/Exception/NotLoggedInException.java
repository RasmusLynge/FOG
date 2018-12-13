/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Exception;

/**
 *
 * @author Rasmu
 */
public class NotLoggedInException extends Exception {
    
        public NotLoggedInException(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
