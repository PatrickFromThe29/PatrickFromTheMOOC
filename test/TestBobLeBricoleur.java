package test;

import avis.SocialNetwork;

/**
 * Ce test 	a pour unique objectif de vérifier que consultItems ne renvoie pas d'erreurs.
 * @author Yann Andreu
 *
 */
public class TestBobLeBricoleur {
	
	public static void main( String args[])
	{
		SocialNetwork sn = new SocialNetwork();
		try
		{
			sn.addMember("patrick", "patrick", "patrick");
			sn.addItemBook("patrick", "patrick", "patrick", "patrick", "patrick", 56);
			sn.consultItems("patrick");
		}
		catch (Exception e){
			System.out.println("Erreur");
		}
	}
}
