package avis;

import exception.BadEntry;

/**
 * Représente un Film
 * @author Yann Andreu et Yannick Omnès
 *
 */
public class Film extends Item {

	/**
	 * genre du film
	 * @uml.property  name="genre"
	 */
	private String genre;	
	/**
	 * realisateur du film
	 * @uml.property  name="realisateur"
	 */
	private String realisateur;
	/**
	 * scenariste du film
	 * @uml.property  name="scenariste"
	 */
	private String scenariste;	
	/**
	 * durée du film
	 * @uml.property  name="duree"
	 */
	private int duree;
	
	/**
	 * Construit un nouveau Film
	 * @param titre le titre du film
	 * @param genre le genre du film
	 * @param realisateur le réalisateur du film
	 * @param scenariste le scenariste du film
	 * @param duree la durée du film
	 * @throws BadEntry
	 * <ul>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le genre, le réalisateur ou le scénariste ne sont pas instanciés. </li>
	 *  <li>  si la durée est négative.  </li>
	 * </ul>   
	 */
	public Film(String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry{
		super();
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le titre, le genre, le réalisateur ou le scénariste n'est (ne sont) pas instancié(s)
		if (titre==null || genre==null || realisateur==null || scenariste==null)
			throw new BadEntry("Le titre, le genre, le réalisateur et le scénariste doivent être instanciés.");

		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si la durée n'est pas positive
		if (duree<=0)
			throw new BadEntry ("La durée doit être positive et saisie en minutes.");
		
		//===================================== CONSTRUCTION =======================================
		
		this.titre = titre.trim();
		this.genre = genre;
		this.scenariste = scenariste;
		this.realisateur = realisateur;
		this.duree = duree;
	}	
	
	/**
	 * Renvoie une représentation textuelle du Film courant
	 * @return un String comprenant la représentation textuelle du Film courant
	 */
	public String toString(){
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Réalisateur : " + this.realisateur + "\n";
		s+= "Scénariste : " + this.scenariste + "\n";
		s+= "Durée : "+ this.duree+ "\n";
		//s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= super.toString(); // On appelle la méthode toString() de Item pour inclure l'ensemble des reviews (la liste étant private)
		return s;	
	}
				
	/**
	 * Compare le Film courant à l'objet passé en paramètre
	 * @param o l'objet à comparer au Film courant
	 * @return true si l'objet o est égal au Film courant, false sinon
	 */
	public boolean equals(Object o){
		if (o==null || !(o instanceof Film))
			return false;
		Film f = (Film)o;
		return (f.duree == this.duree && f.genre.equals(this.genre) && f.realisateur.equals(this.realisateur) && f.scenariste.equals(this.scenariste) && f.titre.equals(this.titre));
	}

}
