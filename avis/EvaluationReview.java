package avis;

import exception.BadEntry;

/**
 * Représente une évaluation déposée par un membre sur une Review d'un autre membre
 * @author Yann Andreu et Yannick Omnès
 *
 */
public class EvaluationReview {

	/**
	 * Le Member à l'origine de l'EvaluationReview courante
	 * @uml.property  name="member"
	 * @uml.associationEnd  inverse="evaluationReview:avis.Member"
	 */
	private Member member;
	
	/** 
	 * La note attribuée par le Member member
	 * @uml.property name="note" readOnly="true"
	 */
	private float note;
		
	/**
	 * Instancie une nouvelle EvaluationReview
	 * @param note Note attribuée par le Member member
	 * @param member Membre qui note une Review, à l'origine de l'EvaluationReview courante
	 * @throws BadEntry Si member n'est pas instancié ou si la note n'est pas comprise entre 0 et 5.
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
	 * Retourne la valeur de <tt>note</tt>
	 * @return  retourne la note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}	
		
	/**
	 * Modifie la note de l'EvaluationReview courante si un Member note de nouveau la même Review	
	 * @param note La nouvelle note de l'EvaluationReview
	 * @throws BadEntry Si la note n'est pas comprise entre 0 et 5
	 */
	public void modify(float note) throws BadEntry{
	
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si la note n'est pas comprise entre 0 et 5
		if (note<0.0f || note>5.0f)
			throw new BadEntry("La note d'évaluation de la review doit être comprise entre 0.0 et 5.0 inclus.");
	
		//===================== CONSTRUCTION DE L'EVALUATION DE REVIEW ===============================
		this.note = note;
	}
	
	/**
	 * Permet de savoir si le Member qui a déposé l'EvaluationReview courante est celui passé en paramètre
	 * @param member Member à comparer avec le Member member de l'EvaluationReview courante
	 * @return true si les deux Member sont égaux, false sinon
	 */
	public boolean memberIs(Member member){
		return (this.member.equals(member));
	}
		
	/**
	 * Retourne une représentation textuelle de l'EvaluationReview courante.
	 * @return un String comprenant la description de l'EvaluationReview courante
	 */
	public String toString()
	{
		return "Le Member " + this.member + " a donné la note " + this.note +".";	
	}
			
	/**
	 * Compare deux EvaluationReview
	 * @param o Objet à comparer avec l'EvaluationReview courante
	 * @return true si l'objet o est égal à l'EvaluationReview courante, false sinon
	 */
	public boolean equals(Object o)
	{
		if (!(o instanceof EvaluationReview))
			return false;
		if (this.member.equals(((EvaluationReview)o).member) && this.note == ((EvaluationReview)o).note)
			return true;	
		else
			return false;
	}
}
