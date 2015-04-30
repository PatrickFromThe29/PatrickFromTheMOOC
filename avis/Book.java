package avis;

import exception.BadEntry;


public class Book extends Item {
	
	/**
	 * @uml.property  name="titre"
	 */
	private String titre;
	/**
	 * @uml.property  name="genre"
	 */
	private String genre;
	/**
	 * @uml.property  name="auteur"
	 */
	private String auteur;	
	/**
	 * @uml.property  name="nbPages"
	 */
	private int nbPages;
	
	
	/**
	 */
	public Book(String titre, String genre, String auteur, int nbPages) throws BadEntry{
		super();
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si Le titre, le genre ou l'auteur n'est (ne sont) pas instancié(s)
		if (titre==null || genre==null || auteur==null)
			throw new BadEntry("Le titre,le genre et l'auteur doivent être instanciés.");
		
		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si le nombre de pages n'est pas positif
		if (nbPages<0)
			throw new BadEntry ("Le nombre de pages doit être positif.");
				
		//===================================== CONSTRUCTION =======================================
		
		this.titre = titre.trim();
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	
	/**
	 */
	public boolean titleIs(String titre) throws BadEntry{
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le titre du Book envoyé est null ou comporte moins de 1 caractère qui n'est pas un espace, BadEntry est levée.
		if (titre==null || titre.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le nom du livre à rechercher doit être instancié et comporter au moins un caractère différent d'un espace)");
		
		//===================================== COMPARAISON ========================================
		return (this.titre.toUpperCase().equals(titre.trim().toUpperCase()));	
	}
	
	/**
	 */
	public String toString(){
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Auteur : " + this.auteur + "\n";
		s+= "Nombre de pages : " + this.nbPages + "\n";
		//s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= super.toString();
		return s;	
	}
				
	/**
	 */
	public boolean equals(Object o){
		if (o==null || !(o instanceof Book))
			return false;
		
		Book b = (Book)o;
		return (b.titre.equals(this.titre) && b.genre.equals(this.genre) && b.auteur.equals(this.auteur) && b.nbPages==this.nbPages);
	}

}
