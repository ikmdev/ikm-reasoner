 
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.owl.visitors.ElkDisjointClassesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDisjointClassesAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDisjointClassesAxiomFilter extends
		ElkDisjointClassesAxiomVisitor<ElkDisjointClassesAxiom> {

	// nothing else

}