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

public class TestReviewOpinionItemFilm {



	/**
	 * Vérifie que l'exception BadEntry est bien levée en cas de tentative d'ajout d'une ReviewOpinion sur une Review avec des paramètres incorrects 
	 * @param sn le SocialNetwork sur lequel on travaille
	 * @param pseudo le pseudo du Member qui établit note la Review
	 * @param pwd le mot de passe du Member qui note la Review
	 * @param pseudo2 le pseudo du Member dont une Review est évaluée
	 * @param titre le titre de l'Item sur lequel porte la Review évaluée
	 * @param note la note attribuée à la Review
	 * @param idTest l'idTest affecté
	 * @param messErreur le message d'erreur associé, affiché en cas d'échec du test
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewOpinionItemFilmBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){

		try {
			sn.reviewOpinionItemFilm (pseudo, pwd, pseudo2, titre, note);
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
	
	/**
	 * Vérifie le bon fonctionnement de reviewOpinionItemFilm() avec des paramètres d'entrée corrects (pas d'exception non prévue) 
	 * @param sn le SocialNetwork sur lequel on travaille
	 * @param pseudo le pseudo du Member qui établit note la Review
	 * @param pwd le mot de passe du Member qui note la Review
	 * @param pseudo2 le pseudo du Member dont une Review est évaluée
	 * @param titre le titre de l'Item sur lequel porte la Review évaluée
	 * @param note la note attribuée à la Review
	 * @param idTest l'identifiant unique du test pour référence
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewOpinionItemFilmOKTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest){

		try{
			sn.reviewOpinionItemFilm (pseudo, pwd, pseudo2, titre, note); 
			return 0;
			}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * Vérifie la levée de l'exception NotItem lorsque celle-ci est attendue
	 * @param sn le SocialNetwork sur lequel on travaille
	 * @param pseudo le pseudo du Member qui établit note la Review
	 * @param pwd le mot de passe du Member qui note la Review
	 * @param pseudo2 le pseudo du Member dont une Review est évaluée
	 * @param titre le titre de l'Item sur lequel porte la Review évaluée
	 * @param note la note attribuée à la Review
	 * @param idTest l'identifiant unique du test pour référence
	 * @param messErreur message à afficher en cas d'échec du test
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewOpinionItemFilmNotItemTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){
		try {
			sn.reviewOpinionItemFilm (pseudo, pwd, pseudo2, titre, note);
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
	
	/**
	 * Vérifie la levée de l'exception NotMember lorsque celle-ci est attendue
	 * @param sn le SocialNetwork sur lequel on travaille
	 * @param pseudo le pseudo du Member qui établit note la Review
	 * @param pwd le mot de passe du Member qui note la Review
	 * @param pseudo2 le pseudo du Member dont une Review est évaluée
	 * @param titre le titre de l'Item sur lequel porte la Review évaluée
	 * @param note la note attribuée à la Review
	 * @param idTest l'identifiant unique du test pour référence
	 * @param messErreur message à afficher en cas d'échec du test
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewOpinionItemFilmNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){
		try {
			sn.reviewOpinionItemFilm (pseudo, pwd, pseudo2, titre, note);
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



	/**
	 * main de test de la fonction reviewOpinionItemFilm()
	 * @param args tableau permettant de remonter au test global le nombre d'échecs de tests et le nombre de tests tentés
	 */
	public static void main(String[] args) {

		//================================= Déclarations =======================================
		SocialNetwork sn = new SocialNetwork();

		int nbTests = 0;
		int nbErreurs = 0;
		
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();
		int nbBooks = sn.nbBooks();
		
		System.out.println("Tests d'ajout d'opinions de Reviews à des Films.");

		// Ajout d'un membre puis d'un Film avec paramètres corrects
		try 
		{
			sn.addMember("Membre 1", "password", "");
			sn.addMember("Membre 2", "password", "");
			sn.addMember("Membre 3", "password", "");
			sn.addItemFilm("Membre 1", "password", "La grande vadrouille", "Comédie","Gérard Oury","Gérard Oury",130);
			
			//Ajout de reviews par membre 1 et 2 sur le Film
			
			sn.reviewItemFilm("Membre 1", "password","La grande vadrouille" ,5.0f,"");
			sn.reviewItemFilm("Membre 2", "password","La grande vadrouille" ,0.5f,"");
		
		}
		catch(Exception e)
		{
			System.out.println("Erreur à l'ajout d'un membre ou d'un Film avec paramètres corrects.");
			System.exit(1);
		}
		
		nbMembers+=3;
		nbFilms += 1;
		
		
		//====================================== Tests =========================================
		
		
		// <=> fiche numéro 5
		
		// Exception BadEntry : tentatives BadEntryment incorrectes d'ajout de notes de reviews de Film
		
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, null, "password", "Membre 2", "La grande vadrouille", 2.0f, "7.1", "L'ajout d'une note sur une review avec pseudo non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "   ", "password", "Membre 2", "La grande vadrouille", 2.0f, "7.2", "L'ajout d'une note sur une review avec pseudo ne contenant que des espaces est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", null, "Membre 2", "La grande vadrouille", 2.0f, "7.3", "L'ajout d'une note sur une review avec password non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "    aze   ", "Membre 2", "La grande vadrouille", 2.0f, "7.4", "L'ajout d'une note sur une review avec password contenant moins de 4 caractères autre que des leadings/trailings blanks est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "password", null, "La grande vadrouille", 2.0f, "7.5", "L'ajout d'une note sur une review avec pseudo du membre ayant déposé l'avis non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "password", "Membre 2", null, 2.0f, "7.6", "L'ajout d'une note sur une review avec titre non instancié est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "password", "Membre 2", "               ", 2.0f, "7.7", "L'ajout d'une note sur une review avec titre ne contenant que des espaces est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "password", "Membre 2", "La grande vadrouille", 6.0f, "7.8", "L'ajout d'une note supérieure à 5 sur une review est accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmBadEntryTest(sn, "Membre 1", "password", "Membre 2", "La grande vadrouille", -2.0f, "7.9", "L'ajout d'une note négative sur une review est accepté.");

		
		// <=> fiche numéro 6

		// Ajout de 3 reviews avec entrées "correctes". 
		// On vérifie que l'on peut modifier un avis sur un Film ou en ajouter un.
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmOKTest (sn, "Membre 1", "password", "Membre 2", "La grande vadrouille",2.0f, "8.1a");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmOKTest (sn, "Membre 2", "password", "Membre 1",  "La grande vadrouille", 3.0f, "8.1b");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmOKTest (sn, "Membre 1", "password", "Membre 2", "La grande vadrouille",4.0f, "8.1a");

		//Exception NotMember : comportement en cas d'erreur d'authentification
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotMemberTest(sn, "Membre", "password", "Membre 2", "La grande vadrouille",2.0f, "8.3a", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotMemberTest(sn, "Membre 1", "password", "Membre", "La grande vadrouille",2.0f, "8.3b", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotMemberTest(sn, "Membre 1", "passwsdfsdford", "Membre 2", "La grande vadrouille",2.0f, "8.4", "Mauvais password accepté.");
		
		// Exception NotItem : l'avis n'existe pas
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotItemTest(sn, "Membre 1", "password", "Membre 3", "La grande vadrouille",2.0f, "8.5", "Ajout d'une opinion sur une review inexistante acceptée.");

		
		
		// Exception NotItem : le Film n'existe pas
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotItemTest(sn, "Membre 1", "password", "Membre 2", "L'art desdf la guerre",2.0f, "8.6", "Ajout d'une opinion accepté avec un titre incorrect.");
		
		//Exception NotMember : Un membre tente de s'évaluer lui même
		nbTests++;
		nbErreurs += ReviewOpinionItemFilmNotMemberTest(sn, "Membre 1", "password", "Membre 1", "La grande vadrouille",2.0f, "8.7", "Un membre a pu noter sa propre Review.");

		nbTests++;
		if (nbBooks != sn.nbBooks()){
			nbErreurs++;
			System.out.println("Le nombre de films a changé lors de l'ajout des review");
		}
		
		nbTests++;
		if (nbFilms != sn.nbFilms()){
			nbErreurs++;
			System.out.println("Le nombre de livres a changé lors de l'ajout des review");
		}
		
		nbTests++;
		if (nbMembers != sn.nbMembers()){
			nbErreurs++;
			System.out.println("Le nombre de membres a changé lors de l'ajout des review");
		}
		
		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// bilan du test de ReviewOpinionItemFilmFilm
		System.out.println("TestReviewOpinionItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");


     
		// ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
    }  // fin du main
	}
	
