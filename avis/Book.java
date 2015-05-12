package avis;

import exception.BadEntry;

/**
 * Représente un livre
 * @author Yann Andreu et Yannick Omnès
 *
 */
public class Book extends Item {
	
	/**
	 * genre du livre
	 * @uml.property  name="genre"
	 */
	private String genre;
	/**
	 * auteur du livre
	 * @uml.property  name="auteur"
	 */
	private String auteur;	
	/**
	 * nombre de pages du livre
	 * @uml.property  name="nbPages"
	 */
	private int nbPages;
	
	
	/**
	 * Construit un livre
	 * @param titre titre du livre à créer
	 * @param genre genre du livre à créer
	 * @param auteur auteur du livre à créer
	 * @param nbPages nombre de pages du livre à créer
	 * @throws BadEntry 
	 * <ul>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le genre et/ou l'auteur n'est (ne sont pas) instancié(s). </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul>    
	 */
	public Book(String titre, String genre, String auteur, int nbPages) throws BadEntry{
		super(); //Le constructeur sans paramètres a été redéfini de façon explicite dans la classe Item pour instancier une LinkedList de Review
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si Le titre, le genre ou l'auteur n'est (ne sont) pas instancié(s)
		if (titre==null || genre==null || auteur==null)
			throw new BadEntry("Le titre,le genre et l'auteur doivent être instanciés.");
		
		//Si le titre a moins de 1 caractère autre que des espaces
		if (titre.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le titre doit contenir au moins 1 caractère autre que des espaces.");
		
		// Si le nombre de pages n'est pas positif
		if (nbPages<=0)
			throw new BadEntry ("Le nombre de pages doit être positif.");
				
		//===================================== CONSTRUCTION =======================================
		
		this.titre = titre.trim();
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
	}
	
	
	/**
	 * Renvoie la représentation du Book sous forme d'une chaîne de caractères
	 * @return la représentation du Book sous forme d'un String
	 */
	public String toString(){
		String s= "\nTitre : "+ this.titre +"\n";
		s+= "Genre : " + this.genre + "\n";
		s+= "Auteur : " + this.auteur + "\n";
		s+= "Nombre de pages : " + this.nbPages + "\n";
		//s+= "Note moyenne : " + this.moyenneNotesReview() + "/5 \n";
		s+= super.toString();// On appelle la méthode toString() de Item pour inclure l'ensemble des reviews (la liste étant private)
		return s;	
	}
				
	/**
	 * Teste l'égalité entre l'objet courant et celui passé en paramètre
	 * @param o l'objet à comparer au Book courant
	 * @return true si o est égal au Book courant, false sinon
	 */
	public boolean equals(Object o){
		if (o==null || !(o instanceof Book))
			return false;
		
		Book b = (Book)o;
		return (b.titre.equals(this.titre) && b.genre.equals(this.genre) && b.auteur.equals(this.auteur) && b.nbPages==this.nbPages);
	}

}
