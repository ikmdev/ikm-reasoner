
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkHasKeyAxiom;
import org.semanticweb.elk.owl.visitors.ElkHasKeyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkHasKeyAxiom} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkHasKeyAxiomFilter extends
		ElkHasKeyAxiomVisitor<ElkHasKeyAxiom> {

	// nothing else

}