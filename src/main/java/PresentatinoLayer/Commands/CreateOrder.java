/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import FunctionLayer.Exception.GeneralException;
import FunctionLayer.LogicFacade;
import FunctionLayer.Exception.MakeOrderException;
import FunctionLayer.Entity.Order;
import PresentatinoLayer.SVG.SVGUtilCarportSide;
import PresentatinoLayer.SVG.SVGUtilCarportTop;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rasmu
 */
public class CreateOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException, MakeOrderException {
        LogicFacade lf = new LogicFacade();
        SVGUtilCarportTop svgStringTop = new SVGUtilCarportTop();
        SVGUtilCarportSide svgStringSide = new SVGUtilCarportSide();

        int width = Integer.parseInt(request.getParameter("widthnumber"));
        int length = Integer.parseInt(request.getParameter("lengthnumber"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String zip = request.getParameter("zip");
        String phone = request.getParameter("phone");
        String evt = request.getParameter("evt");
        String measurementtype = request.getParameter("measurements");
        
        boolean highRoof;
        int roof = Integer.parseInt(request.getParameter("roof"));
        if( roof == 1) {
            highRoof = false;
        } else {
            highRoof = true;
        }
        
        boolean isShed;
        int shedWidth = 0;
        int shedLength = 0;
        String shedWidthString = (request.getParameter("shedWidth"));
        System.out.println("SHED WIDTH " + shedWidthString);
        String shedLengthString = (request.getParameter("shedLength"));
        if(shedWidthString.isEmpty() && shedLengthString.isEmpty()) {
            isShed = false;
        }else {
            isShed = true;
            shedWidth = Integer.parseInt(shedWidthString);
            shedLength = Integer.parseInt(shedLengthString);
        }
        
        Order o;

        if ("outermeasurements".equals(measurementtype) && (length < 325 || width < 310)) {
            throw new MakeOrderException("Længden eller bredden på din carports indre mål er under 240.");
        }
        if ("outermeasurements".equals(measurementtype)) {
            o = lf.makeOrder(width - 70, length - 85, name, email, zip, phone, evt);
        } else {
            o = lf.makeOrder(width, length, name, email, zip, phone, evt);
        }
        String svgTop = svgStringTop.printCarportTop(length, width, highRoof, isShed, shedLength, shedWidth);
        String svgSide = svgStringSide.printCarportSide(length, width, highRoof, isShed, shedLength);

        request.getSession().setAttribute("svgside", svgSide);
        request.getSession().setAttribute("svgtop", svgTop);
        request.getSession().setAttribute("order", o);

        return "singleOrder";

    }

}