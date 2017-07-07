package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ville;

public class VilleDaoSQL implements VilleDao
{

    public VilleDaoSQL()
    {
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
    }

    private Connection connexion;

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

    public List<Ville> findAll()
    {
        // Liste des villes que l'on va retourner
        List<Ville> villes = new ArrayList<Ville>();
        // Connexion � la BDD
        try
        {
            /*
             * Connexion � la BDD
             */
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM ville");
            // 4. Execution de la requ�te
            ResultSet tuple = ps.executeQuery();
            // 5. Parcoutuple de l'ensemble des r�sultats (ResultSet) pour
            // r�cup�rer les valeutuple des colonnes du tuple qui correspondent
            // aux
            // valeur des attributs de l'objet
            while (tuple.next())
            {
                // Creation d'un objet Ville
                Ville ville = new Ville(tuple.getInt("id"),
                        tuple.getString("nom"));
                // Ajout du nouvel objet Ville cr�� � la liste des villes
                villes.add(ville);
            } // fin de la boucle de parcoutuple de l'ensemble des r�sultats

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        // Retourne la liste de toutes les villes
        return villes;
    }

    public Ville findById(Integer id)
    {
        // D�claration d'un objet ville
        Ville ville = null;

        try
        {
            // Connexion � la BDD
            PreparedStatement ps = connexion
                    .prepareStatement("SELECT * FROM ville where id=?");
            // Cherche l'idVill voulu dans la BDD
            ps.setInt(1, id);

            // R�cup�ration des r�sultats de la requ�te
            ResultSet tuple = ps.executeQuery();

            if (tuple.next())
            {
                ville = new Ville(tuple.getInt("id"), tuple.getString("nom"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ville;
    }

    public void create(Ville ville)
    {

        try
        {

            PreparedStatement requete = connexion.prepareStatement(
                    "INSERT INTO ville (id, nom) VALUES(?,?)");

            requete.setLong(1, ville.getIdVil());
            requete.setString(2, ville.getNom());

            requete.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Ville update(Ville ville)
    {

        try
        {
            PreparedStatement ps = connexion
                    .prepareStatement("UPDATE ville SET nom=? WHERE id = ?");

            ps.setString(1, ville.getNom());
            ps.setInt(2, ville.getIdVil());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ville;
    }

    public void delete(Ville ville)
    {

        try
        {
            PreparedStatement ps = connexion
                    .prepareStatement("delete from ville where id = ?");
            ps.setLong(1, ville.getIdVil());

            ps.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
