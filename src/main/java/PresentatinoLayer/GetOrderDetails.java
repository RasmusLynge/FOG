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

/**
 *
 * @author Simon Bojesen
 */
public class GetOrderDetails extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        int orderid = Integer.parseInt(request.getParameter("orderID")); 
        Order o = LogicFacade.getOrderByID(orderid);
        request.setAttribute("order", o);
        return "showorderdetails";
    }
}
