package avis;

import exception.BadEntry;


public class Film extends Item {

	/**
	 * @uml.property  name="titre"
	 */
	private String titre;
	/**
	 * @uml.property  name="genre"
	 */
	private String genre;	
	/**
	 * @uml.property  name="realisateur"
	 */
	private String realisateur;
	/**
	 * @uml.property  name="scenariste"
	 */
	private String scenariste;	
	/**
	 * @uml.property  name="duree"
	 */
	private int duree;
	
	/**
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry{
		super();
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le pseudo, le password, le titre, le genre, le réalisateur ou le scénariste n'est (ne sont) pas instancié(s)
		if (titre==null || genre==null || realisateur==null || scenariste==null)
			throw new BadEntry("Le pseudo, le password, le réalisateur, le titre, le genre, le réalisateur et le scénariste doivent être instanciés.");

		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si la durée n'est pas positive
		if (duree<0)
			throw new BadEntry ("La durée doit être positive et saisie en minutes.");
		
		//===================================== CONSTRUCTION =======================================
		
		this.titre = titre.trim();
		this.genre = genre;
		this.scenariste = scenariste;
		this.realisateur = realisateur;
		this.duree = duree;
	}	
	
	/**
	 */
	public boolean titleIs(String titre){
		return (this.titre.toUpperCase().equals(titre.trim().toUpperCase()));	
	}
	
	/**
	 */
	public String toString(){
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Réalisateur : " + this.realisateur + "\n";
		s+= "Scénariste : " + this.scenariste + "\n";
		s+= "Durée : "+ this.duree+ "\n";
		//s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= "Avis : \n";
		s+= super.toString();
		return s;	
	}
				
	/**
	 */
	public boolean equals(Object o){
		if (o==null || !(o instanceof Film))
			return false;
		Film f = (Film)o;
		return (f.duree == this.duree && f.genre.equals(this.genre) && f.realisateur.equals(this.realisateur) && f.scenariste.equals(this.scenariste) && f.titre.equals(this.titre));
	}

}
