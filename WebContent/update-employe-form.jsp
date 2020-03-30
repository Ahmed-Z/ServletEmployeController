<!DOCTYPE html>
<html>
<head>
<title>Modifier Employé</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-employe-style.css">
</head>

<body>
<div id="wrapper">
	<div id="header">
		<h2>Gestionnaire Employés</h2>
	</div>
</div>
<div id="container">
	<h3>Modifier Employé</h3>
	<form action="employéControllerServlet" method="get">
		<input type="hidden" name="command" value="UPDATE" />
		<input type="hidden" name="employeId" value="${LE_EMPLOYE.id}" />
		<table>
			<tbody>
				<tr>
					<td><label>Nom</label></td>
					<td><input type="text" name="lastname" value="${LE_EMPLOYE.nom}"/></td>
				</tr>
				<tr>
					<td><label>Prenom</label></td>
					<td><input type="text" name="firstname" value="${LE_EMPLOYE.prenom}"/></td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><input type="text" name="email"value="${LE_EMPLOYE.email}" /></td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Confirmer" class="save" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div style="clear:both;">
		<p>
			<a href="employéControllerServlet">Retour</a>
		</p>
	</div>
</div>

</body>
</html>