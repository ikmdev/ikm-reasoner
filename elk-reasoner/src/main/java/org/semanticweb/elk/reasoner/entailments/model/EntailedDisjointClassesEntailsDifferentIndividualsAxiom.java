
package org.semanticweb.elk.reasoner.entailments.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;

/**
 * {@link ElkDifferentIndividualsAxiom} was entailed because an
 * {@link org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom
 * ElkDisjointClassesAxiom} asserting that nominals of
 * {@link ElkDifferentIndividualsAxiom#getIndividuals()} are disjoint was
 * entailed.
 * 
 * @author Peter Skocovsky
 */
public interface EntailedDisjointClassesEntailsDifferentIndividualsAxiom
		extends AxiomEntailmentInference<ElkDifferentIndividualsAxiom> {

	@Override
	DifferentIndividualsAxiomEntailment getConclusion();

	@Override
	List<? extends DisjointClassesAxiomEntailment> getPremises();

	public static interface Visitor<O> {
		O visit(EntailedDisjointClassesEntailsDifferentIndividualsAxiom entailedDisjointClassesEntailsDifferentIndividualsAxiom);
	}

}
