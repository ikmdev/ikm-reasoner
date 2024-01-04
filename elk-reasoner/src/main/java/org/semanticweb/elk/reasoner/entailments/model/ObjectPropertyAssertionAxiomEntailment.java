
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkObjectPropertyAssertionAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface ObjectPropertyAssertionAxiomEntailment
		extends AxiomEntailment<ElkObjectPropertyAssertionAxiom> {

	public static interface Visitor<O> {
		O visit(ObjectPropertyAssertionAxiomEntailment objectPropertyAssertionAxiomEntailment);
	}

}
