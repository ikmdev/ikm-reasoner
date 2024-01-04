
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkIrreflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkIrreflexiveObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkIrreflexiveObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkIrreflexiveObjectPropertyAxiomFilter
		extends
		ElkIrreflexiveObjectPropertyAxiomVisitor<ElkIrreflexiveObjectPropertyAxiom> {

	// nothing else

}