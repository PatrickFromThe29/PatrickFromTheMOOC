package avis;

import java.util.LinkedList;

import exception.BadEntry;


public class Member{

	/**
	 * @uml.property  name="pseudo" readOnly="true"
	 */
	private String pseudo;

	/**
	 * @uml.property  name="password"
	 */
	private String password;
	
	/**
	 * @uml.property  name="profil"
	 */
	private String profil;

	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="member:avis.Review"
	 */
	private LinkedList<Review> reviews;
	
	/**
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
	}

		
	/**
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
	 */
	public boolean passwordIs(String password) throws BadEntry{
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
	 */
	public boolean authentificationMatches(String pseudo, String password) throws BadEntry{
		return (this.hasPseudo(pseudo) && this.passwordIs(password));
	}

	
	/**
	 */
	public String toString(){
		String s = this.pseudo + " : " + this.profil+"\n";
		return s;	
	}
		
	public boolean equals(Object o){
		if (o==null || !(o instanceof Member))
			return false;
		else if (((Member)o).pseudo.equals(this.pseudo) && ((Member)o).profil.equals(this.profil) && ((Member)o).password.equals(this.password))
			return true;
		else
			return false;	
	 }

}
