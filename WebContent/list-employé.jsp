<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Employé Tracker</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Gestionnaire Employés</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<input type="button" value="Ajouter" class="add-employe-button" onClick="window.location.href='add-employe-form.jsp'"/>
			<table>
				<tr>
					<th>Nom</th>
					<th>Prenom</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				<c:forEach var="tempEmployé" items="${EMPLOYE_LIST}">
				<c:url var="templink" value="employéControllerServlet">
				<c:param name="command" value="LOAD" />
				<c:param name="employeId" value="${tempEmployé.id}" />
				</c:url>
				
				<c:url var="deletelink" value="employéControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="employeId" value="${tempEmployé.id}" />
				</c:url>
				
				<tr>
					<td>${tempEmployé.nom}</td>	
					<td>${tempEmployé.prenom}</td>	
					<td>${tempEmployé.email}</td>
					<td>
					<a href="${templink}">Modifier</a>
					 | 
					<a href="${deletelink}"
					onclick="if(!(confirm('Supprimer cet employé?'))) return false">
					Supprimer</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>