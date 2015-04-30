package avis;


public class Review {

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Member"
	 */
	private Member member = null;

	/**
	 * @uml.property  name="note"
	 */
	private float note;
	/**
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;
		
		/**
		 */
		public Review(Member member, float note, String commentaire){
		}

			
			/**
			 */
			public boolean equals(Review review){
				return false;	
			}


				
				/**
				 */
				public String toString(){
					return "";	
				}


					
					/**
					 */
					public void modifyReview(float note, String commentaire){
					}


						
						/**
						 */
						public boolean membreIs(Member membre){
							return false;	
						}

}
