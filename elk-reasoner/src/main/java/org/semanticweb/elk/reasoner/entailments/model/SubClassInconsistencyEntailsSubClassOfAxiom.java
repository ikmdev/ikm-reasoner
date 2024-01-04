
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;

/**
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom
 * ElkSubClassOfAxiom} was entailed because inconsistency of
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSubClassExpression()
 * ElkSubClassOfAxiom.getSubClassExpression()} was derived.
 * <p>
 * {@link #getReason()} returns a {@link ClassInconsistency} with
 * {@link ClassInconsistency#getDestination()} corresponding to
 * {@link org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom#getSubClassExpression()
 * ElkSubClassOfAxiom.getSubClassExpression()}.
 * 
 * @author Peter Skocovsky
 */
public interface SubClassInconsistencyEntailsSubClassOfAxiom extends
		SubClassOfAxiomEntailmentInference, HasReason<ClassInconsistency> {

	public static interface Visitor<O> {
		O visit(SubClassInconsistencyEntailsSubClassOfAxiom subClassInconsistencyEntailsSubClassOfAxiom);
	}

}
