<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modification des informations du client</title>
</head>
<body>
<%
	    model.Client client = (model.Client) request.getAttribute("client");
	    /* Tests */
	    Integer idClient = client.getIdCli();
	    String idClientForm;
	    if (idClient == 0)
	    {
	        idClientForm = "";
	    }
	    else
	    {
	        idClientForm = idClient.toString();
	    }

	    String nomClient = client.getNom();
	    String nomClientForm;
	    if (nomClient == null)
	    {
	        nomClientForm = "";
	    }
	    else
	    {
	        nomClientForm = nomClient;
	    }

	    String prenomClient = client.getPrenom();
	    String prenomClientForm;
	    if (nomClient == null)
	    {
	        prenomClientForm = "";
	    }
	    else
	    {
	        prenomClientForm = prenomClient;
	    }
	    String numTel = client.getNumeroTel();
	    String numTelForm;
	    if (numTel == null)
	    {
	        numTelForm = "";
	    }
	    else
	    {
	        numTelForm = numTel.toString();
	    }

	    String numFax = client.getNumeroFax();
	    String numFaxForm;
	    if (numFax == null)
	    {
	        numFaxForm = "";
	    }
	    else
	    {
	        numFaxForm = numFax.toString();
	    }
	    
	    String eMail = client.getEmail();
	    String eMailForm;
	    if (eMail == null)
	    {
	        eMailForm = "";
	    }
	    else
	    {
	        eMailForm = eMail.toString();
	    }
	    
	    Integer siret = client.getSiret();
	    String siretForm;
	    if (siret == 0)
	    {
	        siretForm = "";
	    }
	    else
	    {
	        siretForm = siret.toString();
	    }
	    
// 	    Integer idAdd = client.getAdresse().getIdAdd();
// 	    String idAddForm;
// 	    if (idAdd == null)
// 	    {
// 	        idAddForm = "";
// 	    }
// 	    else
// 	    {
// 	        idAddForm = idAdd.toString();
// 	    }
	    
	%>
	<fieldset>
		<legend>
			<%
			    if (idClientForm.equals(""))
			    {
			        out.print("Création");
			    }
			    else
			    {
			        out.print("Edition");
			    }
			%>
			du client
		</legend>
		<form action="client" method="post">
			<input type="hidden" name="action" value="update" />
			<table>
				<tr>
					<td>Id</td>
					<td><input type="text" name="id" value="<%=idClientForm%>"
						<%if (idClientForm.equals(""))
            {
                out.print("disabled");
            }%> /></td>
				</tr>
				<tr>
					<td>Nom</td>
					<td><input type="text" name="nom" value="<%=nomClientForm%>" /></td>
				</tr>
				<tr>
					<td>Prénom</td>
					<td><input type="text" name="prenom"
						value="<%=prenomClientForm%>" /></td>
				</tr>
				<tr>
					<td>Numéro de téléphone</td>
					<td><input type="text" name="numTel"
						value="<%=numTelForm%>" /></td>
				</tr>
				<tr>
					<td>Numéro de fax</td>
					<td><input type="text" name="numFax" value="<%=numFaxForm%>" /></td>
				</tr>
				<tr>
					<td>eMail</td>
					<td><input type="text" name="eMail" value="<%=eMailForm%>" /></td>
				</tr>
				<tr>
					<td>Siret</td>
					<td><input type="text" name="siret" value="<%=siretForm%>" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</form>
	</fieldset>

</body>
</html>