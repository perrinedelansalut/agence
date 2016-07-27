package agence.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdresseDao;
import dao.AdresseDaoSql;
import model.Adresse;

@WebServlet("/adresse")
public class AdresseController extends HttpServlet {

	private AdresseDao adresseDao = new AdresseDaoSql();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on teste si le param�tre action est pr�sent dans l'URL
		String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
		// si l'action demand�e par le user est la liste des �l�ves
		if (action.equals("list")) {
			// je r�cup�re la liste des adresses
			List<Adresse> adresses = adresseDao.findAll();
			// je la charge dans le request
			request.setAttribute("adresses", adresses);
			// je pr�pare le dispatche de la requ�te vers ma page adresses.jsp
			RequestDispatcher rd = request.getRequestDispatcher("adresses.jsp");
			// le ctrl fait suivre la requ�te et la r�ponse � la jsp
			rd.forward(request, response);
		} else if (action.equals("add")) {
			request.setAttribute("adresse", new Adresse());

			RequestDispatcher rd = request.getRequestDispatcher("adresseEdit.jsp");

			rd.forward(request, response);
		} else if (action.equals("edit")) {
			Integer id = Integer.parseInt(request.getParameter("id"));

			Adresse adresse = adresseDao.findById(id);

			request.setAttribute("adresse", adresse);

			RequestDispatcher rd = request.getRequestDispatcher("adresseEdit.jsp");

			rd.forward(request, response);

		} else if (action.equals("update")) {
			String idAddForm = request.getParameter("idAdd");

			Integer id = null;
			String adresse = "";
			String codePostal = "";
			String ville = "";
			String pays = "";

			try {
				// si l'id r�cup�r� est non null, on parse
				if (idAddForm != null) {
					id = Integer.parseInt(idAddForm);
				}
				adresse = request.getParameter("adresse");
				codePostal = request.getParameter("codePostal");
				ville = request.getParameter("ville");
				pays = request.getParameter("pays");
			} catch (Exception e) {
				e.printStackTrace();
			}

			Adresse adresseObj = null;

			if (id == null) {
				adresseObj = new Adresse();
			} else {
				adresseObj = adresseDao.findById(id);
			}

			adresseObj.setAdresse(adresse);
			adresseObj.setCodePostal(codePostal);
			adresseObj.setVille(ville);
			adresseObj.setPays(pays);

			if (id == null) {
				adresseDao.create(adresseObj);
			} else {
				adresseDao.update(adresseObj);
			}

			request.setAttribute("adresses", adresseDao.findAll());

			RequestDispatcher rd = request.getRequestDispatcher("adresses.jsp");

			rd.forward(request, response);

		} else if (action.equals("delete")) {
			Integer id = Integer.parseInt(request.getParameter("id"));

			Adresse adresseObj = adresseDao.findById(id);

			adresseDao.delete(adresseObj);

			request.setAttribute("adresses", adresseDao.findAll());

			RequestDispatcher rd = request.getRequestDispatcher("adresses.jsp");

			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
