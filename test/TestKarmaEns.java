package test;

import avis.SocialNetwork;

/**
 * Reprise du scénario de test de l'enseignant sur le fonctionnement du karma via la méthode reviewOpinionItemFilm
 * @author Yann Andreu et Yannick Omnès
 */
public class TestKarmaEns {
	public static void main (String args[])
	{
		SocialNetwork sn = new SocialNetwork();
		float moyenneAvantOpinion;
		float moyenneApresOpinion;
		try {
			// Ajout de 3 membres, d'un film et de deux Review sur celui-ci
			sn.addMember("Alice", "alice", "");
			sn.addMember("Antoine", "antoine", "");
			sn.addMember("Patrick", "patrick", "");
			
			sn.addItemFilm("Alice", "alice", "film", "film", "film", "film", 123);
			sn.reviewItemFilm("Alice", "alice", "film", 2, "commentaire");
			// On récupère la moyenne avant modification du karma
			moyenneAvantOpinion = sn.reviewItemFilm("Antoine", "antoine", "film", 4, "commentaire");
			
			// En principe, ce qui suit doit modifier le karma
			sn.reviewOpinionItemFilm("Alice", "alice", "Antoine", "film", 1);
			sn.reviewOpinionItemFilm("Patrick", "patrick", "Antoine", "film", 1);
			// On récupère la moyenne après modification du karma
			moyenneApresOpinion = sn.reviewItemFilm("Alice", "alice", "film", 3, "commentaire");
			
			System.out.println("Moyenne avant modification du karma : "+ moyenneAvantOpinion);
			System.out.println("Moyenne après modification du karma : " + moyenneApresOpinion);
		}
		catch (Exception e)
		{
			System.out.println("Une erreur est survenue");
		}
	}
}
