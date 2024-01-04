
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkReflexiveObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkReflexiveObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkReflexiveObjectPropertyAxiomFilter extends
		ElkReflexiveObjectPropertyAxiomVisitor<ElkReflexiveObjectPropertyAxiom> {

	// nothing else

}