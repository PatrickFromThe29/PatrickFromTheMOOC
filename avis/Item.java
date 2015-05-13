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
	 * titre de l'Item
	 * @uml.property name="titre"
	 */
	protected String titre;
	
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
		float karma = 0;
		float sommeKarma = 0;
		
		if (reviews.size() == 0)
			return 0.0f;
					
		for (Review review : reviews)
		{
			karma = review.getMember().getKarma();
			somme+=review.getNote()*karma;
			sommeKarma+= karma;
		}
		return somme/sommeKarma;
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
				memberDeposant.updateKarma(); // A chaque note déposée sur l'avis d'un membre, il faut actualiser le karma de celui-ci.
				return;
			}
		throw new Exception("Le membre dont vous voulez évaluer l'avis n'a pas déposé d'avis sur l'Item courant");
	}	
	
	/**
	 * Compare le titre passé en paramètre avec le titre de l'Item courant
	 * @param titre le titre à comparer au titre de l'Item courant
	 * @return true si le titre passé en paramètre correspond au titre de l'Item courant, false sinon
	 */
	public boolean titleIs(String titre) throws BadEntry {
		//=============================== ANALYSE DES CAS D'ERREURS ================================
		// Si le titre de l'Item envoyé est null ou comporte moins de 1 caractère qui n'est pas un espace, BadEntry est levée.
		if (titre==null || titre.replaceAll(" ", "").length()<1)
			throw new BadEntry("Le nom de l'Item à rechercher doit être instancié et comporter au moins un caractère différent d'un espace)");
		
		//===================================== COMPARAISON ========================================
		return (this.titre.toUpperCase().equals(titre.trim().toUpperCase()));	
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
