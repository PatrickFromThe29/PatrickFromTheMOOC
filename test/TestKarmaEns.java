package test;

import avis.SocialNetwork;

public class TestKarmaEns {
	public static void main (String args[])
	{
		SocialNetwork sn = new SocialNetwork();
		float moyenneAvantOpinion;
		float moyenneApresOpinion;
		try {
			sn.addMember("Alice", "alice", "");
			sn.addMember("Antoine", "antoine", "");
			sn.addMember("Patrick", "patrick", "");
			
			sn.addItemFilm("Alice", "alice", "film", "film", "film", "film", 123);
			sn.reviewItemFilm("Alice", "alice", "film", 2, "commentaire");
			moyenneAvantOpinion = sn.reviewItemFilm("Antoine", "antoine", "film", 4, "commentaire");
			
			sn.reviewOpinionItemFilm("Alice", "alice", "Antoine", "film", 1);
			sn.reviewOpinionItemFilm("Patrick", "patrick", "Antoine", "film", 1);
			
			moyenneApresOpinion = sn.reviewItemFilm("Alice", "alice", "film", 2, "commentaire");
			
			System.out.println("Moyenne avant modification du karma : "+ moyenneAvantOpinion);
			System.out.println("Moyenne après modification du karma : " + moyenneApresOpinion);
		}
		catch (Exception e)
		{
			System.out.println("Une erreur est survenue");
		}
	}
}
