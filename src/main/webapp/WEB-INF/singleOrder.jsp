<%@page import="FunctionLayer.Entity.Order"%>
<%@page import="FunctionLayer.Entity.User"%>
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
                <a class="navbar-brand" href="/FOG/" style="padding:0px;">
                    <img src="logo.png" style="height:100%;">
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/FOG/">Hjem</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                                Design Carport
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/FOG/FrontController?command=orderpage">Med skur</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="https://www.johannesfog.dk/byggecenter/landingpages/carporte/">Standart Carporte</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/om-fog/">Om FOG</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/find-butik/kontakt/">Kontakt</a>
                        </li>
                        <li class="nav-item">
                            <% User user = (User) session.getAttribute("user");
                                if (user == null) {
                                    out.print("<a class=\"nav-link\" href=\"/FOG/FrontController?command=employeelogin\">Log ind</a>");
                                } else if (user.getRole().equalsIgnoreCase("employee")) {%>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                                <%= user.getEmail()%>
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/FOG/FrontController?command=getemployeepage">Gå til medarbejder siden</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/FOG/FrontController?command=logout">Log ud</a>
                            </div>

                            <%} else {
                                    out.print("<a class=\"nav-link\" href=\"/FOG/FrontController?command=logout\">Log ud</a>");

                                }%>                         
                        </li>
                    </ul>
                </div>
            </nav>
            <% Order o = (Order) request.getSession().getAttribute("order");%>
            <div class="jumbotron">
                <h2>Din ordre:</h2>

                <h6>Indre mål:</h6>
                <p> Længde: <%=o.getLength()%> <br>
                    Bredde: <%=o.getWidth()%> <br>
                </p>
                <h6>Ydre mål:</h6>
                <p> Længde: <%=o.getLength() + 85%> <br>
                    Bredde: <%=o.getWidth() + 70%> <br>
                    <br>
                </p>
                <b>Pris: <%=o.getPrice()%></b> <br>
                Vejledende Pris: <%=o.getPrice()%> dkk <br>

                </p>

            </div>
            <div class="jumbotron">
                <h2>Skitse af Carport:</h2>
                <p> Her er en skitse af carporten set fra toppen 
                <p> Bilen skal her køre ind fra højre side
                    <% String svgTop = (String) request.getSession().getAttribute("svgtop");%>
                    <%= svgTop%>
                </p>

                <p> Her er en skitse af carporten set fra siden 
                <p> Bilen skal her køre ind fra højre side
                    <% String svgSide = (String) request.getSession().getAttribute("svgside");%>
                    <%= svgSide%>
                </p>

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

