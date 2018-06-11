<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>ReservAula</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
 <g:img dir="images" file="salones.jpg" width="1380" height="300"/>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Bienvenido a ReservAula</h1>
            <p>
                <sec:ifAnyGranted roles="ROLE_USUARIO">
                    <p>
                        Bienvenido Usuario
                    </p>
                </sec:ifAnyGranted>
            </p>
            <p>  
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <p>
                        Bienvenido administrador de la Salle
                    </p>
                </sec:ifAnyGranted>
            </p>
            <!-- <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div> -->
            <br>
            </br>
            <p>
            <sec:ifNotLoggedIn>
            <div align="right">
                <form action="/solicitudarea/login/authenticate" method="POST" id="loginForm" class="cssform" autocomplete="off">
                <p>
                    <label for="username">Nombre de usuario:</label>
                    <input type="text" class="text_" name="username" id="username">
                </p>
                <p>
                    <label for="password">Contraseña:</label>
                    <input type="password" class="text_" name="password" id="password">
                </p>
                <p>
                <g:link controller="bienvenida" action="index">
                    <input type="submit" name="ingresar"/>
                </g:link>
                </p>
                </form>
            </div>
            </sec:ifNotLoggedIn>
            </p>
        </section>
    </div>

</body>
</html>
