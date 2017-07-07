package model;

import java.util.ArrayList;
import java.util.List;

public class Aeroport
{

    /**
     * id de l'aéroport
     */
    private int idAer;
    /**
     * Nom de l'aéroport
     */
    private String nom;

    /**
     * Liste des villes désservies par l'aéroport
     */
    private List<Ville> villes = new ArrayList<Ville>();

    public Aeroport(int idAer, String nom)
    {
        this.nom = nom;
        this.idAer = idAer;
    }

    /**
     * Ajout d'une ville existante à la liste des villes desservies par
     * l'aéroport
     * 
     * @param ville
     *            Ville à ajouter
     */
    public void ajouterVille(Ville ville)
    {
        this.villes.add(ville); // ajout d'une ville déjà existante
    }

    public int getIdAer()
    {
        return idAer;
    }

    public String getNom()
    {
        return nom;
    }

    public List<Ville> getVilles()
    {
        return villes;
    }

    public void setIdAer(int idAer)
    {
        this.idAer = idAer;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setVilles(List<Ville> villes)
    {
        this.villes = villes;
    }

    @Override
    public String toString()
    {
        return "Aeroport [idAer=" + idAer + ", nom=" + nom + ", villes="
                + villes + "]";
    }

}
