<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="model.Login"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition Logins</title>
</head>
<body>
	<%
		model.Login login = (model.Login) request.getAttribute("login");
		/* Tests */
		Integer idLog = login.getIdLog();
		String idLogForm;
		if (idLog == null) {
			idLogForm = "";
		} else {
			idLogForm = idLog.toString();
		}

		String loginLog = login.getLogin();
		String loginLogForm;
		if (loginLog == null) {
			loginLogForm = "";
		} else {
			loginLogForm = loginLog;
		}

		String mdpLog = login.getMotDePasse();
		String mdpLogForm;
		if (mdpLog == null) {
			mdpLogForm = "";
		} else {
			mdpLogForm = mdpLog;
		}
		Integer adminLog = login.getAdmin();
		String adminLogForm;
		if (adminLog == null) {
			adminLogForm = "0";
		} else {
			adminLogForm = "1";
		}
	%>
	<fieldset>
		<legend>
			<%
				if (idLogForm.equals("")) {
					out.print("Création");
				} else {
					out.print("Edition");
				}
			%>
			de l'élève
		</legend>
		<form action="login" method="post">
			<input type="hidden" name="action" value="update" />
			<table>
				<tr>
					<td>Id</td>
					<td><input type="text" name="id" value="<%=idLogForm%>"
						<%if (idLogForm.equals("")) {
				out.print("disabled");
			}%> /></td>
				</tr>
				<tr>
					<td>Login</td>
					<td><input type="text" name="login" value="<%=loginLogForm%>" /></td>
				</tr>
				<tr>
					<td>Mot de Passe</td>
					<td><input type="text" name="mot de passe"
						value="<%=mdpLogForm%>" /></td>
				</tr>
				<tr>
					<td>Admin</td>
					<td><input type="radio" name="admin" value="oui">oui
					<%if (adminLogForm.equals("1")) {out.print("checked");}%>
					<input type="radio" name="admin" value="non">non
					<%if (adminLogForm.equals("0")) {out.print("checked");}%>
						</td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</form>
	</fieldset>
</body>
</html>