package test;


import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.NotMember;

/** 
 * @author Yannick Omnès
 * @date mars 2015
 * @version V1.0
 */

public class TestAddItemBook {


	/**
	 * vérifie que l'ajout d'un Book est refusé en cas de mauvaise saisie des paramètres (levée de l'exception BadEntry)
	 * Si c'est bien le cas, ne fait rien
	 * Sinon, affiche le message d'erreur passé en paramètre
	 * @param sn SocialNetwork avec lequel on travaille
	 * @param pseudo pseudo du membre 
	 * @param pwd password du membre
	 * @param titre titre du Book
	 * @param genre genre du Book
	 * @param realisateur réalisateur du Book
	 * @param scenariste scénariste du Book
	 * @param duree durée du Book
	 * @param idTest id du test
	 * @param messErreur message d'erreur à  renvoyer si l'ajout est autorisé
	 * @return 0 si le test s'est bien passé, 1 sinon
	 */
	public static int addItemBookBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){

		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de Books a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addBookOKTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages, String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " :  le nombre de Books n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages,  String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de Books a été modifié");
				return 1;
			}
			else
				return 0;
		}
		
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addBookNotMemberTest (SocialNetwork sn, String pseudo, String pwd, String titre, String genre, String auteur, int nbPages,  String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook (pseudo, pwd, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de Books a été modifié");
				return 1;
			}
			else
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
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();

		
		

		int nbBooks = sn.nbBooks() ;

		
		System.out.println("Tests d'ajout de Books au réseau social.");


		try {
			
			sn.addMember("Membre 1", "password", "");
			
		}
		catch(Exception e){
			System.out.println("Impossible d'ajouter un membre.");
			System.exit(1);

		} 
		nbMembers++;

		//===================================== Tests ==========================================

		// <=> fiche numéro 3

		// Exception BadEntry : tentative d'ajout de Books avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, null, "password", "a","a","a",1, "3.1", "L'ajout d'un Book avec pseudo non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "     ", "password", "a","a","a",1, "3.2", "L'ajout d'un Book avec un pseudo contenant uniquement des espaces est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", null, "a", "a","a",1,"3.3", "L'ajout d'un Book avec password non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "   qwd ", "a", "a","a",1,"3.4", "L'ajout d'un Book avec un password ne contenant pas plus de 4 caractères autres que leading/trailing blanks est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "password", null,"a","a",1, "3.5", "L'ajout d'un Book avec un titre non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "password", "   ","a","a",1, "3.6", "L'ajout d'un Book avec titre ne contenant que des espaces est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "password", "b",null,"a",1, "3.7", "L'ajout d'un Book avec un genre non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "password", "b","a",null,1, "3.8", "L'ajout d'un Book avec un auteur non instancié est accepté.");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest ( sn, "Membre 1", "password", "b","a","a",-5, "3.10", "L'ajout d'un Book avec un nombre de pages négatif est accepté.");

		
		nbTests++;
		if(nbBooks != sn.nbBooks())
			
			{
			System.out.println("Le nombre de Books a changé après tentative d'ajout avec Bad Entry");
			nbErreurs++;
			}

		// <=> fiche numéro 4

		// Ajout de 3 Books avec entrées "correctes"
		nbTests++;
		nbBooks++;
		nbErreurs += addBookOKTest (sn, "Membre 1", "password", "L'art de la guerre", "Traité de Stratégie militaire", "Sun Tzu", 338, "4.1a");
		nbTests++;
		nbBooks++;
		nbErreurs += addBookOKTest (sn, "Membre 1", "password", "Les traites négrières","Essai philosophique", "Olivier Pétré-grenouilleau", 480, "4.1b");
		nbTests++;
		nbBooks++;
		nbErreurs += addBookOKTest (sn, "Membre 1", "password", "Essai sur les données imédiates de la conscience", "Texte Philosophique", "Henri Bergson", 120, "4.1c");
		
		
		nbTests++;
		if(nbBooks != sn.nbBooks())
			{
			System.out.println("Le nombre de Books ne s'est pas correctement incrémenté après ajout de Books");
			nbErreurs++;
			}

		// Exception NotMember : Tentative d'ajout de Books avec erreur d'authentification
		nbTests++;
		nbErreurs += addBookNotMemberTest(sn, "M", "password", "L'art de la guerreds","Traité de Stratégie militaire","Sun Tzu",338, "4.2", "Membre inexistant accepté.");
		nbTests++;
		nbErreurs += addBookNotMemberTest(sn, "Membre 1", " sdmmf ", "L'art de ma guerresdf","Traité de Stratégie militaire","Sun Tzu",338, "4.3", "Mauvais password accepté.");
		
		nbTests++;
		if(nbBooks != sn.nbBooks())
		{
			System.out.println("Le nombre de Books a changé après ajout incorrect de Book");
			nbErreurs++;
		}

		
		// Exception ItemBookAlreadyExists : tentative d'ajout de Book déjà  existant
		nbTests++;
		nbErreurs += addBookAlreadyExistsTest(sn, "Membre 1", "password", "L'art de la guerre","Traité de Stratégie militaire","Sun Tzu",130, "4.4a", "Ajout d'un Book identique au premier ajouté accepté.");
		nbTests++;
		nbErreurs += addBookAlreadyExistsTest(sn, "Membre 1", "password", "Les traites négrières","Essai philosophique", "Olivier Pétré-grenouilleau", 480,  "4.4b", "L'ajout d'un Book identique au dernier Book ajouté est accepté.");
		nbTests++;
		nbErreurs += addBookAlreadyExistsTest(sn, "Membre 1", "password", "Essai sur les données imédiates de la conscience", "Texte Philosophique", "Henri Bergson", 120,  "4.5", "L'ajout d'un Book déjà  existant (avec casse différente) est accepté.");
		nbTests++;
		nbErreurs += addBookAlreadyExistsTest(sn, "Membre 1", "password", "           Essai sur les données imédiates de la conscience            ", "Texte philosophique", "Henry Bergson", 120,  "4.6", "L'ajout d'un Book déjà  existant (avec leading et trailing blanks) est accepté.");		
		
		nbTests++;
		if(nbBooks != sn.nbBooks())
		{
			nbErreurs++;
			System.out.println("Le nombre de Books a changé après la tentative d'ajout de Book déjà  existant");
		}
		
		nbTests++;
		if (nbFilms != sn.nbFilms()){
			nbErreurs++;
			System.out.println("Le nombre de films a changé lors de l'ajout des livres");
		}
		
		nbTests++;
		if (nbMembers != sn.nbMembers()){
			nbErreurs++;
			System.out.println("Le nombre de membres a changé lors de l'ajout des livres");
		}

		// Ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);

		// Bilan du test de addItemBook
		System.out.println("TestsAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
		
		if ((args != null) && (args.length == 2)) {        
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);       
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
         }

	}
}
