<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Passager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des passagers</title>
</head>
<body>

<table border="1">
		<tr>
			<td>Id</td>
			<td>Nom</td>
			<td>Prénom</td>
			<td>Adresse</td>
			<td>Edition</td>
		<td>Suppression</td> 
			
		</tr>

		<%
			List<Passager> passagers = (List<Passager>) request.getAttribute("passagers");
		
			for(Passager passager : passagers) {
		%>
		
		
			<tr>
				<td><%=passager.getIdPas() %></td>
				<td><%=passager.getNom() %></td>
				<td><%=passager.getPrenom() %></td>
				<td><%=passager.getAdresse() %></td>
				<td><a href="passager?action=edit&id=<%=passager.getIdPas() %>">Editer</a></td>
			<td><a href="passager?action=delete&id=<%=passager.getIdPas() %>">Supprimer</a></td>  
			</tr>
		<% } %>
		
		<tr>
			<td colspan="6"><a href="passager?action=add">Ajouter un passager</a></td>
		</tr>
	</table>
	
	






</body>
</html>