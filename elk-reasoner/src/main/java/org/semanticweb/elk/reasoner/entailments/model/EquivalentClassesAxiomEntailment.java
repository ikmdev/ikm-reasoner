
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkEquivalentClassesAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface EquivalentClassesAxiomEntailment
		extends AxiomEntailment<ElkEquivalentClassesAxiom> {

	public static interface Visitor<O> {
		O visit(EquivalentClassesAxiomEntailment equivalentClassesAxiomEntailment);
	}

}
