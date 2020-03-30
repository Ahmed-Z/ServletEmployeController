<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Employ� Tracker</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Gestionnaire Employ�s</h2>
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
				<c:forEach var="tempEmploy�" items="${EMPLOYE_LIST}">
				<c:url var="templink" value="employ�ControllerServlet">
				<c:param name="command" value="LOAD" />
				<c:param name="employeId" value="${tempEmploy�.id}" />
				</c:url>
				
				<c:url var="deletelink" value="employ�ControllerServlet">
					<c:param name="command" value="DELETE" />
					<c:param name="employeId" value="${tempEmploy�.id}" />
				</c:url>
				
				<tr>
					<td>${tempEmploy�.nom}</td>	
					<td>${tempEmploy�.prenom}</td>	
					<td>${tempEmploy�.email}</td>
					<td>
					<a href="${templink}">Modifier</a>
					 | 
					<a href="${deletelink}"
					onclick="if(!(confirm('Supprimer cet employ�?'))) return false">
					Supprimer</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
</body>
</html>