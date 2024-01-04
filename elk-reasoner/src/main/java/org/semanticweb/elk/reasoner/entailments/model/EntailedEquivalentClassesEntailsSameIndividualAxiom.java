
package org.semanticweb.elk.reasoner.entailments.model;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * {@link ElkSameIndividualAxiom} was entailed because an
 * {@link org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom
 * ElkEquivalentClassesAxiom} asserting that nominals of
 * {@link ElkSameIndividualAxiom#getIndividuals()} are equivalent was entailed.
 * 
 * @author Peter Skocovsky
 */
public interface EntailedEquivalentClassesEntailsSameIndividualAxiom
		extends AxiomEntailmentInference<ElkSameIndividualAxiom> {

	@Override
	SameIndividualAxiomEntailment getConclusion();

	@Override
	List<? extends EquivalentClassesAxiomEntailment> getPremises();

	public static interface Visitor<O> {
		O visit(EntailedEquivalentClassesEntailsSameIndividualAxiom entailedEquivalentClassesEntailsSameIndividualAxiom);
	}

}
