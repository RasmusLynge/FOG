<%-- 
    Document   : orderpage
    Created on : 10-11-2018, 17:22:36
    Author     : Rasmu
--%>

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
                                <a class="dropdown-item" href="#">Med skur</a>
                                <a class="dropdown-item" href="#">Uden skur</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="https://www.johannesfog.dk/byggecenter/landingpages/carporte/">Standart Carporte</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/om-fog/">Om FOG</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/FOG/employeelogin.jsp">login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/find-butik/kontakt/">Kontakt</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="jumbotron">
                <h2 class="display-4">QUICK-BYG TILBUD - carport med fladt tag</h2>
                <p class="lead">Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker. </p>
                <br>
                <p class="lead"> Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                    Ved bestilling medfølger standardbyggevejledning.</p>

                <% String error = (String) request.getAttribute("error");
                    if (error != null) {
                        out.println("<h2>Fejl!</h2>");
                        out.println(error);
                    }
                %>
                <h6>Carport Bredde</h6>
                <form name="order" action="FrontController" method="POST">
                    <input type="hidden" name="command" value="order">


                    <div class="form-group">
                        <input type="number" name="widthnumber" class="form-control" placeholder="Vælg bredde min. 240cm max 720cm" value="" required min="240" max="720"/>
                    </div>


                    <h6>Carport Længde</h6>
                    <div class="form-group">
                        <input type="number" name="lengthnumber" class="form-control" placeholder="Vælg længde min. 240cm max 720cm" value="" required min="240" max="720"/>
                    </div>

                    <h6>Indre eller ydre mål</h6>
                    <p class="lead">Som udgangspunkt bestiller du de indre mål på din carport. Carportens ydre mål er større end hvad du indtaster.
                        Hvis du i stedet ønsker at bestille på de ydre mål, skal du markere det i boksen herunder:</p>
                    <input type="checkbox" name="measurements" value="outermeasurements">Jeg vil gerne bestille med de indtastede værdier som ydre mål<br>


                    <br>
                    <h6>Kontakt information</h6>
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" placeholder="Navn *" value="" required/>
                    </div>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="Email *" value="" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="zip" class="form-control" placeholder="Postnummer *" value="" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="phone" maxlength="10" minlength="8" class="form-control" placeholder="Telefon nummer *" value="" required/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="evt" class="form-control" placeholder="Evt. bemærkninger" value=""  
                    </div>
                    <br>
                    <p class="lead">
                        <input type="submit" class="btn btn-primary" value="Send forespørgsel">
                    </p>
                </form>
            </div>


        </div>
        <nav class="navbar bottom navbar-dark bg-dark">
            <a class="navbar-brand" >Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</a>
            <a class="navbar-brand" style="float: right" >Alle priser er inkl. moms</a>
        </li>
    </nav>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</body>
</html>