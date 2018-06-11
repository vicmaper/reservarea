<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="edit-articulo" class="content scaffold-edit" role="main">
			<g:form method="PUT">
				<fieldset class="button">
					<input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}"/>
				</fieldset> 
			</g:form>
		</div>
	</body>
</html>