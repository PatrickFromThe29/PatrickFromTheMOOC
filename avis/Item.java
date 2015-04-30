package avis;

import java.util.LinkedList;

import exception.BadEntry;


public abstract class Item {

	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	
	/**
	 */
	public Item(){
		reviews = new LinkedList<Review>();
	}
	
	
	/**
	 * @throws BadEntry 
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
	 */
	public float moyenneNoteReview(){
		float somme = 0;
		for (Review review : reviews)
			somme+=review.getNote();
		
		return (somme/(float)(reviews.size()));
	}

			


	
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
	 */
	public abstract boolean equals(Object o);			
}
