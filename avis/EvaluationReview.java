package avis;

import exception.BadEntry;

/**
 * 
 * @author Yann Andreu et Yannick Omnès
 *
 */
public class EvaluationReview {

	/**
	 * @uml.property  name="member"
	 * @uml.associationEnd  inverse="evaluationReview:avis.Member"
	 */
	private Member member;
	/** 
	 * @uml.property name="note" readOnly="true"
	 */
	private float note;
		
	/**
	 */
	public EvaluationReview(float note, Member member) throws BadEntry{
		
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le membre n'est pas instancié
		if (member == null)
			throw new BadEntry ("Le membre doit être instancié.");
		// Si la note n'est pas comprise entre 0 et 5
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note d'évaluation de la review doit être comprise entre 0.0 et 5.0 inclus.");
		
		//===================== CONSTRUCTION DE L'EVALUATION DE REVIEW ===============================
		this.member = member;
		this.note = note;
		
	}

	/**
	 * Getter of the property <tt>note</tt>
	 * @return  Returns the note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}	
		
		
	public void modify(float note, Member member) throws BadEntry{
	
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le membre n'est pas instancié
		if (member == null)
			throw new BadEntry ("Le membre doit être instancié.");
		// Si la note n'est pas comprise entre 0 et 5
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note d'évaluation de la review doit être comprise entre 0.0 et 5.0 inclus.");
	
		//===================== CONSTRUCTION DE L'EVALUATION DE REVIEW ===============================
		this.member = member;
		this.note = note;
	}
	
	/**
	 */
	public boolean memberIs(Member member){
		return (this.member.equals(member));
	}
		
	/**
	 */
	public String toString()
	{
		return "";	
	}
			
	/**
	 */
	public boolean equals(Object o)
	{
		return false;	
	}
}
