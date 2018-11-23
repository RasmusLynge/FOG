<%-- 
    Document   : showorderdetails
    Created on : 15-11-2018, 16:07:03
    Author     : Simon
--%>

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
<%
Order o = (Order) request.getAttribute("order");
%>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <div class="container">
            <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-primary">
                <a class="navbar-brand" href="/index.html" style="padding:0px;">
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
                <%String id = o.getId();%>
                <h1>Ordre detaljer for id: <%= id %></h1>
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="editorderdetails">
                    <input type="hidden" name="orderID" value=<%=id%>>
                    <ol>
                    <li>
                        Bredde: <input type="number" name="width" min="240" max="720" placeholder="<%out.println(o.getWidth());%>" required> cm.
                    </li>
                    <li>
                        Længde: <input type="number" name="length" min="240" max="720" placeholder="<%out.println(o.getLength());%>" required> cm.
                    </li>                 
                    <li>
                        Tag:
                        <select name="roof">
                            <option value="1" <%if (o.isFlat_roof()) out.println("selected"); %>>Fladt tag</option>
                            <option value="0" <%if (!o.isFlat_roof()) out.println("selected"); %>>Tag med rejsning</option>
                        </select>
                    </li>
                    <li>
                        Bestillingsdato: <%out.println(o.getOrderdate());%>
                    </li>
                    <li>
                        <input type="radio" name="State" value="Forespørgsel" <%if ("Forespørgsel".equals(o.getState())) out.println("checked"); %>> Forespørgsel<br>
                        <input type="radio" name="State" value="Afventer sælger" <%if ("Afventer sælger".equals(o.getState())) out.println("checked"); %>> Afventer sælger<br>
                        <input type="radio" name="State" value="Betalt" <%if ("Betalt".equals(o.getState())) out.println("checked"); %>> Betalt<br>
                        <input type="radio" name="State" value="Fragtet" <%if ("Fragtet".equals(o.getState())) out.println("checked"); %>> Fragtet<br>
                        <input type="radio" name="State" value="Afsluttet uden salg" <%if ("Afsluttet uden salg".equals(o.getState())) out.println("checked"); %>> Afsluttet uden salg<br>
                    </li>
                </ol>
                    <input type="submit" value="Gem ændringer">
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