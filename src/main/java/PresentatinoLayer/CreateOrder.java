/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer;

import FunctionLayer.GeneralException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Order;
import FunctionLayer.MakeOrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class CreateOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException,MakeOrderException {
        LogicFacade lf = new LogicFacade();
        String measurementtype = request.getParameter("measurements");
        Order o;

        String width = request.getParameter("widthnumber");
        String length = request.getParameter("lengthnumber");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String evt = request.getParameter("evt");
        System.out.println(measurementtype);
        
        if("outermeasurements".equals(measurementtype) & (Integer.parseInt(length)<325 || Integer.parseInt(width)<310)) throw new MakeOrderException("Længden eller bredden på din carports indre mål er under 240.");
        if ("outermeasurements".equals(measurementtype)) {
            o = lf.makeOrder(Integer.parseInt(width) - 70, Integer.parseInt(length) - 85, name, email, zip, phone, evt);
        } else {
            o = lf.makeOrder(Integer.parseInt(width), Integer.parseInt(length), name, email, zip, phone, evt);
        }
        request.getSession().setAttribute("order", o);
        return "singleOrder";
    }
}
