/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import Facade.LogicFacade;
import FunctionLayer.Entity.User;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class CreateEmployeeUsers extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, LoginException {
        LogicFacade lf = new LogicFacade();
        String email = request.getParameter( "email" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        if ( password1.equals( password2 ) ) {
            User user = lf.createEmployeeUser(email, password1 );
            HttpSession session = request.getSession();
            return "adminpage";
        } else {
            throw new LoginException( "the two passwords did not match" );
        }
    }
    
}
