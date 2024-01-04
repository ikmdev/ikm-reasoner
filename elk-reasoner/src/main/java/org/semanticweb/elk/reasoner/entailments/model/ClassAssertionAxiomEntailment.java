
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkClassAssertionAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface ClassAssertionAxiomEntailment
		extends AxiomEntailment<ElkClassAssertionAxiom> {

	public static interface Visitor<O> {
		O visit(ClassAssertionAxiomEntailment classAssertionAxiomEntailment);
	}

}
