<!DOCTYPE html>
<html>
<head>
<title>Add Employ�</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-employe-style.css">
</head>

<body>
<div id="wrapper">
	<div id="header">
		<h2>Gestionnaire Employ�s</h2>
	</div>
</div>
<div id="container">
	<h3>Ajouter Employ�</h3>
	<form action="employ�ControllerServlet" method="get">
		<input type="hidden" name="command" value="ADD" />
		<table>
			<tbody>
				<tr>
					<td><label>Nom</label></td>
					<td><input type="text" name="lastname" /></td>
				</tr>
				<tr>
					<td><label>Prenom</label></td>
					<td><input type="text" name="firstname" /></td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><input type="text" name="email" /></td>
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
			<a href="employ�ControllerServlet">Retour</a>
		</p>
	</div>
</div>

</body>
</html>