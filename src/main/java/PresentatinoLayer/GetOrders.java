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
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Simon Bojesen
 */
public class GetOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        ArrayList<Order> ol = new ArrayList();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if ("customer".equals(user.getRole())) {
            ol = LogicFacade.getOrdersByUserID(user.getId());
            if (ol.isEmpty()) {
                System.out.println("Tom liste");
            }
            else {
                session.setAttribute("ordersByUserID", ol);
            }
        } 
        else if ("employee".equals(user.getRole())) {
            ol = LogicFacade.getAllOrders();
            if (ol.isEmpty()) {
                System.out.println("Tom liste");
            }
            else {
                session.setAttribute("getAllOrders", ol);
            }
        }
        return "showorders";
    }
}
