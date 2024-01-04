
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkDifferentIndividualsAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface DifferentIndividualsAxiomEntailment
		extends AxiomEntailment<ElkDifferentIndividualsAxiom> {

	public static interface Visitor<O> {
		O visit(DifferentIndividualsAxiomEntailment differentIndividualsEntailment);
	}

}
