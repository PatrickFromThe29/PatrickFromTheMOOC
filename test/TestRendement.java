package test;

import java.lang.System;

import avis.SocialNetwork;

public class TestRendement 
{
	public static void main (String args[])
	{
		SocialNetwork sn = new SocialNetwork();
		long tempsDepart;
		long tempsFin;
		
		// Ajout d'un premier membre
		try {
			tempsDepart = System.nanoTime();
			sn.addMember("Michel0", "michel", "michel");
			tempsFin = System.nanoTime();
			System.out.println("Temps d'ajout du premier membre avec SN vide : "+ (float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		
		// Ajout de 498 membres
		try {
			for (int i=1;i<499;i++)
				sn.addMember("Michel"+i, "michel", "profil");
		}
		catch (Exception e){
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Temps pour ajouter le 500e membre
		try {
			tempsDepart = System.nanoTime();
			sn.addMember("Michel499", "michel", "michel");
			tempsFin = System.nanoTime();
			System.out.println("Temps d'ajout du 500e membre : "+ (float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Ajout du premier item
		try{
			tempsDepart = System.nanoTime();
			sn.addItemFilm("Michel499", "michel", "Film0", "genre", "realisateur", "scenariste", 180);
			tempsFin = System.nanoTime();
			System.out.println("Temps d'ajout d'un film dans un SN sans item : "+ (float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Ajout de 4998 items
		try{
			for(int i=1;i<4999;i++)
				sn.addItemFilm("Michel499", "michel", "Film"+i, "genre", "realisateur", "scenariste", 180);
		}
		catch (Exception e){
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Temps pour ajouter le 5000e item		
		try{
			tempsDepart = System.nanoTime();
			sn.addItemFilm("Michel499", "michel", "Film4999", "genre", "realisateur", "scenariste", 180);
			tempsFin = System.nanoTime();
			System.out.println("Temps d'ajout du 5000e film : "+ (float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		
		// Ajout d'une première review
		try{
			tempsDepart = System.nanoTime();
			sn.reviewItemFilm("Michel0", "michel", "Film4999", 4.0f, "commentaire");
			tempsFin = System.nanoTime();
			System.out.println("Ajout de la première review pour le 4999e film : "+(float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Ajout de 498 reviews au dernier film de la liste
		try{
			for (int i=1;i<499;i++)
				sn.reviewItemFilm("Michel"+i, "michel", "Film4999", 4.0f, "commentaire");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Ajout de la dernière review de la part du dernier membre
		try{
			tempsDepart = System.nanoTime();
			sn.reviewItemFilm("Michel499", "michel", "Film4999", 4.0f, "commentaire");
			tempsFin = System.nanoTime();
			System.out.println("Ajout de la 500e review pour le 4999e film : "+(float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		// Modification de la dernière review de la part du dernier membre
		try{
			tempsDepart = System.nanoTime();
			sn.reviewItemFilm("Michel499", "michel", "Film4999", 5.0f, "commentaire2");
			tempsFin = System.nanoTime();
			System.out.println("Modification de la 500e review pour le 4999e film : "+(float)(tempsFin-tempsDepart)/1000000+"ms");
		}
		catch (Exception e)
		{
			System.out.println("Erreur : test arrêté.");
			System.exit(-1);
		}
		
		
	}
	
	
	

	
	
}