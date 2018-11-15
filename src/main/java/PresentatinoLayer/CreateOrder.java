/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer;

import FunctionLayer.GeneralException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
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
        LogicFacade lf = new LogicFacade();
        String width = request.getParameter("widthnumber");
        String length = request.getParameter("lengthnumber");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String evt = request.getParameter("evt");
        
        Order o = lf.makeOrder(Integer.parseInt(width), Integer.parseInt(length), name, email, zip, phone, evt);
        request.getSession().setAttribute("order", o);
       return "singleOrder";
    }
    
}
