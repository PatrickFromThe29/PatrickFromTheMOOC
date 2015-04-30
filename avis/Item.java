package avis;

import java.util.LinkedList;


public abstract class Item {

	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	/**
	 */
	public void addOrModifyReview(Member member, float note, String commentaire){
		/* Si le Member a déjà déposé un avis, on va le modifier. Pour cela, il faut parcourir 
		 * les reviews de l'item jusqu'à trouver la review déposée par le Member membre.
		 */
		for (int i=0; i<reviews.size();i++)
			if (reviews.get(i).membreIs(member)){
				reviews.get(i).modifyReview(note, commentaire);
				return;
			}
		
		// S'il s'avère que le membre n'a pas déposé d'avis, il faut en créer un nouveau
		this.reviews.add(new Review(member, note, commentaire));
	}
		
	
	/**
	 */
	public float moyenneNoteReview(){
		return 0;
	}

			


	
	public String toString(){
		String s="";
		s+= "Avis : \n";
		if (!reviews.isEmpty())
			for(Review avis:reviews) // Ajoute tous les avis déposés sur ce Film
				s+= avis + "\n";
		return s;
	}

					
	/**
	 */
	public abstract boolean equals(Object o);
					
}
