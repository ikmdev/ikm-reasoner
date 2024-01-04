
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

/**
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom
 * ElkSubClassOfAxiom} was entailed because inclusion of
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSubClassExpression()
 * ElkSubClassOfAxiom.getSubClassExpression()} in
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSuperClassExpression()
 * ElkSubClassOfAxiom.getSuperClassExpression()} was derived.
 * <p>
 * {@link #getReason()} returns a {@link SubClassInclusionComposed} with
 * {@link SubClassInclusionComposed#getDestination()} corresponding to
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSubClassExpression()
 * ElkSubClassOfAxiom.getSubClassExpression()} and
 * {@link SubClassInclusionComposed#getSubsumer()} corresponding to
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSuperClassExpression()
 * ElkSubClassOfAxiom.getSuperClassExpression()}.
 * 
 * @author Peter Skocovsky
 */
public interface DerivedClassInclusionEntailsSubClassOfAxiom
		extends SubClassOfAxiomEntailmentInference,
		HasReason<SubClassInclusionComposed> {

	public static interface Visitor<O> {
		O visit(DerivedClassInclusionEntailsSubClassOfAxiom derivedClassInclusionEntailsSubClassOfAxiom);
	}

}
