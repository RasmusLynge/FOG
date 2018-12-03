/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Entity.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon Bojesen
 */
public class EditOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        int id = Integer.parseInt(request.getParameter("orderID"));
        int width = Integer.parseInt(request.getParameter("width"));
        int length = Integer.parseInt(request.getParameter("length"));
        int roof = Integer.parseInt(request.getParameter("roof"));
        String state = request.getParameter("State");
        try {
            Order o = LogicFacade.EditOrder(id, width, length, roof, state);
            request.setAttribute("order", o);
            return "showorderdetails";

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error");
        }
        return null;
    }
}
