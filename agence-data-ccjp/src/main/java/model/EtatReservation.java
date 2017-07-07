package model;

public enum EtatReservation {
	ouvert("Ouvert"), ferme("Fermé");
	
	private final String label;
	
	private EtatReservation(String label){
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static EtatReservation permissiveValueOf(String label) {
	    for (EtatReservation e : values()) {
	        if (e.getLabel().equals(label)) {
	            return e;
	        }
	    }
	    return null;
	}
	
}
