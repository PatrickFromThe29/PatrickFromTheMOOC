package avis;


import exception.BadEntry;


public class Review {

	/** 
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Member"
	 */
	private Member member = null;

	/** 
	 * @uml.property name="note" readOnly="true"
	 */
	private float note;
	/**
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;
		
	/**
	 */
	public Review(Member member, float note, String commentaire) throws BadEntry
	{
		//=============================== ANALYSE DES CAS D'ERREURS ================================	
		// Si le Membre ou le commentaire ne sont pas instanciés on lève BadEntry
		if (commentaire==null || member==null)
			throw new BadEntry("Le commentaire et le membre doivent être instanciés.");
				
		// Si la note n'est pas comprise entre 0.0 et 5.0 on lève BadEntry
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note doit être comprise entre 0.0 et 5.0.");
		
		//================================ CONSTRUCTION DE LA REVIEW ===============================
		this.member = member;
		this.note = note;
		this.commentaire = commentaire;	
	}

	
	/**
	 * 	
	 * @param note
	 * @param commentaire
	 */
	public void modify(float note, String commentaire) throws BadEntry
	{
		//=============================== ANALYSE DES CAS D'ERREURS ================================	
		// Si le commentaire n'est pas instancié on lève BadEntry
		if (commentaire==null)
			throw new BadEntry("Le commentaire doit être instancié.");
				
		// Si la note n'est pas comprise entre 0.0 et 5.0 on lève BadEntry
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note doit être comprise entre 0.0 et 5.0.");
		
		//================================ MODIFICATION DE LA REVIEW ===============================
		this.note = note;
		this.commentaire = commentaire;	
	}
					
	/**
	 */
	public boolean membreIs(Member membre){
		return (this.member.equals(membre));	
	}
	
	/**
	 * Getter of the property <tt>note</tt>
	 * @return  Returns the note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}
	
	/**
	 */
	public boolean equals(Review review){
		if (!(review instanceof Review))
			return false;
		return (this.member.equals(review.member) && this.note == review.note && this.commentaire.equals(review.commentaire));
	}
			
	/**
	 */
	public String toString(){
		String s = "Avis de "+ this.member + " : \n";
		s+= "Note : "+ this.note +"/5 \n";
		s+= "Commentaire : "+ this.commentaire;
		return s;
	}

}
