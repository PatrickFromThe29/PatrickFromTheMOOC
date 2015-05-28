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

public class TestReviewOpinionItemBook {



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
	public static int ReviewOpinionItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){

		try {
			sn.reviewOpinionItemBook (pseudo, pwd, pseudo2, titre, note);
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
	 * Vérifie le bon fonctionnement de reviewOpinionItemBook() avec des paramètres d'entrée corrects (pas d'exception non prévue) 
	 * @param sn le SocialNetwork sur lequel on travaille
	 * @param pseudo le pseudo du Member qui établit note la Review
	 * @param pwd le mot de passe du Member qui note la Review
	 * @param pseudo2 le pseudo du Member dont une Review est évaluée
	 * @param titre le titre de l'Item sur lequel porte la Review évaluée
	 * @param note la note attribuée à la Review
	 * @param idTest l'identifiant unique du test pour référence
	 * @return 1 si erreur détectée, 0 sinon
	 */
	public static int ReviewOpinionItemBookOKTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest){
		
		try{
			sn.reviewOpinionItemBook (pseudo, pwd, pseudo2, titre, note); 
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
	public static int ReviewOpinionItemBookNotItemTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){
		try {
			sn.reviewOpinionItemBook (pseudo, pwd, pseudo2, titre, note);
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
	public static int ReviewOpinionItemBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String pseudo2, String titre, float note , String idTest, String messErreur){
		try {
			sn.reviewOpinionItemBook (pseudo, pwd, pseudo2, titre, note);
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
	 * main de test de la fonction reviewOpinionItemBook()
	 * @param args tableau permettant de remonter au test global le nombre d'échecs de tests et le nombre de tests tentés
	 */
	public static void main(String[] args) {

		//================================= Déclarations =======================================
		SocialNetwork sn = new SocialNetwork();

		int nbTests = 0;
		int nbErreurs = 0;
		
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();
		
		System.out.println("Tests d'ajout d'opinions de Reviews à des Books.");

		// Ajout d'un membre puis d'un Book avec paramètres corrects
		try 
		{
			sn.addMember("Membre 1", "password", "");
			sn.addMember("Membre 2", "password", "");
			sn.addMember("Membre 3", "password", "");
			sn.addItemBook ("Membre 1", "password", "L'art de la guerre", "Traité de Stratégie militaire", "Sun Tzu", 338);
			
			//Ajout de reviews par membre 1 et 2 sur le book
			
			sn.reviewItemBook("Membre 1", "password","L'art de la guerre" ,5.0f,"");
			sn.reviewItemBook("Membre 2", "password","L'art de la guerre" ,0.5f,"");
		
		}
		catch(Exception e)
		{
			System.out.println("Erreur à l'ajout d'un membre ou d'un Book avec paramètres corrects.");
			System.exit(1);
		}
		
		nbMembers+=3;
		nbBooks += 1;
		
		
		//====================================== Tests =========================================
		
		
		// <=> fiche numéro 5
		
		// Exception BadEntry : tentatives BadEntryment incorrectes d'ajout de notes de reviews de book
		
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, null, "password", "Membre 2", "L'art de la guerre", 2.0f, "13.1", "L'ajout d'une note sur une review avec pseudo non instancié est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "   ", "password", "Membre 2", "L'art de la guerre", 2.0f, "13.2", "L'ajout d'une note sur une review avec pseudo ne contenant que des espaces est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", null, "Membre 2", "L'art de la guerre", 2.0f, "13.3", "L'ajout d'une note sur une review avec password non instancié est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "    aze   ", "Membre 2", "L'art de la guerre", 2.0f, "13.4", "L'ajout d'une note sur une review avec password contenant moins de 4 caractères autre que des leadings/trailings blanks est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "password", null, "L'art de la guerre", 2.0f, "13.5", "L'ajout d'une note sur une review avec pseudo du membre ayant déposé l'avis non instancié est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "password", "Membre 2", null, 2.0f, "13.6", "L'ajout d'une note sur une review avec titre non instancié est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "password", "Membre 2", "               ", 2.0f, "13.7", "L'ajout d'une note sur une review avec titre ne contenant que des espaces est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "password", "Membre 2", "L'art de la guerre", 6.0f, "13.8", "L'ajout d'une note supérieure à 5 sur une review est accepté.");
		nbTests++;;
		nbErreurs += ReviewOpinionItemBookBadEntryTest(sn, "Membre 1", "password", "Membre 2", "L'art de la guerre", -2.0f, "13.9", "L'ajout d'une note négative sur une review est accepté.");

		
		// <=> fiche numéro 6

		// Ajout de 3 reviews avec entrées "correctes". 
		// On vérifie que l'on peut modifier un avis sur un Book ou en ajouter un.
		nbTests++;
		nbErreurs += ReviewOpinionItemBookOKTest (sn, "Membre 1", "password", "Membre 2", "L'art de la guerre",2.0f, "14.1a");
		nbTests++;
		nbErreurs += ReviewOpinionItemBookOKTest (sn, "Membre 2", "password", "Membre 1",  "L'art de la guerre", 3.0f, "14.1b");
		nbTests++;
		nbErreurs += ReviewOpinionItemBookOKTest (sn, "Membre 1", "password", "Membre 2", "L'art de la guerre",4.0f, "14.1a");

		//Exception NotMember : comportement en cas d'erreur d'authentification
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotMemberTest(sn, "Membre", "password", "Membre 2", "L'art de la guerre",2.0f, "14.2a", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotMemberTest(sn, "Membre 1", "password", "Membre", "L'art de la guerre",2.0f, "14.2b", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotMemberTest(sn, "Membre 1", "passwsdfsdford", "Membre 2", "L'art de la guerre",2.0f, "14.3", "Mauvais password accepté.");
		
		// Exception NotItem : l'avis n'existe pas
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotItemTest(sn, "Membre 1", "password", "Membre 3", "L'art de la guerre",2.0f, "14.4", "Ajout d'une opinion sur une review inexistante acceptée.");

		
		
		// Exception NotItem : le Book n'existe pas
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotItemTest(sn, "Membre 1", "password", "Membre 2", "L'art desdf la guerre",2.0f, "14.5", "Ajout d'une opinion accepté avec un titre incorrect.");
		
		//Exception NotMember : Un membre tente de s'évaluer lui même
		nbTests++;
		nbErreurs += ReviewOpinionItemBookNotMemberTest(sn, "Membre 1", "password", "Membre 1", "L'art de la guerre",2.0f, "14.6", "Un membre a pu noter sa propre Review.");

		nbTests++;
		if (nbFilms != sn.nbFilms()){
			nbErreurs++;
			System.out.println("Le nombre de films a changé lors de l'ajout des review");
		}
		
		nbTests++;
		if (nbBooks != sn.nbBooks()){
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

		// bilan du test de ReviewOpinionItemBookBook
		System.out.println("TestReviewOpinionItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");


     
		// ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
    }  // fin du main
	}
	
