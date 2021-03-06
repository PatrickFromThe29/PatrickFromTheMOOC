package test;

/**
 * Classe permettant d'appeler l'ensemble des tests successifs et d'en récupérer le bilan pour affichage
 * @author Yannick Omnès et Yann Andreu
 */

public class TestSocialNetwork {

	/**
	 * main de test global. Lance successivement tous les tests de l'API SocialNetwork
	 */
	public static void main(String[] args) {

	  String [] resultats = new String[] {"0", "0"};
	      
      TestsInitialisation.main(resultats);
      
      System.out.println("\n\n ***************************************\n");

      TestsAddMember.main(resultats);
      
      System.out.println("\n\n ***************************************\n");

      TestAddItemFilm.main(resultats);
      
      System.out.println("\n\n ***************************************\n");

      TestReviewItemFilm.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      TestAddItemBook.main(resultats);
      
      System.out.println("\n\n ***************************************\n");

      TestReviewItemBook.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      TestConsultItem.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      TestReviewOpinionItemFilm.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      TestReviewOpinionItemBook.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      System.out.println("Exécution du test de rendement. Veuillez patienter.");
      TestRendement.main(resultats);
      
      System.out.println("\n\n ***************************************\n");
      
      // .... d'autres appels à des tests
      
      System.out.println("Bilan des Tests :   " + resultats[1] + 
                         " erreur(s) / " +  resultats[0] + " tests effectués");
      
	}

}
