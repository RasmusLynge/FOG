<%-- 
    Document   : showorders
    Created on : 15-11-2018, 17:10:18
    Author     : Simon
--%>

<%@page import="FunctionLayer.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="FunctionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>Bootstrap 4 Starter Template</title>

        <title>Bootstrap 4 Layout</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:400,800">
        <link rel='stylesheet' href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" media="all" href="CSS/main.css">
    </head>
    <body>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <div class="container">
            <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-primary">
                <a class="navbar-brand" href="#" style="padding:0px;">
                    <img src="logo.png" style="height:100%;">
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Hjem</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                                Design Carport
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Fladt Tag</a>
                                <a class="dropdown-item" href="#">Tag Med Rejsning</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#">Info om FOG's carport design</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/om-fog/">Om FOG</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/find-butik/kontakt/">Kontakt</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="jumbotron">
                <%
                    User user = (User) session.getAttribute("user");
                    if ("customers".equals(user.getRole())) {
                %>
                <table>
                    <thead>
                    <th>Her kan du se dine tidligere ordrer</th>
                    </thead>
                    <tbody>
                    <td>
                        <%
                            if (user != null) {
                                ArrayList<Order> orderlist = new ArrayList<>();
                                orderlist = (ArrayList<Order>) request.getSession().getAttribute("ordersByUserID");
                                for (int i = 0; i < orderlist.size(); i++) {
                                    String orderID = orderlist.get(i).getId();
                        %>
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="command" value="vieworder">
                            <input type="hidden" name="orderID" value="<%out.print(orderID);%>">
                            View order details concerning order ID: <%out.println(orderID);%><input type="submit" value="View order details">
                        </form>
                        <%
                                }
                            }
                        %>       
                    </td>
                    </tbody>
                </table>
                <%
                } else if ("employee".equals(user.getRole())) {
                %>
                <table>
                    <thead>
                    <th>Her kan du se alle ordre i systemmet</th>
                    </thead>
                    <tr>
                        <td>
                            <%
                                if (user != null) {
                                    ArrayList<Order> orderlist = new ArrayList<>();
                                    orderlist = (ArrayList<Order>) session.getAttribute("getAllOrders");
                                    for (int i = 0; i < orderlist.size(); i++) {
                                        String orderID = orderlist.get(i).getId();
                            %>
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="showOrderDetails">
                                <input type="hidden" name="orderID" value="<%out.print(orderID);%>">
                                Order ID: <%out.println(orderID);%><input type="submit" value="Se ordre detaljer">  
                            </form>
                            <%
                                    }
                                }
                            %>       
                        </td>
                    </tr>
                </table>
                <%
                    }
                %>

            </div>
            <div class="jumbotron">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Order ID</th>
                            <th scope="col">Date</th>
                            <th scope="col">Status</th>
                            <th scope="col">Se detaljer for ordren</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <%
                            if (user != null) {
                                ArrayList<Order> orderlist = new ArrayList<>();
                                orderlist = (ArrayList<Order>) session.getAttribute("getAllOrders");
                                for (int i = 0; i < orderlist.size(); i++) {
                                    String orderID = orderlist.get(i).getId();
                        %>
                    <tbody>
                        <tr>
                            <th scope="row"><%out.print(orderID);%></th>
                            <td><%orderlist.get(i).getOrderdate();%> her skal der stå dato</td>
                            <td><%orderlist.get(i).getState();%></td>
                            <td>
                    <form action="FrontController" method="POST">
                        <input type="hidden" name="command" value="showOrderDetails">
                        <input type="hidden" name="orderID" value="<%out.print(orderID);%>">
                        <input type="submit" value="Se ordre detaljer">  
                    </form> 
                    </td>
                    </tr>
                    <%
                            }
                        }
                    %>       
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>the Bird</td>
                        <td>@twitter</td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <nav class="navbar bottom navbar-dark bg-dark">
                <a class="navbar-brand" >Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</a>
                <a class="navbar-brand" style="float: right" >Alle priser er inkl. moms</a>
                </li>
            </nav>

        </div>

        <script src="/js/jquery.min.js"></script>
        <script src="/js/popper.min.js"></script>
        <script src="/js/bootstrap.min.js"></script>
    </body>
</html>
