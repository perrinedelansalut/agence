package agence.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import dao.LoginDaoSql;
import model.Login;


@WebServlet("/login")
public class LoginController extends HttpServlet 
{
	private LoginDao loginDao = new LoginDaoSql();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// on test si le param action des présent dans l'url
		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
		// si l'action demandé par le user est la liste des login
		
		if (action.equals("list")) {
			// je récupère le liste des élèves
			List<Login> logins = loginDao.findAll();
			// je la charge dans l'obj request
			request.setAttribute("logins", logins);
			// je prépare le dispatche ma requete vers la page eleve.jsp
			RequestDispatcher rd = request.getRequestDispatcher("logins.jsp");
			// le c1trl fait suivre la requete et la réponse à la jsp
			rd.forward(request, response);
			
		} else if (action.equals("add")) {
			request.setAttribute("login", new Login());

			RequestDispatcher rd = request.getRequestDispatcher("loginEdit.jsp");

			rd.forward(request, response);
		} else if (action.equals("edit")) {
			Integer id = Integer.parseInt(request.getParameter("id"));

			Login login = loginDao.findById(id);

			request.setAttribute("login", login);

			RequestDispatcher rd = request.getRequestDispatcher("loginEdit.jsp");

			rd.forward(request, response);

		} else if (action.equals("update")) 
		{
			String idForm = request.getParameter("id");

			Integer id = null;
			String login = "";
			String mdp = "";
			Integer admin = null;

			try 
			{
				// si l'id récupéré est non null, on parse
				if (idForm != null) 
				{
					id = Integer.parseInt(idForm);
				}
				login = request.getParameter("login");
				mdp = request.getParameter("mot de passe");
				admin = Integer.parseInt(request.getParameter("admin"));
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			Login log= null;

			if (id == null) 
			{
				log = new Login();
			} 
			else 
			{
				log = loginDao.findById(id);
			}

			log.setLogin(login);
			log.setMotDePasse(mdp);
			log.setAdmin(admin);

			if (id == null) 
			{
				loginDao.create(log);
			} 
			else
			{
				loginDao.update(log);
			}
			
			request.setAttribute("logins", loginDao.findAll());

			RequestDispatcher rd = request.getRequestDispatcher("logins.jsp");

			rd.forward(request, response);
			
		}
		else if (action.equals("delete")) 
		{
			Integer id = Integer.parseInt(request.getParameter("id"));

			Login login = loginDao.findById(id);

			loginDao.delete(login);

			request.setAttribute("logins", loginDao.findAll());

			RequestDispatcher rd = request.getRequestDispatcher("logins.jsp");

			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
