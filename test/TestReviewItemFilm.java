package test;



import avis.SocialNetwork;

import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author Yannick Omnès et Yann Andreu
 * @date mars 2015
 * @version V1.0
 */

public class TestReviewItemFilm {



	/**
	 * Vérifie que l'ajout d'une review est refusé (levée de l'exception BadEntry) 
	 * Si c'est bien le cas, ne fait rien
	 * Sinon, affiche le message d'erreur passé en paramètre
	 * @param sn Le Social Network
	 * @param pseudo le pseudo du membre
	 * @param pwd le password du membre
	 * @param titre le titre du film
	 * @param note la note attribuée
	 * @param commentaire le commentaire rédigé
	 * @param idTest l'idTest affecté
	 * @param messErreur le message d'erreur associé
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, String messErreur){

		try {
			sn.reviewItemFilm (pseudo, pwd, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			return 0;
		}
		catch (Exception e) 
		{
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addReviewOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, float moyenneAttendue){
		
		
		try{
			if (sn.reviewItemFilm (pseudo, pwd, titre, note, commentaire) == moyenneAttendue)
				return 0;
			else {
				
				System.out.println("Moyenne incorrecte (différence entre moyenne attendue et moyenne réelle).");
				return 1;
			}
			}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addReviewNotItem (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire, String idTest, String messErreur){
		try {
			sn.reviewItemFilm (pseudo, pwd, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotItem e) {
			return 0;
		}
		
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addReviewNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, float note, String commentaire,  String idTest, String messErreur){
		try {
			sn.reviewItemFilm (pseudo, pwd, titre, 5, "");
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}




	public static void main(String[] args) {

		//================================= Déclarations =======================================
		SocialNetwork sn = new SocialNetwork();

		int nbTests = 0;
		int nbErreurs = 0;
		
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();
		
		System.out.println("Tests d'ajout de reviews à des films.");

		// Ajout d'un membre puis d'un film avec paramètres corrects
		try 
		{
			sn.addMember("Membre 1", "password", "");
			sn.addMember("Membre 2", "password", "");
			sn.addItemFilm("Membre 1", "password", "La grande vadrouille", "Comédie","Gérard Oury","Gérard Oury",130);
			sn.addItemFilm("Membre 1", "password", "Oblivion", "SF","Joseph Kosinski","Joseph Kosinski et ses amis",124);
			sn.addItemFilm("Membre 1", "password", "50 Nuances de Grey", "SM","Sam Taylor-Wood","Kelly Marcel",125);
		}
		catch(Exception e)
		{
			System.out.println("Erreur à l'ajout d'un membre ou d'un film avec paramètres corrects.");
			System.exit(1);
		}
		
		//====================================== Tests =========================================
		
		
		// <=> fiche numéro 5
		
		// Exception BadEntry : tentatives BadEntryment incorrectes d'ajout de reviews
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, null, "password", "La grande vadrouille",0.f,"a", "5.1", "L'ajout d'une review avec pseudo non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "   ", "qsdfgh", "La grande vadrouille",0.f,"a", "5.2", "L'ajout d'une review avec un pseudo contenant uniquement des espaces est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", null, "La grande vadrouille",0.f,"a","5.3", "L'ajout d'une review avec un password non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "      fgh  ", "La grande vadrouille",0.f,"a","5.4", "L'ajout d'une review avec un password ne contenant pas plus de 4 caractères autres que leading/trailing blanks est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "password", null,0.f,"a", "5.5", "L'ajout d'une review avec un titre non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "password", "         ",0.f,"a", "5.6", "L'ajout d'une review avec un titre ne contenant que des espaces est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "password", "La grande vadrouille",-1.f,"a", "5.7", "L'ajout d'une review avec une note inferieure à 0 est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "password", "La grande vadrouille",6.f,"a", "5.8", "L'ajout d'une review avec une note supérieure à 5 est accepté.");
		nbTests++;
		nbErreurs += ReviewItemFilmBadEntryTest ( sn, "Membre 1", "password", "La grande vadrouille",0.f,null, "5.9", "L'ajout d'une review avec un commentaire non instancié est accepté.");

		
		// <=> fiche numéro 6

		// Ajout de 3 reviews avec entrées "correctes". Le test prend en compte la moyenne retournée par reviewItemFilm.
		// On vérifie que l'on peut modifier un avis sur un film ou en ajouter un.
		nbTests++;
		nbErreurs += addReviewOKTest (sn, "Membre 1", "password", "La grande vadrouille",2.0f, "review 1", "6.1a", 2.0f);
		nbTests++;
		nbErreurs += addReviewOKTest (sn, "Membre 2", "password", "La grande vadrouille",3.0f, "review 1", "6.1b", 2.5f);
		nbTests++;
		nbErreurs += addReviewOKTest (sn, "Membre 1", "password","La grande vadrouille",4.0f, "review 1", "6.2", 3.5f);

		//Exception NotMember : comportement en cas d'erreur d'authentification
		nbTests++;
		nbErreurs += addReviewNotMemberTest(sn, "Memb", "password", "La grande vadrouille",2.0f, "review 1", "6.3", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += addReviewNotMemberTest(sn, "Membre 1", " ghsdf ", "La grande vadrouille",2.0f, "review 1", "6.4", "Mauvais password accepté.");
		
		// Exception NotItem : le film n'existe pas
		nbTests++;
		nbErreurs += addReviewNotItem(sn, "Membre 1", "password", "La gransdfde vadrouille",2.5f, "", "6.5", "Ajout d'une review accepté avec un titre incorrect.");

		nbTests++;
		if (nbFilms != sn.nbFilms()){
			nbErreurs++;
			System.out.println("Le nombre de film a chang� lors de l'ajout des review");
		}
		
		nbTests++;
		if (nbBooks != sn.nbBooks()){
			nbErreurs++;
			System.out.println("Le nombre de livres a chang� lors de l'ajout des review");
		}
		
		nbTests++;
		if (nbMembers != sn.nbMembers()){
			nbErreurs++;
			System.out.println("Le nombre de membres a chang� lors de l'ajout des review");
		}
		
		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de addReviewFilm
		System.out.println("TestsReviewItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");


     
     // ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
    }  // fin du main
	}
	
