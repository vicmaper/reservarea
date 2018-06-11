<!DOCTYPE html>
	<html>
		<head>
			<meta name="layout" content="main" />
		</head>
		<body>
			<g:if test="${miLista == null}">
				VACIA
			</g:if>
			<table id="example">
				<g:each in="${miLista}" var="p">
					<tr>
						<td>${p.id}</td>
						<td>${p.nombre}</td>
						<td>
							<g:form action="editar">
								<input type="hidden" name="id" value=${p.id}>
								<input type="submit" value="Actualizar">
							</g:form>
							<g:form method="DELETE" action="eliminar">
								<input type="hidden" name="id" value="${p.id}">
								<input type="submit" value="Eliminar">
							</g:form>
						</td>
					</tr>
				</g:each>
			</table>
			</br>
			<g:form method="POST" class="form-horizontal form-label-left" action="guardar">
				<input type="hidden" name="el_id" value="${el_id}">
				Nombre: <input type="text" name="nombre"><br/>
				<fieldset class="button">
					<g:submitButton class="save" name="crear" value="Crear" />					
				</fieldset>
			</g:form>
		</body>
	</html>