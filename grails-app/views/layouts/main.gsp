<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <assets:stylesheet src="bootstrap.css"/>
    <assets:javascript src="jquery-3.3.1.min.js"/>
    <assets:javascript src="bootstrap.js"/>
    <g:layoutHead/>
    <style>
        body {
            background: #E6E6E6;
        }
        footer {
            background: #CEF6F5;
        }
        nav {
            background: #CEF6F5;
        }
        #se{
            padding: left;    
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <sec:ifNotLoggedIn>
                <li class="nav-item">
                    <a class="nav-link" href="${createLink(uri: '/')}">Inicio</a>
                </li>
                </sec:ifNotLoggedIn>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/')}">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Solicitantes')}">Registro de solicitantes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Salones')}">Registro de aulas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Solicitud')}">Solicitudes de aula</a>
                    </li>
                    <li>
                        <a href="/solicitudarea/logoff">Salir</a>
                    </li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_USUARIO">
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/')}">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Solicitud')}">Solicitudes de aula</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${createLink(uri: '/Pago')}">Pago</a>
                    </li>
                    <li>
                        <a href="/solicitudarea/logoff">Salir</a>
                    </li>
                </sec:ifAnyGranted>
            </ul>
        </div>
    </nav>
    
    </div>
    <g:layoutBody/>
    <footer class="page-footer font-small blue pt-4 mt-4"><center>Desarrollado en la universidad la Salle Oaxaca</center></footer>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>