<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Adresse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition des passagers</title>
</head>
<body>

	<%
	    model.Passager passager = (model.Passager) request.getAttribute("passager");
	    /* Tests */
	    Integer idPass = passager.getIdPas();
	    String idPassForm;
	    if (idPass == 0)
	    {
	    	idPassForm = "";
	    }
	    else
	    {
	    	idPassForm = idPass.toString();
	    }

	    String nomPassager = passager.getNom();
	    String nomPassagerForm;
	    if (nomPassager == null)
	    {
	    	nomPassagerForm = "";
	    }
	    else
	    {
	    	nomPassagerForm = nomPassager;
	    }

	    String prenomPassager = passager.getPrenom();
	    String prenomPassagerForm;
	    if (prenomPassager == null)
	    {
	    	prenomPassagerForm = "";
	    }
	    else
	    {
	    	prenomPassagerForm = prenomPassager;
	    }
	    
  	    Adresse addPassager = passager.getAdresse();
  	    String addPassagerForm;
 	    if (addPassager == null)
	    {
 	    	addPassagerForm = "";
 	    }
	    else
 	    {
 	    	addPassagerForm = addPassager.toString();
 	    }
	    
	    
	    
	    

	%>
	<fieldset>
		<legend>
			<%
			    if (idPassForm.equals(""))
			    {
			        out.print("Création");
			    }
			    else
			    {
			        out.print("Edition");
			    }
			%>
			du Passager
		</legend>
		<form action="passager" method="post">
			<input type="hidden" name="action" value="update" />
			<table>
				<tr>
					<td>Id</td>
					<td><input type="text" name="id" value="<%=idPassForm%>" disabled="disabled"
						<%if (idPassForm.equals(""))
            {
                out.print("disabled");
            }%> /></td>
				</tr>
				<tr>
					<td>Nom</td>
					<td><input type="text" name="nom" value="<%=nomPassagerForm%>" /></td>
				</tr>
				<tr>
				
				</tr>
				<tr>
				
					<td>Prénom</td>
					<td><input type="text" name="prenom"
						value="<%=prenomPassagerForm%>" /></td>
				</tr>
				<tr>
 					<td>Adresse</td>
 					<td><input type="text" name="Adresse" 
						value="<%=addPassagerForm%>" /></td> 
				</tr>
				<tr>
					
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</form>
	</fieldset>


</body>
</html>