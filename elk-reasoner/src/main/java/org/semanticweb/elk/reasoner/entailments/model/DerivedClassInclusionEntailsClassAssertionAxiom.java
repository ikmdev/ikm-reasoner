
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * {@link ElkClassAssertionAxiom} was entailed because inclusion of
 * {@link ElkClassAssertionAxiom#getIndividual()} in
 * {@link ElkClassAssertionAxiom#getClassExpression()} was derived.
 * <p>
 * {@link #getReason()} returns a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getDestination()} corresponding to
 * {@link ElkClassAssertionAxiom#getIndividual()} and
 * {@link SubClassInclusionComposed#getSubsumer()} corresponding to
 * {@link ElkClassAssertionAxiom#getClassExpression()}.
 * 
 * @author Peter Skocovsky
 */
public interface DerivedClassInclusionEntailsClassAssertionAxiom
		extends AxiomEntailmentInference<ElkClassAssertionAxiom>,
		HasReason<SubClassInclusionComposed> {

	@Override
	ClassAssertionAxiomEntailment getConclusion();

	public static interface Visitor<O> {
		O visit(DerivedClassInclusionEntailsClassAssertionAxiom derivedClassInclusionEntailsClassAssertionAxiom);
	}

}
