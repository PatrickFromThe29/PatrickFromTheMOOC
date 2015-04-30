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
		// Si le pseudo, le password, le titre, le genre ou l'auteur n'est (ne sont) pas instancié(s)
		if (titre==null || genre==null || auteur==null)
			throw new BadEntry("Le pseudo, le password, l'auteur, le titre et le genre doivent être instanciés.");
		
		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si le nombre de pages n'est pas positif
		if (nbPages<0)
			throw new BadEntry ("Lae nombre de pages doit être positif.");
				
		//===================================== CONSTRUCTION =======================================
		
		this.titre = titre.trim();
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
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
		s+= "Auteur : " + this.auteur + "\n";
		s+= "Nombre de pages : " + this.nbPages + "\n";
		//s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= "Avis : \n";
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
