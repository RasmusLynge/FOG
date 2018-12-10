/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PresentatinoLayer.Commands;

import FunctionLayer.Entity.Material;
import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Entity.Order;
import FunctionLayer.Exception.MakeOrderException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simon Bojesen
 */
public class GetOrderDetails extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        LogicFacade lf = new LogicFacade();
        int orderid = Integer.parseInt(request.getParameter("orderID")); 
        Order o = lf.getOrderByID(orderid);
        ArrayList<Material> materialList = o.getCarport().getList();
        request.setAttribute("order", o);
        request.setAttribute("materiallist", materialList);
        return "showorderdetails";
    }
}
