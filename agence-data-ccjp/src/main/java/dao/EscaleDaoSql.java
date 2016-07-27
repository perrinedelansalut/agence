package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Escale;

public class EscaleDaoSql implements EscaleDao {

	public EscaleDaoSql() {
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

	public List<Escale> findAll() {
		// Liste des escales que l'on va retourner
		List<Escale> escales = new ArrayList<Escale>();
		AeroportDaoSQL aeroportDAO = new AeroportDaoSQL();
		VolDaoSql volDAO = new VolDaoSql();
		try {
			// connexion
			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM escale");
			// 4. Execution de la requête
			ResultSet tuple = ps.executeQuery();
			// 5. Parcoutuple de l'ensemble des résultats (ResultSet) pour
			// récupérer les valeutuple des colonnes du tuple qui correspondent
			// aux
			// valeur des attributs de l'objet
			while (tuple.next()) {
				// Creation d'un objet escale
				Escale escale = new Escale(tuple.getInt("idEscale"));
				escale.setDateArrivee(tuple.getDate("dateArrivee"));
				escale.setDateDepart(tuple.getDate("dateDepart"));
				escale.setHeureArrivee(tuple.getTime("heureArrivee"));
				escale.setHeureDepart(tuple.getTime("heureDepart"));
				// ajout des id Adress
				escale.setVol(volDAO.findById(tuple.getInt("idVol")));
				// ajout des aeroports
				escale.setAeoroport(aeroportDAO.findById(tuple.getInt("idAeroport")));
				// Ajout du nouvel objet Aeroport créé à la liste des aéroports
				escales.add(escale);
			} // fin de la boucle de parcoutuple de l'ensemble des résultats
				// ajout des vols;

			volDAO.fermetureConnexion();
			aeroportDAO.fermetureConnexion();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Retourne la liste de tous les aéroports
		return escales;
	}

	public Escale findById(Integer idEscale) {
		Escale escale = new Escale();
		AeroportDaoSQL aeroport = new AeroportDaoSQL();
		VolDaoSql vol = new VolDaoSql();

		try {

			PreparedStatement ps = connexion.prepareStatement("SELECT * FROM escale where idEscale=?");
			// Cherche l'idVol voulu dans la BDD
			ps.setInt(1, idEscale);

			// Récupération des résultats de la requête
			ResultSet tuple = ps.executeQuery();

			if (tuple.next()) {
				escale.setIdEscale(tuple.getInt("idEscale"));
				escale.setDateArrivee(tuple.getDate("dateArrivee"));
				escale.setDateDepart(tuple.getDate("dateDepart"));
				escale.setHeureArrivee(tuple.getTime("heureArrivee"));
				escale.setHeureDepart(tuple.getTime("heureDepart"));
				escale.setVol(vol.findById(tuple.getInt("idVol")));
				vol.fermetureConnexion();
				escale.setAeoroport(aeroport.findById(tuple.getInt("idAeroport")));
				aeroport.fermetureConnexion();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return escale;
	}

	@Override
	public void create(Escale escale) {

		try {
			PreparedStatement requete = connexion.prepareStatement(
					"INSERT INTO escale (idEscale, dateDepart, dateArrivee, heureDepart, heureArrivee, idAeroport, idVol) VALUES(?,?,?,?,?,?,?)");

			requete.setLong(1, escale.getIdEscale());
			requete.setDate(2, new java.sql.Date(escale.getDateDepart().getTime()));
			requete.setDate(3, new java.sql.Date(escale.getDateArrivee().getTime()));
			requete.setTime(4, new java.sql.Time(escale.getHeureDepart().getTime()));
			requete.setTime(5, new java.sql.Time(escale.getHeureArrivee().getTime()));
			requete.setLong(6, escale.getAeoroport().getIdAer());
			requete.setLong(7, escale.getVol().getIdVol());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Escale update(Escale escale) {

		try {
			PreparedStatement requete = connexion.prepareStatement(
					"UPDATE escale SET dateDepart=?,dateArrivee=?,heureDepart=?,heureArrivee=?,idAeroport=?,idVol=? WHERE idEscale = ?");

			requete.setDate(1, new java.sql.Date(escale.getDateDepart().getTime()));
			requete.setDate(2, new java.sql.Date(escale.getDateArrivee().getTime()));
			requete.setTime(3, new java.sql.Time(escale.getHeureDepart().getTime()));
			requete.setTime(4, new java.sql.Time(escale.getHeureArrivee().getTime()));
			requete.setLong(5, escale.getAeoroport().getIdAer());
			requete.setLong(6, escale.getVol().getIdVol());
			requete.setLong(7, escale.getIdEscale());

			requete.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return escale;
	}

	@Override
	public void delete(Escale escale) {

		try {
			PreparedStatement ps = connexion.prepareStatement("delete from escale where idEscale = ?");
			ps.setLong(1, escale.getIdEscale());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
