package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Vol;

public class VolDaoSql implements VolDao
{

    private Connection connexion;

    public VolDaoSql()
    {
        /*
         * Connexion � la BDD
         */
        // 1. Chargement du driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 2. Cr�er la connexion � la base (on instancie l'objet connexion)
        try
        {
            connexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vol", "user", "password");
        }
        catch (SQLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 3. Cr�ation d'une requ�te (statement) � partir de l'objet
        // connexion
    }

    public void fermetureConnexion()
    {
        try
        {
            connexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Vol> findAll()
    {
        // Liste des vols que l'on va retourner
        List<Vol> vols = new ArrayList<Vol>();
        // Cr�ation d'un objet aeroport pour faire un findbyid;
        AeroportDaoSQL aeroportDAO = new AeroportDaoSQL();
        // Connexion � la BDD
        try
        {

            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM vol");
            // 4. Execution de la requ�te
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des r�sultats (ResultSet) pour
            // r�cup�rer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Vol
                Vol vol = new Vol(tuple.getInt("idVol"));
                vol.setDateArrivee(tuple.getDate("dateArrivee"));
                vol.setDateDepart(tuple.getDate("dateDepart"));
                vol.setHeureArrivee(tuple.getTime("heureArrivee"));
                vol.setHeureDepart(tuple.getTime("heureDepart"));
                vol.setAeroportArrivee(aeroportDAO
                        .findById(tuple.getInt("idAeroportArrivee")));
                vol.setAeroportDepart(
                        aeroportDAO.findById(tuple.getInt("idAeroportDepart")));
                // Ajout du nouvel objet vol cr�� � la liste des vols
                vols.add(vol);
            } // fin de la boucle de parcoutuple de l'ensemble des r�sultats

            // fermeture de la base aeroport
            aeroportDAO.fermetureConnexion();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // Retourne la liste de tous les a�roports
        return vols;
    }

    public Vol findById(Integer idVol)
    {
        // D�claration d'un objet vol
        Vol vol = null;
        // Cr�ation d'un objet aeroport pour faire un findbyid;
        AeroportDaoSQL aeroportDAO = new AeroportDaoSQL();

        try
        {
            PreparedStatement requete = connexion
                    .prepareStatement("SELECT * FROM vol where idVol=?");
            // Cherche l'idVol voulu dans la BDD
            requete.setInt(1, idVol);

            // R�cup�ration des r�sultats de la requ�te
            ResultSet tuple = requete.executeQuery();

            if (tuple.next())
            {
                vol = new Vol(tuple.getInt("idVol"));
                vol.setDateArrivee(tuple.getDate("dateArrivee"));
                vol.setDateDepart(tuple.getDate("dateDepart"));
                vol.setHeureArrivee(tuple.getTime("heureArrivee"));
                vol.setHeureDepart(tuple.getTime("heureDepart"));
                vol.setAeroportArrivee(aeroportDAO
                        .findById(tuple.getInt("idAeroportArrivee")));
                vol.setAeroportDepart(
                        aeroportDAO.findById(tuple.getInt("idAeroportDepart")));
                // fermeture de la base aeroport
                aeroportDAO.fermetureConnexion();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return vol;
    }

    @Override
    public void create(Vol vol)
    {

        try
        {
            PreparedStatement requete = connexion.prepareStatement(
                    "INSERT INTO vol (idVol, dateDepart, dateArrivee, heureDepart, heureArrivee, idAeroportDepart, idAeroportArrivee) VALUES(?,?,?,?,?,?,?)");

            requete.setLong(1, vol.getIdVol());
            requete.setDate(2,
                    new java.sql.Date(vol.getDateDepart().getTime()));
            requete.setDate(3,
                    new java.sql.Date(vol.getDateArrivee().getTime()));
            requete.setTime(4,
                    new java.sql.Time(vol.getHeureDepart().getTime()));
            requete.setTime(5,
                    new java.sql.Time(vol.getHeureArrivee().getTime()));
            requete.setLong(6, vol.getAeroportDepart().getIdAer());
            requete.setLong(7, vol.getAeroportArrivee().getIdAer());

            requete.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Vol update(Vol vol)
    {

        try
        {
            PreparedStatement ps = connexion.prepareStatement(
                    "UPDATE vol SET dateDepart=?,dateArrivee=?,heureDepart=?,heureArrivee=?,idAeroportDepart=?,idAeroportArrivee=? WHERE idVol = ?");

            ps.setDate(1, new java.sql.Date(vol.getDateDepart().getTime()));
            ps.setDate(2, new java.sql.Date(vol.getDateArrivee().getTime()));
            ps.setTime(3, new java.sql.Time(vol.getHeureDepart().getTime()));
            ps.setTime(4, new java.sql.Time(vol.getHeureArrivee().getTime()));
            ps.setLong(5, vol.getAeroportDepart().getIdAer());
            ps.setLong(6, vol.getAeroportArrivee().getIdAer());
            ps.setLong(7, vol.getIdVol());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return vol;
    }

    public void delete(Vol vol)
    {

        try
        {
            PreparedStatement ps = connexion
                    .prepareStatement("delete from vol where idVol = ?");
            ps.setLong(1, vol.getIdVol());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
