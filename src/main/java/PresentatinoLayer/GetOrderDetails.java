/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PresentatinoLayer;

import FunctionLayer.GeneralException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon Bojesen
 */
public class GetOrderDetails extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        return "showorderdetails";
    }
}
