package test;

import java.lang.System;

import avis.SocialNetwork;

public class TestComplexite {

	
	public static void main(String args[])
	{
		
		long tempsDepart = 0;
		long tempsArrivee = 0;
		long temps2500membres = 0;
		long temps2500films = 0;
		long temps2500books = 0;
		SocialNetwork sn = new SocialNetwork();
		SocialNetwork sn2 = new SocialNetwork();
		final String nomMembre = "Patrick";
		final String nomFilm = "monFilm";
		final String nomBook = "monBook";
		
		
		//============================ Mesure du temps d'ajout de 2500 membres =========================
		
		System.out.print ("Ajout de 2500 membres : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				
				sn.addMember(nomMembre+i, "Patrick", "Patrick");
			}
			catch (Exception e)
			{
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		temps2500membres = tempsArrivee-tempsDepart;
		System.out.println(temps2500membres+"ms");
		
		
		//============================ Mesure du temps d'ajout de 2500 films =========================
		System.out.print ("Ajout de 2500 Films : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				sn.addItemFilm("Patrick0", "Patrick", nomFilm+i, "Genre", "réalisateur", "scénariste", 123);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		temps2500films = tempsArrivee-tempsDepart;
		System.out.println(temps2500films+"ms");
		
		
		//============================ Mesure du temps d'ajout de 2500 books =========================
		System.out.print ("Ajout de 2500 Books : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<2500;i++)
		{
			try{
				sn.addItemBook("Patrick0", "Patrick",nomBook+i, "Genre", "Auteur", 560);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		temps2500books = tempsArrivee-tempsDepart;
		System.out.println(temps2500books+"ms");
		
		
		
		
		//============================ Mesure du temps d'ajout de 5000 membres =========================
		System.out.print ("Ajout de 5000 membres : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addMember(nomMembre+i, "Patrick", "Patrick");
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms (x"+(float)(tempsArrivee-tempsDepart)/(float)temps2500membres+")");
		
		
		//============================ Mesure du temps d'ajout de 5000 films =========================
		System.out.print ("Ajout de 5000 Films : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addItemFilm("Patrick0", "Patrick", nomFilm+i, "Genre", "réalisateur", "scénariste", 123);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms (x"+(float)(tempsArrivee-tempsDepart)/(float)temps2500films+")");
		
		
		
		//============================ Mesure du temps d'ajout de 5000 books =========================
		System.out.print ("Ajout de 5000 Books : ");
		tempsDepart = System.currentTimeMillis();
		for (int i=0;i<5000;i++)
		{
			try{
				sn2.addItemBook("Patrick0", "Patrick", nomBook+i, "Genre", "Auteur", 560);
			}
			catch (Exception e){
				System.out.println("Une exception inattendue s'est produite.");
				System.exit(1);
			}
		}
		tempsArrivee = System.currentTimeMillis();
		System.out.println((tempsArrivee-tempsDepart)+"ms (x"+(float)(tempsArrivee-tempsDepart)/(float)temps2500books+")");
	}

}

