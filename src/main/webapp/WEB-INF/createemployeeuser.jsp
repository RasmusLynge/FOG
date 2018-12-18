<%@page import="FunctionLayer.Entity.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 
        <title>Fog Carport</title>

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
                        </li>
                        <%} else if (user.getRole().equalsIgnoreCase("admin")) {
                        %> 
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" data-toggle="dropdown">
                                <%= user.getEmail()%>
                            </a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="/FOG/FrontController?command=logout">Log ud</a>
                            </div>
                        </li>
                        <%
                            } else {
                                out.print("<a class=\"nav-link\" href=\"/FOG/FrontController?command=logout\">Log ud</a>");

                            }%>                         
                    </ul>
                </div>
            </nav>

            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent2">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent2">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command" value="createemployeeuserpage">
                                <input class="btn btn-primary btn-md"type="submit" value="Opret bruger til medarbejder">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="FrontController" method="POST">
                                <input type="hidden" name="command"  value="getemployeeusers" > 
                                <input class="btn btn-primary btn-md"type="submit" value="Slet bruger for medarbejder">
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="jumbotron">
                <h2>Lav en ny bruger til en medarbejder: </h2>
                <br>
                <div class="form-group">
                    <form name="register" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="createemployeeuser">
                        Indtast Email:<br>
                        <input class="form-control" type="text" name="email" placeholder="Email *" value="" required/>
                        <br>
                        Indtast adgangskode:<br>
                        <input class="form-control" type="password" name="password1" placeholder="Adgangskode *" value="" required/>
                        <br>
                        Indtast adgangskode igen:<br>
                        <input class="form-control" type="password" name="password2" placeholder="Skriv adgangskode igen *" value="" required/>
                        <br>
                        <input class="btn btn-primary" type="submit" value="Opret bruger">
                    </form>
                </div>
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
