<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'solicitantes.label', default: 'Solicitantes')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
     <g:img dir="images" file="solicitantes.png" width="1380" height="200"/>
        <a href="#list-solicitantes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-solicitantes" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <table>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido Paterno</th>
                        <th>Apellido Materno</th>
                        <th>Rol</th>
                        <th>Matricula</th>
                    </tr>
                </thead>
            <tbody>
                <g:each in ="${solicitantesList}">
                <tr>
                    <td>
                        <g:link action="show" params="${[id: it.id]}">
                        ${it.nombre}
                        </g:link>
                    </td>
                    <td>${it.apPaterno}</td>
                    <td>${it.apMaterno}</td>
                    <td>${it.rol}</td>
                    <td>${it.matricula}</td>
                </tr>
            </g:each>
            </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${solicitantesCount ?: 0}" />
            </div>
        </div>
    </body>
</html>