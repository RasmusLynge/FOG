<%@page import="FunctionLayer.Entity.User"%>
<!doctype html>
<html class="no-js" lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>FOG Carporte</title>

        <title>Bootstrap 4 Layout</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway:400,800">
        <link rel='stylesheet' href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" media="all" href="CSS/main.css">
    </head>
    <body>
        <!-- Denne del blive brugt til dropdown meny -->
        <!-- jQuery --> 
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <!-- Popper.js --> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
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
            <div class="jumbotron">
                <h1 class="display-4">KØB DIN NYE CARPORT HOS FOG</h1>
                <p class="lead">Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram - i de mål du ønsker.
                    Tilbud og skitsetegning fremsendes med post hurtigst muligt. </p>

                <!-- Dette er den tidligere knap her:
                <p class="lead"> 
                     <a class="btn btn-primary btn-lg" href="#" role="button">Bestil et tilbud her</a>
                 </p>
                -->

                <form name="orderpage" action="FrontController" method="POST">
                    <input class="btn btn-primary btn-lg" type="hidden" name="command" value="orderpage">
                    <br>
                    <input class="btn btn-primary btn-lg" type="submit" value="Bestil et tilbud her">
                </form>

            </div>
            <div class="row">
                <div class="col-sm-12 col-md-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <h5 class="card-title"> STANDARD MODELLER</h5>
                            <p class="card-text">Leveres som Byg-selv sæt - usamlet og ubehandlet!
                                Altid kvalitetsmaterialer.
                                Udførlig byggevejledning til carport og spær medfølger.

                                Levering i hele Danmark inden for ca. 10 hverdage.</p>
                            <a href="https://www.johannesfog.dk/byggecenter/sog/?searchterm=carporte" class="card-link">Se Standart modeller</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <h5 class="card-title">FOG BOLIG & DESIGNHUS</h5>
                            <p class="card-text">Shop lækkert design og udstyr til boligen.
                                Vælg mellem de bedste brands inden for designerlamper, køkkenudstyr, bad og VVS, maling, værktøj, boligindretning og eksklusive havemøbler.</p>
                            <a href="https://www.johannesfog.dk/designhus/" class="card-link">Se bolig og designhus her</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-4">
                    <div class="card mb-4">
                        <div class="card-body text-center">
                            <h5 class="card-title">FOG TRÆLAST & BYGGECENTER</h5>
                            <p class="card-text">Køb træ, byggematerialer og alt det du behøver til hus og have inden for f.eks. maling, bad og VVS, beslag, elartikler og lamper samt haveredskaber, grill og havemøbler. Hos os får du kvalitet og den rådgivning, du har brug for.</p>
                            <a href="https://www.johannesfog.dk/byggecenter/" class="card-link">Se tilbudsavisen her</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="jumbotron">

                <h1 class="display-4">Medarbejder hos fog?</h1>
                <p class="lead">Her kan du logge ind som medarbejder </p>

                <form name="orderpage" action="FrontController" method="POST">
                    <input class="btn btn-primary btn-lg" type="hidden" name="command" value="employeelogin">
                    <br>
                    <input class="btn btn-primary btn-lg" type="submit" value="Log ind">
                </form>
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