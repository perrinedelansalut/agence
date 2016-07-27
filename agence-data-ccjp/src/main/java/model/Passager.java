package model;

public class Passager {

	/**
	 * Id du passager
	 */
	private int idPas;
	/**
	 * Nom du passager
	 */
	private String nom;
	/**
	 * Prenom du passager
	 */
	private String prenom;

	private Adresse adresse;

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Passager(int idPas) {

		this.idPas = idPas;

	}

	public int getIdPas() {
		return idPas;
	}

	public void setIdPas(int idPas) {
		this.idPas = idPas;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return "Passager [idPas=" + idPas + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
	}

}
