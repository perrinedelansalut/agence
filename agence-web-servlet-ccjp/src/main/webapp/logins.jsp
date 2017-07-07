<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Login"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Logins</title>
</head>
<body>
	<!-- Création d'un tableau de bordure 1 -->
	<table border="1">
		<!-- première ligne nom des en-têtes -->
		<tr>
			<td>Id</td>
			<td>Login</td>
			<td>Mot de Passe</td>
			<td>Admin</td>
			<td>Edition</td>
			<td>Suppression</td>
			
		</tr>

		<%
			List<Login> logins = (List<Login>) request.getAttribute("logins");

			for (Login login : logins) {
		%>


		<tr>
			<td><%=login.getIdLog()%></td>
			<td><%=login.getLogin()%></td>
			<td><%=login.getMotDePasse()%></td>
			<td><%=login.getAdmin()%></td>
			<td><a href="login?action=edit&id=<%=login.getIdLog()%>">Editer</a></td>
			<td><a href="login?action=delete&id=<%=login.getIdLog()%>">Supprimer</a></td>
		</tr>
		<%
			}
		%>

		<tr>
			<!-- la colonne s'étale sur 8 colonne -->
			<td colspan="6"><a href="login?action=add">Ajouter un Login</a></td>
		</tr>
	</table>

</body>
</html>