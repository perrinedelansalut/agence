/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ajc
 *
 */
public class Client {

	/**
	 * 
	 */
	private int idCli;
	private String nom;
	private String numeroTel;
	private String numeroFax;
	private String email;
	private String prenom;
	private int siret;

	private List<Reservation> ListReservations;
	private Adresse adresse;
	private Login Log;

	public Client() {
		this.ListReservations = new ArrayList<>();
	}

	public Client(int idCli) {

		this();
		this.idCli = idCli;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public String getEmail() {
		return email;
	}

	public int getIdCli() {
		return idCli;
	}

	public List<Reservation> getListReservations() {
		return ListReservations;
	}

	public Login getLog() {
		return Log;
	}

	public String getNom() {
		return nom;
	}

	public String getNumeroFax() {
		return numeroFax;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getSiret() {
		return siret;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

	public void setListReservations(List<Reservation> listReservations) {
		ListReservations = listReservations;
	}

	public void setLog(Login log) {
		Log = log;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNumeroFax(String numeroFax) {
		this.numeroFax = numeroFax;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setSiret(int siret) {
		this.siret = siret;
	}

	public String toString() {
		String reponse = "Le Client : " + this.nom + " " + this.prenom + " " + getNumeroTel() + " a effectué la/les reservation(s) : \n";
		for (int i = 0; i < ListReservations.size(); i++) {
			reponse += "\n" + this.ListReservations.get(i).getNumero();
		}

		return reponse;
	}

}
