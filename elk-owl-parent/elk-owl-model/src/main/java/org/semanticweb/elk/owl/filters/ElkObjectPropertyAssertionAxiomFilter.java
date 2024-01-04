
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkObjectPropertyAssertionAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyAssertionAxiomFilter extends
		ElkObjectPropertyAssertionAxiomVisitor<ElkObjectPropertyAssertionAxiom> {

	// nothing else

}