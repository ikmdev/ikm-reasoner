
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyRangeAxiomVisitor;

/**
 * A filter producing objects in {@link ElkObjectPropertyRangeAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyRangeAxiomFilter extends
		ElkObjectPropertyRangeAxiomVisitor<ElkObjectPropertyRangeAxiom> {

	// nothing else

}