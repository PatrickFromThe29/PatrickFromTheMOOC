package avis;

import java.util.LinkedList;


public class Member {

	/**
	 * @uml.property  name="pseudo" readOnly="true"
	 */
	private String pseudo;



	/**
	 * @uml.property  name="password"
	 */
	private String password;



		
		/**
		 */
		public boolean hasPseudo(String pseudo){
			return false;	
		}




		/**
		 * @uml.property  name="profil"
		 */
		private String profil;




			
			/**
			 */
			public String toString(){
				return "";	
			}





				
				/**
				 */
				public boolean equals(Object member){
					return false;	
				}






					
					/**
					 */
					public boolean passwordIs(String password){
						return false;	
					}






						
						/**
						 */
						public Member(String pseudo, String password, String profil){
						}




						/**
						 * @uml.property  name="reviews"
						 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="member:avis.Review"
						 */
						private LinkedList reviews;







}
