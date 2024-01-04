 
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;

/**
 * How was some {@link AxiomEntailment} entailed.
 * 
 * @author Peter Skocovsky
 *
 * @param <A>
 *            The type of the axiom.
 */
public interface AxiomEntailmentInference<A extends ElkAxiom>
		extends EntailmentInference {

	@Override
	AxiomEntailment<? extends A> getConclusion();

	public static interface Visitor<O>
			extends DerivedClassInclusionEntailsClassAssertionAxiom.Visitor<O>,
			DerivedClassInclusionEntailsObjectPropertyAssertionAxiom.Visitor<O>,
			DerivedClassInclusionEntailsObjectPropertyDomainAxiom.Visitor<O>,
			EntailedClassInclusionCycleEntailsEquivalentClassesAxiom.Visitor<O>,
			EntailedDisjointClassesEntailsDifferentIndividualsAxiom.Visitor<O>,
			EntailedEquivalentClassesEntailsSameIndividualAxiom.Visitor<O>,
			EntailedIntersectionInconsistencyEntailsDisjointClassesAxiom.Visitor<O>,
			OntologyInconsistencyEntailsAnyAxiom.Visitor<O>,
			SubClassOfAxiomEntailmentInference.Visitor<O> {
		// combined interface
	}

}
