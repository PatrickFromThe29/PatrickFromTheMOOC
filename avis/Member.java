package avis;

import java.util.LinkedList;

import exception.BadEntry;

/**
 * Représente un membre du réseau social
 * @author Yann Andreu et Yannick Omnès
 *
 */
public class Member{

	/**
	 * pseudo du Member
	 * @uml.property  name="pseudo" readOnly="true"
	 */
	private String pseudo;

	/**
	 * mot de passe du Member
	 * @uml.property  name="password"
	 */
	private String password;
	
	/**
	 * profil du Member
	 * @uml.property  name="profil"
	 */
	private String profil;
	
	/**
	 * @uml.property  name="karma" readOnly="true"
	 */
	private float karma;

	/**
	 * Liste des Review déposées par le Member sur des Item du SocialNetwork
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="member:avis.Review"
	 */
	private LinkedList<Review> reviews;
	
	/**
	 * Construit un nouveau Member
	 * @param pseudo pseudo du nouveau Member
	 * @param password mot de passe du nouveau Member
	 * @param profil profil du nouveau Member
	 * @throws BadEntry
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces.</li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leading or trailing blanks.</li>
	 *  <li>  si le profil n'est pas instancié.</li>
	 * </ul>  
	 */
	public Member(String pseudo, String password, String profil) throws BadEntry{
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		
		// Si l'une des informations n'est pas instanciée, on lève l'exception BadEntry
		if (pseudo==null || password==null || profil==null)
			throw new BadEntry("le pseudo, le password et le profil doivent être instanciés");
		
		// Si le pseudo a moins de 1 caractère autres que des espaces on lève BadEntry
		if (pseudo.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le pseudo doit contenir au moins un caractère qui n'est pas un espace.");
		
		//Si le password a moins de 4 caractères autres que des leading ou trailing blanks, on lève BadEntry
		if(password.trim().length()<4)
			throw new BadEntry("Le mot de passe doit contenir au moins 4 caractères autres que des espaces en début et en fin.");

		//===================================== CONSTRUCTION =======================================
		this.pseudo = pseudo.trim();
		this.password= password.trim();
		this.profil = profil;
		this.reviews = new LinkedList<Review>();
		this.karma = 2.5f; // on attribue un karma moyen à un nouvel utilisateur
	}
	
	/**
	 * Getter of the property <tt>karma</tt>
	 * @return  Returns the karma.
	 * @uml.property  name="karma"
	 */
	public float getKarma() {
		return karma;
	}

		
	/**
	 * Détermine si le pseudo passé en paramètre correspond à celui du Member courant
	 * @param pseudo pseudo à comparer à celui du Member courant
	 * @throws BadEntry
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces.</li>
	 * </ul>   
	 * @return true si le pseudo passé en paramètre correspond à celui du Member courant, false sinon
	 */
	public boolean hasPseudo(String pseudo) throws BadEntry{
		// Si le pseudo n'est pas instancié
		if (pseudo==null)
			throw new BadEntry("Le pseudo doit être instancié.");
		
		//Si le pseudo a moins d'un caractère autre que des espaces
		if (pseudo.replaceAll(" ","").length()<1)
			throw new BadEntry ("Le pseudo doit comporter au moins un caractère autre que des espaces.");
		
		// Si on arrive ici, c'est que le pseudo est correct sur la forme
		// On fait toUpperCase pour s'affranchir de la casse et trim pour les leading/trailing blanks (trim est fait dans le constructeur pour  this.pseudo)
		return (this.pseudo.toUpperCase().equals(pseudo.trim().toUpperCase()));	
	}
	
		
	/** 
	 * Détermine si le mot de passe passé en paramètre correspond à celui du Member courant
	 * @param password le mot de passe à comparer à celui du Member courant
	 * @throws BadEntry <ul>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  </ul>
	 */
	private boolean passwordIs(String password)	throws BadEntry 
	{
		// Si le password n'est pas instancié
		if (password==null)
			throw new BadEntry("Le password doit être instancié.");
		// Si le password possède moins de 4 caractères autres que des leading or trailing blanks
		if (password.trim().length()<4)
			throw new BadEntry ("Le password doit comporter au moins 4 caractères autres que des espaces de début ou fin.");
		
		// Si on arrive ici, c'est que le password est correct sur la forme
		// Pas de toUpperCase : on tient compte de la casse dans le password
		// trim pour supprimer les leading et trailing blanks (déjà fait pour this.password à la construction)
		return (this.password.equals(password.trim()));
	 }

	/**
	 * Vérifie si le couple pseudo/password passé en paramètre correspond à celui du membre courant
	 * @param pseudo le pseudo à comparer avec celui du Member courant
	 * @param password le password à comparer avec celui du Member courant
	 * @return true si les informations correspondent au Member courant, false sinon 
	 * @throws BadEntry dans les cas mentionnés pour les méthodes hasPseudo et passwordIs
	 */
	public boolean authentificationMatches(String pseudo, String password) throws BadEntry{
		return (this.hasPseudo(pseudo) && this.passwordIs(password));
	}
	
	
	/**
	 * Ajoute une Review à la liste de Review du Member courant
	 * @param review la review à ajouter à la liste
	 */
	public void addReview(Review review){
		if (review!=null)
			reviews.add(review);
	}
	
	/**
	 * Renvoie une représentation textuelle du Member
	 * @return la représentation du Member sous forme d'un String
	 */
	public String toString(){
		return this.pseudo + " ("+ profil + ")\n";	
	}
		
	/**
	 * Compare l'objet passé en paramètre avec le Member courant
	 * @param o l'objet à comparer avec le Member courant
	 * @return true si o est égal au Member courant, false sinon
	 */
	public boolean equals(Object o){
		if (o==null || !(o instanceof Member))
			return false;
		else if (((Member)o).pseudo.equals(this.pseudo) && ((Member)o).profil.equals(this.profil) && ((Member)o).password.equals(this.password))
			return true;
		else
			return false;	
	 }

		
	/**
	 * Met à jour le karma du Member.
	 * La méthode consiste à faire la moyenne des moyennes des notes de chaque Review.
	 * Le karma est compris entre 0.0 et 5.0
	 * Il est actualisé à chaque notation d'une Review par un membre
	 */
	public void updateKarma(){
		float somme = 0;
		for (Review review : reviews)
			somme = review.moyenneEvaluationsReview();
		this.karma = somme/(float)(reviews.size());	
	}
}
