<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="model.Login"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creation Logins</title>
</head>
<body>
	<%
		model.Login login = (model.Login) request.getAttribute("login");
		/* Tests */
		Integer idLog = login.getIdLog();
		String idLogForm;
		if (idLog == 0) {
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
		if (adminLog == 0) {
			adminLogForm = "";
		} else {
			adminLogForm = adminLog.toString();
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
			Login
		</legend>
		<form action="login" method="post">
			<input type="hidden" name="action" value="update" />
			<table>
				<tr>
					<td>Id</td>
					<input type="hidden" name="id" value="<%=idLogForm%>" />
					<td><%=idLogForm%></td>
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
					<td><input type="text" name="admin"
						value="<%=adminLogForm%>" /></td>
					
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