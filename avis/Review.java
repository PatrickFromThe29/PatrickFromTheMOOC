package avis;


import exception.BadEntry;

/**
 * Représente un avis déposé sur un Item du SocialNetwork
 * @author Yann Andreu et Yannick Omnès
 */
public class Review {

	/** 
	 * Member qui a déposé la Review
	 * @uml.property name="member"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Member"
	 */
	private Member member = null;

	/** 
	 * note donnée par le Member
	 * @uml.property name="note" readOnly="true"
	 */
	private float note;
	/**
	 * commentaire déposé par le Member
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;
		
	/**
	 * Construit une nouvelle Review
	 * @param member le Member à l'origine de la Review
	 * @param note la note à mettre dans la nouvelle Review
	 * @param commentaire le commentaire à mettre dans la nouvelle Review
	 * @throws BadEntry
	 * <ul>
	 *  <li>  si le member n'est pas instancié.</li>
	 *  <li>  si le commentaire n'est pas instancié.</li>
	 *  <li>  si la note n'est pas comprise entre 0 et 5.</li>
	 * </ul> 
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
	 * Modifie la note et le commentaire d'une Review	
	 * @param note la note que le Member attribue à l'Item évalué
	 * @param commentaire le commentaire du Member sur l'item évalué
	 * @ throws BadEntry
	 * <ul>
	 *  <li>  si le commentaire n'est pas instancié.</li>
	 *  <li>  si la note n'est pas comprise entre 0 et 5.</li>
	 * </ul><br> 
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
	 * Permet de savoir si le membre passé en paramètre est celui qui a déposé la Review courante
	 * @param membre Member à comparer avec le membre qui a déposé la review courante
	 * @return true si le membre passé en paramètre est celui qui a déposé la Review, false sinon.
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
	 * Compare une Review avec la Review courante
	 * @param review Review à comparer avec la Review courante
	 * @return true si la Review review est égale à la Review courante, faux sinon.
	 */
	public boolean equals(Review review){
		if (!(review instanceof Review))
			return false;
		return (this.member.equals(review.member) && this.note == review.note && this.commentaire.equals(review.commentaire));
	}
			
	/**
	 * Renvoie une représentation textuelle de la Review
	 * @return la représentation textuelle de la Review courante sous la forme d'un String
	 */
	public String toString(){
		String s = "Avis de "+ this.member + " : \n";
		s+= "Note : "+ this.note +"/5 \n";
		s+= "Commentaire : "+ this.commentaire;
		return s;
	}

}
