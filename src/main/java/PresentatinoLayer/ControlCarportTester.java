/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer;

import PresentatinoLayer.SVG.SVGUtilCarportSide;
import PresentatinoLayer.SVG.SVGUtilCarportTop;
import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Magnus
 */
@WebServlet(name = "Control", urlPatterns = {"/Control"})
public class ControlCarportTester extends HttpServlet {
    
     private static final int length = 500;
     private static final int width = 300;
     private static final int SHEDlength = 170;
     private static final int SHEDwidth = 300;
     int canvasX = length+300;
     int canvasY = width+300;
     

    SVGUtilCarportTop utilCarportTop = new SVGUtilCarportTop();
    SVGUtilCarportSide utilCarportSide = new SVGUtilCarportSide();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String carportTop = "<SVG width=\""+canvasX+"\" height=\""+canvasY+"\">" + utilCarportTop.caportFromAbove(length, width, true, true, SHEDlength, SHEDwidth) + "</SVG>";
            request.setAttribute("carporttop", carportTop);
            String carportSide = "<SVG width=\""+canvasX+"\" height=\""+canvasY+"\">" + utilCarportSide.caportFromSide(length, width, true, true, SHEDlength) + "</SVG>";
            request.setAttribute("carportside", carportSide);
            
        } catch (NumberFormatException ex) {
            System.out.println("erorororororoororo");
            request.setAttribute("error", "Input must be numeric values");
        }
        request.getRequestDispatcher("Carport.jsp").forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
