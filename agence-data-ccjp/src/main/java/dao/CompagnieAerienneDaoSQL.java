package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CompagnieAerienne;

public class CompagnieAerienneDaoSQL implements CompagnieAerienneDao {

	public CompagnieAerienneDaoSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. Créer la connexion à la base (on instancie l'objet connexion)
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/vol", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public List<CompagnieAerienne> findAll() {
		// Liste des compagnies aeriennes que l'on va retourner
		List<CompagnieAerienne> compagniesAeriennes = new ArrayList<CompagnieAerienne>();
		// Connexion à la BDD
		try {
			/*
			 * Connexion à la BDD
			 */
			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM compagnie_aerienne");
			// 4. Execution de la requête
			ResultSet tuple = ps.executeQuery();
			// 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
			// récupérer les valeutuple des colonnes du tuple qui correspondent
			// aux
			// valeur des attributs de l'objet
			while (tuple.next()) {
				// Creation d'un objet compagnieAerienne
				CompagnieAerienne compagnieAerienne = new CompagnieAerienne(tuple.getInt("id"), tuple.getString("nom"));
				// Ajout du nouvel objet compagnieAerienne créé à la liste des
				// compagniesAeriennes
				compagniesAeriennes.add(compagnieAerienne);
			} // fin de la boucle de parcoutuple de l'ensemble des résultats

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Retourne la liste de tous les aéroports
		return compagniesAeriennes;
	}

	public CompagnieAerienne findById(Integer id) {
		// Déclaration d'un objet compagnieAerienne
		CompagnieAerienne compagnieAerienne = null;

		try {
			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM compagnie_aerienne where id=?");
			// Cherche l'idComp recherché dans la BDD
			ps.setInt(1, id);

			// Récupération des résultats de la requête
			ResultSet tuple = ps.executeQuery();

			if (tuple.next()) {
				compagnieAerienne = new CompagnieAerienne(tuple.getInt("id"), tuple.getString("nom"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compagnieAerienne;
	}

	@Override
	public void create(CompagnieAerienne compagnieAerienne) {

		try {
			PreparedStatement requete = connexion
					.prepareStatement("INSERT INTO compagnie_aerienne (id, nom) VALUES(?,?)");

			requete.setInt(1, compagnieAerienne.getId());
			requete.setString(2, compagnieAerienne.getNom());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public CompagnieAerienne update(CompagnieAerienne compagnieAerienne) {

		try {
			PreparedStatement ps = connexion.prepareStatement("UPDATE compagnie_aerienne SET nom=? WHERE id = ?");

			ps.setString(1, compagnieAerienne.getNom());
			ps.setInt(2, compagnieAerienne.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compagnieAerienne;
	}

	public void delete(CompagnieAerienne compagnieAerienne) {

		try {
			PreparedStatement ps = connexion.prepareStatement("delete from compagnie_aerienne where id = ?");
			ps.setLong(1, compagnieAerienne.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
