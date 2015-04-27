package avis;

import java.util.LinkedList;


public abstract class Item {

	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	protected LinkedList reviews;

	/** 
	 * Getter of the property <tt>reviews</tt>
	 * @return  Returns the reviews.
	 * @uml.property  name="reviews"
	 */
	public LinkedList getReviews() {
		return reviews;
	}

	/** 
	 * Setter of the property <tt>reviews</tt>
	 * @param reviews  The reviews to set.
	 * @uml.property  name="reviews"
	 */
	public void setReviews(LinkedList reviews) {
		this.reviews = reviews;
	}

		
		/**
		 */
		public float moyenneNoteReview(){
			return 0;
		}

			
			/**
			 */
			public void addOrModifyReview(Member member, float note, String commentaire){
			}

				
				/**
				 */
				public abstract String toString();

					
					/**
					 */
					public abstract boolean equals(Object o);
					
				

}
