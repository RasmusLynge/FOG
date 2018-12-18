package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.LoggerException;
import FunctionLayer.Exception.LoginException;
import FunctionLayer.Exception.MakeOrderException;
import FunctionLayer.Exception.NotLoggedInException;
import logging.Logging;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

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
            throws ServletException, IOException, LoggerException {
        try {
            Command action = Command.from(request);
            String view = action.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        } catch (DMException Ex) {
            Logging.getLogger().log(Level.SEVERE, Ex.getMessage(), Ex);
            request.setAttribute("error", Ex.getMessage());
            Ex.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/errorpage.jsp").forward(request, response);
        } catch (MakeOrderException MakeOrderEx) {
            Logging.getLogger().log(Level.WARNING, MakeOrderEx.getMessage(), MakeOrderEx);
            request.setAttribute("error", MakeOrderEx.getMessage());
            MakeOrderEx.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/orderpage.jsp").forward(request, response);
        } catch (LoginException LoginEx) {
            Logging.getLogger().log(Level.WARNING, LoginEx.getMessage(), LoginEx);
            request.setAttribute("error", LoginEx.getMessage());
            LoginEx.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/employeelogin.jsp").forward(request, response);
        } catch (NotLoggedInException Ex) {
            Logging.getLogger().log(Level.SEVERE, Ex.getMessage(), Ex);
            request.setAttribute("error", Ex.getMessage());
            Ex.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/errorpage.jsp").forward(request, response);
        }
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
        try {
            processRequest(request, response);
        } catch (LoggerException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (LoggerException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
