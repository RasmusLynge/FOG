/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer;

import FunctionLayer.GeneralException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class CreateOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        
        String width = request.getParameter("width");
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String evt = request.getParameter("evt");
        
        System.out.println(" width: " + width + "name: " + name + " email: "+ email);
        //HttpSession session = request.getSession();
        
       return "orderpage";
    }
    
}
