package model;

public class CompagnieAerienneVol {

	private int id;
	private CompagnieAerienne compagnieAerienne;
	private Vol vol;
	private String numero;

	private short ouvert;
	
	public CompagnieAerienneVol(String numero, short ouvert) {
		this.numero = numero;
		this.ouvert = ouvert;
	}

	public int getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public short getOuvert() {
		return ouvert;
	}

	public Vol getVol() {
		return vol;
	}

	public short isOuvert() {
		return ouvert;
	}

	public CompagnieAerienne getCompagnieAerienne() {
		return compagnieAerienne;
	}
	
	public void setCompagnieAerienne(CompagnieAerienne compagnieAerienne) {
		this.compagnieAerienne = compagnieAerienne;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setOuvert(short ouvert) {
		this.ouvert = ouvert;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	@Override
	public String toString() {
		return "CompagnieAerienneVol [id=" + id + " compagnieAerienne="
				+ compagnieAerienne + ", vol=" + vol + ", numero=" + numero + ", ouvert=" + ouvert + "]";
	}

}
