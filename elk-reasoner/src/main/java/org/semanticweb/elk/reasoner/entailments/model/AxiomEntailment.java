
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * Instances of this interface represent an entailment of an axiom.
 * 
 * @author Peter Skocovsky
 *
 * @param <A>
 *            The type of the axiom.
 */
public interface AxiomEntailment<A extends ElkAxiom> extends Entailment {

	/**
	 * @return The axiom that is entailed.
	 */
	A getAxiom();

	public static interface Visitor<O>
			extends ClassAssertionAxiomEntailment.Visitor<O>,
			DifferentIndividualsAxiomEntailment.Visitor<O>,
			DisjointClassesAxiomEntailment.Visitor<O>,
			EquivalentClassesAxiomEntailment.Visitor<O>,
			ObjectPropertyAssertionAxiomEntailment.Visitor<O>,
			ObjectPropertyDomainAxiomEntailment.Visitor<O>,
			SameIndividualAxiomEntailment.Visitor<O>,
			SubClassOfAxiomEntailment.Visitor<O> {
		// combined visitor
	}

}
