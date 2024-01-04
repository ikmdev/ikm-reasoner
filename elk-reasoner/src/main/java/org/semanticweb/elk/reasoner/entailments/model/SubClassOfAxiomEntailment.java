
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkSubClassOfAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface SubClassOfAxiomEntailment
		extends AxiomEntailment<ElkSubClassOfAxiom> {

	public static interface Visitor<O> {
		O visit(SubClassOfAxiomEntailment subClassOfAxiomEntailment);
	}

}
