package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientDaoSql implements ClientDao {

	private Connection connexion;

	public ClientDaoSql() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 2. Créer la connexion à la base (on instancie l'objet connexion)
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/vol", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fermetureConnexion() {
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Client> findAll() {
		// Liste des clients que l'on va retourner
		List<Client> ListClients = new ArrayList<Client>();
		AdresseDaoSql adresseDAO = new AdresseDaoSql();
		LoginDaoSql loginDAO = new LoginDaoSql();

		try {

			/*
			 * Connexion à la BDD
			 */
			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM client");

			// 4. Execution de la requête
			ResultSet tuple = ps.executeQuery();
			// 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
			// récupérer les valeutuple des colonnes du tuple qui correspondent
			// aux
			// valeur des attributs de l'objet
			while (tuple.next()) {
				// Creation d'un objet Client
				Client objClient = new Client(tuple.getInt("idClient"));

				objClient.setNom(tuple.getString("nom"));
				objClient.setPrenom(tuple.getString("prenom"));
				objClient.setNumeroTel(tuple.getString("numTel"));
				objClient.setNumeroFax(tuple.getString("numFax"));
				objClient.setEmail(tuple.getString("eMail"));
				objClient.setSiret(tuple.getInt("siret"));

				objClient.setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
				objClient.setLog(loginDAO.findById(tuple.getInt("idLog")));

				// for (int i=0; i< ListClients.size(); i++)
				// {
				// ListClients.get(i).setAdresse(adresseDAO.findById(ListClients.get(i).getIdAdd()));
				// }

				// for (int i=0; i< ListClients.size(); i++)
				// {
				// ListClients.get(i).setLog(loginDAO.findById(ListClients.get(i).getIdLog()));
				// }

				// Ajout du nouvel objet Client créé à la liste des clients
				ListClients.add(objClient);
			} // fin de la boucle de parcoutuple de l'ensemble des résultats
			adresseDAO.fermetureConnexion();
			loginDAO.fermetureConnexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Retourne la liste de toutes les clients
		return ListClients;
	}

	@Override
	public Client findById(Integer idCli) {
		// Déclaration d'un objet Client
		Client objClient = null;
		AdresseDaoSql adresseDAO = new AdresseDaoSql();
		LoginDaoSql loginDAO = new LoginDaoSql();

		try {
			// Connexion à la BDD
			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM client WHERE idClient=?");
			// Cherche l'idVill voulu dans la BDD
			ps.setInt(1, idCli);

			// Récupération des résultats de la requête
			ResultSet tuple = ps.executeQuery();

			if (tuple.next()) {
				objClient = new Client(tuple.getInt("idClient"));
				objClient.setNom(tuple.getString("nom"));
				objClient.setPrenom(tuple.getString("prenom"));
				objClient.setNumeroTel(tuple.getString("numTel"));
				objClient.setNumeroFax(tuple.getString("numFax"));
				objClient.setEmail(tuple.getString("eMail"));
				objClient.setSiret(tuple.getInt("siret"));

				objClient.setAdresse(adresseDAO.findById(tuple.getInt("idAdd")));
				adresseDAO.fermetureConnexion();
				objClient.setLog(loginDAO.findById(tuple.getInt("idLog")));
				loginDAO.fermetureConnexion();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objClient;
	}

	public void create(Client client) {

		try {

			PreparedStatement requete = connexion.prepareStatement(
					"INSERT INTO client (idClient, nom, prenom, numTel, numFax, eMail, siret, idAdd) VALUES(?,?,?,?,?,?,?,?)");

			requete.setLong(1, client.getIdCli());
			requete.setString(2, client.getNom());
			requete.setString(3, client.getPrenom());
			requete.setString(4, client.getNumeroTel());
			requete.setString(5, client.getNumeroFax());
			requete.setString(6, client.getEmail());
			requete.setInt(7, client.getSiret());
			requete.setLong(8, client.getAdresse().getIdAdd());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Client update(Client client) {

		try {

			PreparedStatement requete = connexion.prepareStatement(
					"UPDATE client SET nom=?,prenom=?, numTel=?, numFax=?, eMail=?, siret=?, idAdd=? WHERE idClient = ?");

			requete.setString(1, client.getNom());
			requete.setString(2, client.getPrenom());
			requete.setString(3, client.getNumeroTel());
			requete.setString(4, client.getNumeroFax());
			requete.setString(5, client.getEmail());
			requete.setInt(6, client.getSiret());
			requete.setLong(7, client.getAdresse().getIdAdd());
			requete.setLong(8, client.getIdCli());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return client;
	}

	public void delete(Client client) {

		try {

			PreparedStatement ps = connexion.prepareStatement("DELETE FROM client WHERE idCLient = ?");
			ps.setLong(1, client.getIdCli());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
