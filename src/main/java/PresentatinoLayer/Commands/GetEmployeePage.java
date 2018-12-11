package PresentatinoLayer.Commands;

import FunctionLayer.Exception.DMException;
import FunctionLayer.Exception.MakeOrderException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetEmployeePage extends Command {

    public GetEmployeePage() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DMException, MakeOrderException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user").equals(null)) {
            throw new DMException("Du har ikke rettigheder til at logge ind her");
        }
        if (!session.getAttribute("user").equals(null)) {
            return "employeepage";
        } else {
            throw new DMException("Du har ikke rettigheder til at logge ind her");
        }
    }
}
