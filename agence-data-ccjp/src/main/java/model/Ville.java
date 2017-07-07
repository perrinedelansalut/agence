package model;

public class Ville
{

    /**
     * Id de la ville
     */
    private int idVil;

    /**
     * Nom de la ville
     */
    private String nom;

    public Ville(int idVil, String nom)
    {
        this.idVil = idVil;
        this.nom = nom;
    }

    public int getIdVil()
    {
        return idVil;
    }

    public void setIdVil(int idVil)
    {
        this.idVil = idVil;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String toString()
    {
        String reponse = "La Ville : " + this.nom;

        return reponse;
    }

}
