package model;

public class VilleAeroport {

	private int id;
	private Ville ville;
	private Aeroport aeroport;

	public VilleAeroport(){
		
	}
	
	public VilleAeroport(int id){
		this();
		this.id = id;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public int getId() {
		return id;
	}


	public Ville getVille() {
		return ville;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	public String toString() {
		String reponse="La Ville : "+ville.getNom()+" est desservie par l'aéroport : "+aeroport.getNom()+".";
				
		return reponse;
}

}
