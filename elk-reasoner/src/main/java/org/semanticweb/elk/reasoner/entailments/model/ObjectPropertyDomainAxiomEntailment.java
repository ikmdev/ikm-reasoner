
package org.semanticweb.elk.reasoner.entailments.model;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;

/**
 * Instances of this interface represent an entailment of
 * {@link ElkObjectPropertyDomainAxiom}.
 * 
 * @author Peter Skocovsky
 */
public interface ObjectPropertyDomainAxiomEntailment
		extends AxiomEntailment<ElkObjectPropertyDomainAxiom> {

	public static interface Visitor<O> {
		O visit(ObjectPropertyDomainAxiomEntailment objectPropertyDomainAxiomEntailment);
	}

}
