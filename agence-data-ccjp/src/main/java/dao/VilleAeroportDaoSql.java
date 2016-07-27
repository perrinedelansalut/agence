package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.VilleAeroport;

public class VilleAeroportDaoSql implements VilleAeroportDao {

	public VilleAeroportDaoSql() {
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

	private Connection connexion;

	public void fermetureConnexion() {
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<VilleAeroport> findAll() {
		List<VilleAeroport> villeAeroports = new ArrayList<VilleAeroport>();
		AeroportDaoSQL aeroportDAO = new AeroportDaoSQL();
		VilleDaoSQL villeDAO = new VilleDaoSQL();
		try {
			/*
			 * Connexion à la BDD
			 */

			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM ville_aeroport");
			// 4. Execution de la requête
			ResultSet tuple = ps.executeQuery();
			// 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
			// récupérer les valeutuple des colonnes du tuple qui correspondent
			// aux
			// valeur des attributs de l'objet
			while (tuple.next()) {
				// Creation d'un objet Aeroport
				VilleAeroport villeAeroport = new VilleAeroport(tuple.getInt("id"));
				villeAeroport.setVille(villeDAO.findById(tuple.getInt("idVille")));
				villeAeroport.setAeroport(aeroportDAO.findById(tuple.getInt("idAeroport")));
				// Ajout du nouvel objet Aeroport créé à la liste des aéroports
				villeAeroports.add(villeAeroport);
			} // fin de la boucle de parcoutuple de l'ensemble des résultats

			// for (int i=0; i<villeAeroports.size(); i++)
			// {
			// villeAeroports.get(i).setVille(villeDAO.findById(villeAeroports.get(i).getIdVille()));
			// }

			// for (int i=0; i<villeAeroports.size(); i++)
			// {
			// villeAeroports.get(i).setAeroport(aeroportDAO.findById(villeAeroports.get(i).getIdAeroport()));
			// }
			villeDAO.fermetureConnexion();
			aeroportDAO.fermetureConnexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Retourne la liste de tous les aéroports
		return villeAeroports;
	}

	@Override
	public VilleAeroport findById(Integer id) {
		// Déclaration d'un objet aeroport
		VilleAeroport villeAeroport = null;
		AeroportDaoSQL aeroportDAO = new AeroportDaoSQL();
		VilleDaoSQL villeDAO = new VilleDaoSQL();

		try {

			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM ville_aeroport where id=?");
			// Cherche l'idAero voulu dans la BDD
			ps.setInt(1, id);

			// Récupération des résultats de la requête
			ResultSet tuple = ps.executeQuery();

			if (tuple.next()) {
				villeAeroport = new VilleAeroport(tuple.getInt("id"));
				villeAeroport.setVille(villeDAO.findById(tuple.getInt("idVille")));
				villeDAO.fermetureConnexion();
				villeAeroport.setAeroport(aeroportDAO.findById(tuple.getInt("idAeroport")));
				aeroportDAO.fermetureConnexion();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return villeAeroport;
	}

	@Override
	public void create(VilleAeroport villeAeroport) {

		try {
			// prépare la requete d'insertion, celle ci aura 5 paramètres
			PreparedStatement requete;
			requete = connexion.prepareStatement("insert into ville_aeroport (id,idVille,idAeroport) VALUES(?,?,?)");
			// set sur le paramètre 1
			requete.setInt(1, villeAeroport.getId());
			// set sur le paramètre 2 ..
			requete.setInt(2, villeAeroport.getVille().getIdVil());
			requete.setInt(3, villeAeroport.getAeroport().getIdAer());

			// executeUpdate, different de executequery que l'on a dans FindAll
			// et FindById
			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public VilleAeroport update(VilleAeroport villeAeroport) {

		try {
			PreparedStatement ps = connexion
					.prepareStatement("UPDATE ville_aeroport SET idVille=?, idAeroport=? WHERE id = ?");

			ps.setInt(1, villeAeroport.getVille().getIdVil());
			ps.setInt(2, villeAeroport.getAeroport().getIdAer());
			ps.setInt(3, villeAeroport.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return villeAeroport;
	}

	@Override
	public void delete(VilleAeroport villeAeroport) {

		try {
			PreparedStatement ps = connexion.prepareStatement("DELETE FROM ville_aeroport WHERE id = ?");
			ps.setLong(1, villeAeroport.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
