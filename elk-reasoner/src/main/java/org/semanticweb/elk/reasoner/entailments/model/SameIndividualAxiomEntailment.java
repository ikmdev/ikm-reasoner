 
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkSameIndividualAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface SameIndividualAxiomEntailment
		extends AxiomEntailment<ElkSameIndividualAxiom> {

	public static interface Visitor<O> {
		O visit(SameIndividualAxiomEntailment sameIndividualAxiomEntailment);
	}

}
