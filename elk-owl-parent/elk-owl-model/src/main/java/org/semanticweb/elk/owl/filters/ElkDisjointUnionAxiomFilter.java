
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.owl.visitors.ElkDisjointUnionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDisjointUnionAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDisjointUnionAxiomFilter extends
		ElkDisjointUnionAxiomVisitor<ElkDisjointUnionAxiom> {

	// nothing else

}