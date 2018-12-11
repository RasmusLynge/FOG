//package PresentatinoLayer.Commands;
//
//import PresentatinoLayer.Commands.Command;
//import FunctionLayer.LogicFacade;
//import FunctionLayer.Exception.DMException;
//import FunctionLayer.Entity.User;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class Register extends Command {
//
//    @Override
//    String execute( HttpServletRequest request, HttpServletResponse response ) throws DMException {
//        String email = request.getParameter( "email" );
//        String password1 = request.getParameter( "password1" );
//        String password2 = request.getParameter( "password2" );
//        if ( password1.equals( password2 ) ) {
//            User user = LogicFacade.createUser( email, password1 );
//            HttpSession session = request.getSession();
//            session.setAttribute( "user", user );
//            session.setAttribute( "role", user.getRole() );
//            return user.getRole() + "page";
//        } else {
//            throw new DMException( "the two passwords did not match" );
//        }
//    }
//
//}
