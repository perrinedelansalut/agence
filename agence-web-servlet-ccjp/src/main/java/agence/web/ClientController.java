package agence.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import dao.ClientDaoSql;
import model.Client;


@WebServlet("/client")
public class ClientController extends HttpServlet {

	private ClientDao clientDao = new ClientDaoSql();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	//On teste si le paramètre action est présent dans l'url
        String action = request.getParameter("action") != null ? request.getParameter("action") : "list";
        //si l'action demandée par le user est la liste des clients
        if (action.equals("list"))
        {	//je récupère la liste des clients
            List<Client> clients = clientDao.findAll();
            //je la charge dans l'objet request
            request.setAttribute("clients", clients);
            //je prépare le dispatche de la requète vers ma page clients.jsp
            RequestDispatcher rd = request.getRequestDispatcher("clients.jsp");
            //le controller fait suivre la requête et la réponse à la jsp
            rd.forward(request, response);
        }
        else if (action.equals("add"))
        {
            request.setAttribute("client", new Client());

            RequestDispatcher rd = request.getRequestDispatcher("clientEdit.jsp");

            rd.forward(request, response);
        }
        else if (action.equals("edit"))
        {
            Integer id = Integer.parseInt(request.getParameter("id"));

            Client client = clientDao.findById(id);

            request.setAttribute("client", client);

            RequestDispatcher rd = request.getRequestDispatcher("clientEdit.jsp");

            rd.forward(request, response);

        }
        else if (action.equals("update"))
        {
        	String idForm = request.getParameter("id");
        	
            Integer id = null;
            String nom = "";
            String prenom = "";
            String numTel ="";
            String numFax = "";
            String eMail = "";
            Integer siret =null;

            try
            {
                // si l'id récupéré est non null, on parse
                if (idForm != null)
                {
                    id = Integer.parseInt(idForm);
                }
                nom = request.getParameter("nom");
                prenom = request.getParameter("prenom");
                numTel = request.getParameter("numTel");
                numFax = request.getParameter("numFax");
                eMail = request.getParameter("eMail");
                siret = Integer.parseInt(request.getParameter("siret"));
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            Client client = null;

            if (id == null)
            {
                client = new Client();
            }
            else
            {
                client = clientDao.findById(id);
            }

            client.setNom(nom);
            client.setPrenom(prenom);
            client.setNumeroTel(numTel);
            client.setNumeroFax(numFax);
            client.setEmail(eMail);

            if (id == null)
            {
                clientDao.create(client);
            }
            else
            {
                clientDao.update(client);
            }

            request.setAttribute("clients", clientDao.findAll());

            RequestDispatcher rd = request.getRequestDispatcher("clients.jsp");

            rd.forward(request, response);

        }
        else if (action.equals("delete"))
        {
            Integer id = Integer.parseInt(request.getParameter("id"));

            Client client = clientDao.findById(id);

            clientDao.delete(client);

            request.setAttribute("clients", clientDao.findAll());

            RequestDispatcher rd = request.getRequestDispatcher("clients.jsp");

            rd.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

}
