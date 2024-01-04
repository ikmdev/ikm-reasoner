
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkSubClassOfAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSubClassOfAxiom} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSubClassOfAxiomFilter extends
		ElkSubClassOfAxiomVisitor<ElkSubClassOfAxiom> {

	// nothing else

}