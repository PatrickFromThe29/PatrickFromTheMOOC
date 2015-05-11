package avis;

import java.util.LinkedList;


import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @author Y. Andreu
 * @author Y. Omnès
 * @date avril 2015
 * @version V0.6
 */


/** 
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche 
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {
	/** 
	 * @uml.property name="members"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="socialNetwork:avis.Member"
	 */
	private LinkedList<Member> members;
	
	/** 
	 * @uml.property name="items"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="socialNetwork:avis.Item"
	 */
	private LinkedList<Item> items;
	

	/**
	 * constructeur de <i>SocialNetwok</i> 
	 * 
	 */
	public SocialNetwork() {
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return members.size();
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		int nbFilms=0;
		for(Item i : items)
			if (i instanceof Film)
				nbFilms++;
		return nbFilms;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		int nbBooks=0;
		for (Item i : items)
			if (i instanceof Book)
				nbBooks++;
		return nbBooks;
	}


	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {
		//===================================== TENTATIVE AJOUT MEMBRE =======================================
		// On tente d'ajouter le membre. Le contructeur de Member lève les BadEntry si besoin
		Member membre = new Member(pseudo,password,profil);
			
		// Si on arrive là, c'est que les paramètres étaient corrects sur la forme.
		// On parcourt la liste de membres.
		// Si le pseudo correspond déjà à un membre, on refuse l'inscription
		for(Member m:members)
			if (m.hasPseudo(pseudo))
				throw new MemberAlreadyExists();
		
		// ====================================== AJOUT D'UN MEMBRE ===============================================
		// Si on arrive ici, c'est que les informations saisies ont été considérées comme acceptables
		// On entre le pseudo en majuscules et sans ses trailing et leading blanks.
		members.add(membre);
	}


	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		// On tente l'ajout d'un film. Le constructeur de Film peut lever BadEntry si nécessaire
		Film film = new Film(titre, genre, realisateur, scenariste, duree);

		// On recherche le membre
		for (Member m: members)
			if(m.authentificationMatches(pseudo, password)) // Si les infos de connexion sont reconnues
			{
				// Si le film existe déjà
				for (Item i : items)
					if (i instanceof Film) // On recherche uniquement parmi les films
					{
						if(((Film)i).titleIs(titre))
							throw new ItemFilmAlreadyExists();
					}
				
				// ====================================== AJOUT D'UN FILM ===============================================	
				// Si on arrive à cette instruction, c'est que toutes les validations ont été effectuées avec succès.
				items.add(film);
				return; 
			}
		throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier.");
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{
		//===================================== ANALYSE DES CAS D'ERREURS =======================================
		// On tente l'ajout d'un livre. Le constructeur de Book peut lever BadEntry si nécessaire
		Book book = new Book(titre, genre, auteur, nbPages);

		// On recherche le membre
		for (Member m: members)
			if(m.authentificationMatches(pseudo, password)) // Si les infos de connexion sont reconnues
			{
				// Si le livre existe déjà
				for (Item i : items)
					if (i instanceof Book) // On recherche uniquement parmi les livres
					{
						if(((Book)i).titleIs(titre))
							throw new ItemBookAlreadyExists();
					}
				
				// ====================================== AJOUT D'UN LIVRE ===============================================	
				// Si on arrive à cette instruction, c'est que toutes les validations ont été effectuées avec succès.
				items.add(book);
				return; 
			}
		throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier.");
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 * 
	 * @return LinkedList <String> : la liste des représentations de tous les items ayant ce nom 
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond) 
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry {
		LinkedList<String> result = new LinkedList<String>();
		Film film = null;
		Book book = null;
		
		//===================================== RECHERCHE DANS LES ITEMS ========================================
		// On regarde le nom de chaque item. S'il correspond à la recherche, on le rajoute à la liste de chaines result
		// Les cas de levée de BadEntry sont gérés par la méthode titleIs de chaque classe Item.
		
		for (Item item : items)
		{
			if ( item instanceof Film)
			{
				film = (Film)item;
				if (film.titleIs(nom))
					result.add(film.toString());
			}
			else if (item instanceof Book)
			{
				book = (Book)item;
				if (book.titleIs(nom))
					result.add(book.toString());
			}
		}
		return result;
	}



	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce film  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		for (Member member : members) // On parcourt la liste des membres
		{
			if (member.authentificationMatches(pseudo, password)) // Vérification de l'identité, lève les BadEntry sur pseudo et password
			{
				for (Item i : items)// On parcourt la liste des items
					if (i instanceof Film) // On ne travaille que sur les Film
						if (((Film)i).titleIs(titre)) // Vérification du titre, lève les BadEntry sur le titre
						{
							i.addOrModifyReview(member, note, commentaire); // Crée/modifie la review, la rattache au Member concerné et lève BadEntry si besoin
							return i.moyenneNoteReview();
						}
				throw new NotItem("Le titre saisi n'est pas celui d'un film connu. Ajoutez un nouveau film au réseau social pour pouvoir l'évaluer.");
			}
		}
		throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier");
		// Pas besoin de return en fin de méthode : l'exception NotMember est forcément levée si on sort du foreach principal
		// NotItem est levée si on a trouvé le membre mais que le film n'existe pas.
		// Si le membre est trouvé, que le film existe et que des paramètres sont incorrects, BadEntry sera levée par une des méthodes appelées et remontée à celle-ci
		// Si le membre est trouvé, que le film existe et que tous les paramètres sont corrects, on arrivera au return prévu et la méthode se terminera correctement en renvoyant la moyenne des notes des reviews sur le film concerné 
	}



	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		for (Member member : members) // On parcourt la liste des membres
		{
			if (member.authentificationMatches(pseudo, password)) // Vérification de l'identité, lève les BadEntry sur pseudo et password
			{
				for (Item i : items)// On parcourt la liste des items
					if (i instanceof Book) // On ne travaille que sur les Book
						if (((Book)i).titleIs(titre)) // Vérification du titre, lève les BadEntry sur le titre
						{
							i.addOrModifyReview(member, note, commentaire); // Crée/modifie la review, la rattache au Member concerné et lève BadEntry si besoin
							return i.moyenneNoteReview();
						}
				throw new NotItem("Le titre saisi n'est pas celui d'un livre connu. Ajoutez un nouveau livre au réseau social pour pouvoir l'évaluer.");
			}
		}
		throw new NotMember("Les informations fournies n'ont pas permis de vous authentifier");
	}
	
	/**
	 * Permet à un membre de noter la Review émise par un autre membre sur un Film
	 * @param pseudo pseudo du membre souhaitant noter une Review
	 * @param password password du membre souhaitant noter une Review
	 * @param pseudoMembreDeposant pseudo du membre dont on veut évaluer une Review
	 * @param titre titre du Film sur lequel la Review à évaluer a été déposée
	 * @param note note à attribuer à la review
	 * @throws BadEntry:
	 * <ul>
	 *  <li>  si le pseudo  de l'évaluateur n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le pseudo  du membre évalué n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password de l'évaluateur n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 * </ul><br>  
	 * @throws NotItem : si le titre du film sur lequel porte l'évaluation de Review ne désigne pas un film connu
	 * @throws NotMember :  si l'un des deux pseudo ne désigne pas un membre existant
	 */
	public void reviewOpinionFilm(String pseudo, String password, String pseudoMembreDeposant, String titre, float note) throws BadEntry, NotItem, NotMember, Exception
	{
		
	}
		
	/**
	 * Permet à un membre de noter la Review émise par un autre membre sur un Book
	 * @param pseudo pseudo du membre souhaitant noter une Review
	 * @param password password du membre souhaitant noter une Review
	 * @param pseudoMembreDeposant pseudo du membre dont on veut évaluer une Review
	 * @param titre titre du Book sur lequel la Review à évaluer a été déposée
	 * @param note note à attribuer à la review
	 * @throws BadEntry:
	 * <ul>
	 *  <li>  si le pseudo  de l'évaluateur n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le pseudo  du membre évalué n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password de l'évaluateur n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 * </ul><br>  
	 * @throws NotItem : si le titre du Book sur lequel porte l'évaluation de Review ne désigne pas un Book connu
	 * @throws NotMember :  si l'un des deux pseudo ne désigne pas un membre existant
	 */
	public void reviewOpinionBook(String pseudo, String password, String pseudoMembreDeposant, String titre, float note) throws BadEntry, NotItem, NotMember, Exception
	{
		
	}


	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		
		String s = "Le SocialNetwork est composé des éléments suivants:\n";
		
		s+= "\nMEMBRES (" + nbMembers() + ") : \n";
		if (!members.isEmpty())
			for(Member m:members)
				s+=m;
		else
			s+= "Aucun membre.\n";
			
		if(!items.isEmpty()) {
			s+="\nLIVRES (" + nbBooks() + ") : \n";
			for(Item i: items)
				if (i instanceof Book)
					s+= (Book)i;
			
			s+= "\nFILMS (" + nbFilms() + ") : \n";
			for(Item i : items)
				if (i instanceof Film)
					s+= (Film)i;
		}
		else
			s+= "\n AUCUN ITEM.\n";
		
		return s;

	}
}
