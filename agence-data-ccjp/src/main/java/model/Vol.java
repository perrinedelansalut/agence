package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ajc
 */
public class Vol
{

    /**
     * id du vol
     */
    private int idVol;
    /**
     * date de départ du vol
     */
    private Date dateDepart;
    /**
     * date d'arrivé du vol
     */
    private Date dateArrivee;
    /**
     * heure départ du vol
     */
    private Time heureDepart;
    /**
     * heure d'arrivée du vol
     */
    private Time heureArrivee;
    private Aeroport aeroportDepart;
    private Aeroport aeroportArrivee;
    /**
     * liste d'escales par lesquelles on peut passer
     */
    private List<Escale> escales;

    /**
     * constructeur de vol
     * 
     * @param idVol
     *            l'ID du vol
     */
    public Vol(int idVol)
    {
        this.idVol = idVol;
        this.escales = new ArrayList<Escale>();
    }

    public Aeroport getAeroportArrivee()
    {
        return aeroportArrivee;
    }

    public Aeroport getAeroportDepart()
    {
        return aeroportDepart;
    }

    public Date getDateArrivee()
    {
        return dateArrivee;
    }

    public Date getDateDepart()
    {
        return dateDepart;
    }

    public List<Escale> getEscales()
    {
        return escales;
    }

    public Time getHeureArrivee()
    {
        return heureArrivee;
    }

    public Time getHeureDepart()
    {
        return heureDepart;
    }

    public int getIdVol()
    {
        return idVol;
    }

    public void setAeroportArrivee(Aeroport aeroportArrivee)
    {
        this.aeroportArrivee = aeroportArrivee;
    }

    public void setAeroportDepart(Aeroport aeroportDepart)
    {
        this.aeroportDepart = aeroportDepart;
    }

    public void setDateArrivee(Date dateArrivee)
    {
        this.dateArrivee = dateArrivee;
    }

    public void setDateDepart(Date dateDepart)
    {
        this.dateDepart = dateDepart;
    }

    public void setEscales(List<Escale> escales)
    {
        this.escales = escales;
    }

    public void setHeureArrivee(Time heureArrivee)
    {
        this.heureArrivee = heureArrivee;
    }

    public void setHeureDepart(Time heureDepart)
    {
        this.heureDepart = heureDepart;
    }

    public void setIdVol(int idVol)
    {
        this.idVol = idVol;
    }

    public String toString()
    {
        String reponse = "Le vol  de : " + this.aeroportDepart.getNom()
                + " qui part le " + this.dateDepart + " à " + this.heureDepart
                + "\n Arrivera à " + this.aeroportArrivee.getNom() + " à "
                + this.heureArrivee + "\nIl fera des escales à : ";
        for (int i = 0; i < escales.size(); i++)
        {
            reponse += "\n" + this.escales.get(i).getAeoroport().getNom()
                    + " le " + this.escales.get(i).getDateArrivee() + " à "
                    + this.escales.get(i).getHeureArrivee() + "jusqu'au"
                    + this.escales.get(i).getDateDepart() + " à "
                    + this.escales.get(i).getHeureDepart();
        }

        return reponse;
    }
}
