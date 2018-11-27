<%-- 
    Document   : Carport
    Created on : Nov 20, 2018, 12:31:12 PM
    Author     : Magnus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <%= request.getAttribute("carporttop") %>
            <%= request.getAttribute("carportside") %>
             
    </body>
</html>
