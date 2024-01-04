
package org.semanticweb.elk.reasoner.entailments.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * {@link ElkEquivalentClassesAxiom} was entailed because a cyclic inclusion of
 * classes from {@link ElkEquivalentClassesAxiom#getClassExpressions()} was
 * derived.
 * <p>
 * {@link #getPremises()} returns {@link SubClassOfAxiomEntailment}-s over all
 * the classes from {@link ElkEquivalentClassesAxiom#getClassExpressions()},
 * such that superclass of one is the subclass of the next one and superclass of
 * the last one is the subclass of the first one.
 * 
 * @author Peter Skocovsky
 */
public interface EntailedClassInclusionCycleEntailsEquivalentClassesAxiom
		extends AxiomEntailmentInference<ElkEquivalentClassesAxiom> {

	@Override
	EquivalentClassesAxiomEntailment getConclusion();

	@Override
	List<? extends SubClassOfAxiomEntailment> getPremises();

	public static interface Visitor<O> {
		O visit(EntailedClassInclusionCycleEntailsEquivalentClassesAxiom derivedClassInclusionCycleEntailsEquivalentClassesAxiom);
	}

}
