package PresentatinoLayer.Commands;

import PresentatinoLayer.Commands.CreateOrder;
import FunctionLayer.Exception.GeneralException;
import FunctionLayer.Exception.MakeOrderException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



abstract class Command {

    private static HashMap<String, Command> commands;

    private static void initCommands() {
        commands = new HashMap<>();
        commands.put("login", new Login());
        commands.put("register", new Register());
        commands.put("orderpage", new OrderPage());
        commands.put("employeelogin", new EmployeeLoginPage());
        commands.put("order", new CreateOrder());
        commands.put("listorders", new GetOrders());
        commands.put("showOrderDetails", new GetOrderDetails());
        commands.put("editorderdetails", new EditOrder());
        commands.put("logout", new LogOut());
    }

    static Command from(HttpServletRequest request) {
        String commandName = request.getParameter("command");
        if (commands == null) {
            initCommands();
        }
        return commands.getOrDefault(commandName, new UnknownCommand());
    }

    abstract String execute(HttpServletRequest request, HttpServletResponse response)
            throws GeneralException, MakeOrderException;
}