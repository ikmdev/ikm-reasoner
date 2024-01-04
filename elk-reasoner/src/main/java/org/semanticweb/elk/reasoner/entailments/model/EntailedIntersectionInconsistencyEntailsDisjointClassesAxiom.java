
package org.semanticweb.elk.reasoner.entailments.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;

/**
 * {@link ElkDisjointClassesAxiom} was entailed because inconsistencies of
 * pairwise intersections of classes from
 * {@link ElkDisjointClassesAxiom#getClassExpressions()} were entailed.
 * <p>
 * {@link #getPremises()} returns {@link SubClassOfAxiomEntailment}-s where
 * subclasses are all pairwise intersections of classes from
 * {@link ElkDisjointClassesAxiom#getClassExpressions()} and superclasses are
 * {@code owl:Nothing}.
 * 
 * @author Peter Skocovsky
 */
public interface EntailedIntersectionInconsistencyEntailsDisjointClassesAxiom
		extends AxiomEntailmentInference<ElkDisjointClassesAxiom> {

	@Override
	DisjointClassesAxiomEntailment getConclusion();

	@Override
	List<? extends SubClassOfAxiomEntailment> getPremises();

	public static interface Visitor<O> {
		O visit(EntailedIntersectionInconsistencyEntailsDisjointClassesAxiom derivedIntersectionInconsistencyEntailsDisjointClassesAxiom);
	}

}
