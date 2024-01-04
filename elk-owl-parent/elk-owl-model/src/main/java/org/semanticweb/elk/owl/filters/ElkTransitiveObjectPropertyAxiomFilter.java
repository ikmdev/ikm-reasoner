
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkTransitiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkTransitiveObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkTransitiveObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkTransitiveObjectPropertyAxiomFilter
		extends
		ElkTransitiveObjectPropertyAxiomVisitor<ElkTransitiveObjectPropertyAxiom> {

	// nothing else

}