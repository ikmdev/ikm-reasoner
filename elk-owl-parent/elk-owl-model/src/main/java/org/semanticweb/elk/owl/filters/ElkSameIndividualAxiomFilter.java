
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.owl.visitors.ElkSameIndividualAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSameIndividualAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSameIndividualAxiomFilter extends
		ElkSameIndividualAxiomVisitor<ElkSameIndividualAxiom> {

	// nothing else

}