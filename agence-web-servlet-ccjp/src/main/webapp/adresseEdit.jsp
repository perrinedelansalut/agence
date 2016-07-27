<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.Adresse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edition de l'adresse</title>
</head>
<body>
<%
	    Adresse adresse = (Adresse) request.getAttribute("adresse");
	    /* Tests */
	    Integer idAdd = adresse.getIdAdd();
	    String idAddForm;
	    if (idAdd == 0)
	    {
	    	idAddForm = "";
	    }
	    else
	    {
	    	idAddForm = idAdd.toString();
	    }

	    String adresseAdresse = adresse.getAdresse();
	    String adresseAdresseForm;
	    if (adresseAdresse == null)
	    {
	    	adresseAdresseForm = "";
	    }
	    else
	    {
	    	adresseAdresseForm = adresseAdresse;
	    }

	    String codePostalAdresse = adresse.getCodePostal();
	    String codePostalAdresseForm;
	    if (codePostalAdresse == null)
	    {
	    	codePostalAdresseForm = "";
	    }
	    else
	    {
	    	codePostalAdresseForm = codePostalAdresse;
	    }
	    String villeAdresse = adresse.getVille();
	    String villeAdresseForm;
	    if (villeAdresse == null)
	    {
	    	villeAdresseForm = "";
	    }
	    else
	    {
	    	villeAdresseForm = villeAdresse.toString();
	    }

	    String paysAdresse = adresse.getPays();
	    String paysAdresseForm;
	    if (paysAdresse == null)
	    {
	    	paysAdresseForm = "";
	    }
	    else
	    {
	    	paysAdresseForm = paysAdresse.toString();
	    }
	%>
	<fieldset>
		<legend>
			<%
			    if (idAddForm.equals(""))
			    {
			        out.print("Création");
			    }
			    else
			    {
			        out.print("Edition");
			    }
			%>
			de l'adresse
		</legend>
		<form action="adresse" method="post">
			<input type="hidden" name="action" value="update" />
			<table>
				<tr>
					<td>Id</td>
					<td><input type="text" name="id" value="<%=idAddForm%>" disabled="disabled"
						<%if (idAddForm.equals(""))
            {
                out.print("disabled");
            }%> /></td>
				</tr>
				<tr>
					<td>Adresse</td>
					<td><input type="text" name="adresse" value="<%=adresseAdresseForm%>" /></td>
				</tr>
				<tr>
					<td>Code Postal</td>
					<td><input type="text" name="codePostal"
						value="<%=codePostalAdresseForm%>" /></td>
				</tr>
				<tr>
					<td>Ville</td>
					<td><input type="text" name="ville"
						value="<%=villeAdresseForm%>" /></td>
				</tr>
				<tr>
					<td>Pays</td>
					<td><input type="text" name="pays" value="<%=paysAdresseForm%>" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</form>
	</fieldset>

</body>
</html>