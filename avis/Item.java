package avis;


import java.util.LinkedList;

import exception.BadEntry;

/**
 * La classe abstraite Item est la superclasse de tous les objets susceptibles d'être évalués sur ToutAvis. Elle possède toutes les méthodes en lien avec la gestion des reviews.
 * @author   Yann Andreu et Yannick Omnès
 * @uml.dependency   supplier="avis.Basics"
 */
public abstract class Item {

	/**
	 * Liste des reviews associées à l'item courant
	 * @uml.property   name="reviews"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	
	/**
	 * Construit un item.
	 */
	public Item(){
		reviews = new LinkedList<Review>();
	}
	
	
	/**
	 * Détermine si le membre passé en paramètre a déjà déposé un avis sur l'item courant. Si oui, le met à jour. Sinon, crée une nouvelle review et l'ajoute aux listes de l'item et du membre
	 * @param member le membre identifié comme souhaitant déposer un avis sur l'item courant
	 * @param note la note que le membre a attribuée à l'item courant
	 * @param commentaire le commentaire du membre sur l'item courant
	 * @throws BadEntry
	 * @see Review
	 */
	public void addOrModifyReview(Member member, float note, String commentaire) throws BadEntry{
		Review review = new Review(member, note, commentaire); // Lève les BadEntry sur commentaire et note.
		
		/* Si le Member a déjà déposé un avis, on va le modifier. Pour cela, il faut parcourir 
		 * les reviews de l'item jusqu'à trouver la review déposée par le Member membre.
		 */
		for (Review r : reviews)
			if (r.membreIs(member)){
				r.modify(note, commentaire);
				return;
			}

		// Si le membre n'a pas encore déposé d'avis sur le film, on ajoute le nouvel avis à la liste attachée au film
		this.reviews.add(review);
		// Dans le même temps, on ajoute la même review à la liste de reviews attachée au Member concerné. 
		member.addReview(review);
	}
		
	
	/**
	 * Calcule la moyenne des notes comprises dans les reviews associées à l'item courant
	 * @return la note moyenne des reviews formulées sur l'item courant
	 */
	public float moyenneNoteReview(){
		float somme = 0;
		for (Review review : reviews)
			somme+=review.getNote();
		
		return (somme/(float)(reviews.size()));
	}

	
	/**
	 * Evalue une des Review rattachée à l'Item courant en la sélectionnant par le Member qui l'a déposée
	 * @param memberEvaluateur Membre qui évalue une Review
	 * @param note note à attribuer à la Review évaluée
	 */
	public void evaluateReview(Member memberDeposant, Member memberEvaluateur, float note)	throws BadEntry, Exception {
	
		for(Review r : reviews)
			if(r.membreIs(memberDeposant))
			{
				r.addOrModifyEvaluation(memberEvaluateur, note);
				return;
			}
		throw new Exception("Le membre dont vous voulez évaluer l'avis n'a pas déposé d'avis sur l'Item courant");
		}		


	/**
	 * Renvoie une représentation de l'item sous forme d'un String
	 * @return un String comprenant la représentation textuelle de l'Item courant
	 */
	public String toString(){
		String s="";
		s+= "Avis (moyenne : "+ moyenneNoteReview() +"/5) : \n";
		if (!reviews.isEmpty())
			for(Review avis:reviews) // Ajoute tous les avis déposés sur ce Film
				s+= avis + "\n";
		else
			s+= "Aucun \n";
		return s;
	}

					
	/**
	 * Méthode devant être redéfinie dans l'ensemble des classes filles
	 * Détermine si deux objets sont égaux
	 * @param o objet à comprarer à l'Item courant
	 * @return true si o est égal à l'Item courant, false sinon
	 */
	public abstract boolean equals(Object o);


		
	
				
}
