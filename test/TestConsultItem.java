package test;

import java.util.LinkedList;

import avis.SocialNetwork;
import exception.BadEntry;

public class TestConsultItem {

	public static int consultItemBadEntryTest (SocialNetwork sn, String nom, String idTest, String messErreur){

		try {
			sn.consultItems (nom);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) { 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int consultItemOkTest(SocialNetwork sn, String nom, int resultatAttendu, String idTest){
		LinkedList<String> result;
		try{
			result = sn.consultItems(nom);
			if (result != null && result.size() != resultatAttendu) // Priorité opératoire, si result = null result.size() ne sera pas testé
			{
				System.out.println("Test " + idTest + " :  le nombre d'items retournée n'est pas correct n'a pas été correctement incrémenté");
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
SocialNetwork sn = new SocialNetwork();
		
		int nbTests = 0;
		int nbErreurs = 0;
		int nbBooks = sn.nbBooks(), nbFilms = sn.nbFilms(), nbMembers = sn.nbMembers();
		// Ajout d'un membre puis de films et d'un book avec paramètres corrects
		try 
		{
			sn.addMember("Membre 1", "password", "");
			nbMembers++;
			sn.addItemFilm("Membre 1", "password", "La grande vadrouille", "Comédie","Gérard Oury","Gérard Oury",130);
			sn.addItemFilm("Membre 1", "password", "50 Nuances de Grey", "SM","Sam Taylor-Wood","Kelly Marcel",125);
			nbFilms+=2;
			sn.addItemBook("Membre 1", "password", "50 Nuances de Grey", "SM","EL James",560);
			nbBooks++;
		}
		catch(Exception e)
		{
			System.out.println("Erreur à l'ajout d'un membre, d'un film ou d'un book avec paramètres corrects.");
			System.exit(1);
		}
		
		
		System.out.println("Tests de consultations d'Items au réseau social.");

		// Tests de BadEntry
		nbTests++;
		nbErreurs+=consultItemBadEntryTest(sn, null, "7.1", "Consultation d'un item avec nom d'item non instancié accepté");
		nbTests++;
		nbErreurs+=consultItemBadEntryTest(sn, "           ", "7.2", "Consultation d'un item avec uniquement des espaces accepté ");
		
		// Test avec des paramètres respectant les règles de syntaxe fixées
		nbTests++;
		nbErreurs += consultItemOkTest(sn, "50 Nuances de Grey", 2, "8.1");
		nbTests++;
		nbErreurs += consultItemOkTest(sn,"La grande vadrouille", 1, "8.2");
		nbTests++;
		nbErreurs += consultItemOkTest(sn,"Y-a-t-il un pilote dans l'avion?", 0, "8.3"); // Le fait que l'item ne soit pas connu est un cas normal : l'utilisateur n'est pas censé savoir a priori si l'item recherché existe.
		
		nbTests++;
		nbErreurs += (sn.nbMembers() != nbMembers ) ? 1 : 0; //équivalent à if (sn.nbMembers != nbMembers) { nbErreurs ++}
		nbTests++;
		nbErreurs += (sn.nbFilms() != nbFilms ) ? 1 : 0;
		nbTests++;
		nbErreurs += (sn.nbBooks() != nbBooks ) ? 1 : 0;
		
		// Bilan du test de ConsultItem
		System.out.println("TestConsultItem :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
		
		if ((args != null) && (args.length == 2)) {        
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);       
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
         }
			

	}

}
