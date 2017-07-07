package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aeroport;

public class AeroportDaoSQL implements AeroportDao {

	/**
	 * 
	 */
	public AeroportDaoSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2. Créer la connexion à la base (on instancie l'objet connexion)
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/vol", "user", "");
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

	public List<Aeroport> findAll() {
		// Liste des aéroports que l'on va retourner
		List<Aeroport> aeroports = new ArrayList<Aeroport>();
		try {
			/*
			 * Connexion à la BDD
			 */

			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM aeroport");
			// 4. Execution de la requête
			ResultSet tuple = ps.executeQuery();
			// 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
			// récupérer les valeutuple des colonnes du tuple qui correspondent
			// aux
			// valeur des attributs de l'objet
			while (tuple.next()) {
				// Creation d'un objet Aeroport
				Aeroport aeroport = new Aeroport(tuple.getInt("idAero"), tuple.getString("nom"));
				// Ajout du nouvel objet Aeroport créé à la liste des aéroports
				aeroports.add(aeroport);
			} // fin de la boucle de parcoutuple de l'ensemble des résultats
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Retourne la liste de tous les aéroports
		return aeroports;
	}

	public Aeroport findById(Integer idAero) {
		// Déclaration d'un objet aeroport
		Aeroport aeroport = null;
		try {

			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM aeroport where idAero=?");
			// Cherche l'idAero voulu dans la BDD
			ps.setInt(1, idAero);

			// Récupération des résultats de la requête
			ResultSet tuple = ps.executeQuery();

			if (tuple.next()) {
				aeroport = new Aeroport(tuple.getInt("idAero"), tuple.getString("nom"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aeroport;
	}

	@Override
	public void create(Aeroport aeroport) {

		try {
			PreparedStatement requete = connexion.prepareStatement("INSERT INTO aeroport (idAero, nom) VALUES(?,?)");

			requete.setInt(1, aeroport.getIdAer());
			requete.setString(2, aeroport.getNom());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Aeroport update(Aeroport aeroport) {

		try {
			PreparedStatement ps = connexion.prepareStatement("UPDATE aeroport SET nom=? WHERE idAero = ?");

			ps.setString(1, aeroport.getNom());
			ps.setInt(2, aeroport.getIdAer());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aeroport;
	}

	public void delete(Aeroport aeroport) {

		try {

			PreparedStatement ps = connexion.prepareStatement("delete from aeroport where idAero = ?");
			ps.setLong(1, aeroport.getIdAer());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
