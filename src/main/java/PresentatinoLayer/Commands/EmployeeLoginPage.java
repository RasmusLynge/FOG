/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.Command;
import FunctionLayer.Exception.GeneralException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rasmu
 */
public class EmployeeLoginPage extends Command {
    
    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return "employeelogin";
    }
    
}