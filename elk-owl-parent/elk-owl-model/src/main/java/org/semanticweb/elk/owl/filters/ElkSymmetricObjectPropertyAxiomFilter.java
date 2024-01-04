
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkSymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkSymmetricObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkSymmetricObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkSymmetricObjectPropertyAxiomFilter extends
		ElkSymmetricObjectPropertyAxiomVisitor<ElkSymmetricObjectPropertyAxiom> {

	// nothing else

}