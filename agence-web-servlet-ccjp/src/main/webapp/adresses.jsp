<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Adresse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des adresses</title>
</head>
<body>

	<table border="1">
		<tr>
			<td>Id</td>
			<td>Adresse</td>
			<td>Code Postal</td>
			<td>Ville</td>
			<td>Pays</td>
			<td>Edition</td>
			<td>Suppression</td>
		</tr>

		<%
			List<Adresse> adresses = (List<Adresse>)request.getAttribute("adresses");
		
			for(Adresse adresse : adresses) {
		%>
		
		
			<tr>
				<td><%=adresse.getIdAdd() %></td>
				<td><%=adresse.getAdresse() %></td>
				<td><%=adresse.getCodePostal() %></td>
				<td><%=adresse.getVille() %></td>
				<td><%=adresse.getPays() %></td>
				<td><a href="adresse?action=edit&id=<%=adresse.getIdAdd() %>">Editer</a></td>
				<td><a href="adresse?action=delete&id=<%=adresse.getIdAdd() %>">Supprimer</a></td>
			</tr>
		<% } %>
		
		<tr>
			<td colspan="7"><a href="adresse?action=add">Ajouter une adresse</a></td>
		</tr>
	</table>

</body>
</html>