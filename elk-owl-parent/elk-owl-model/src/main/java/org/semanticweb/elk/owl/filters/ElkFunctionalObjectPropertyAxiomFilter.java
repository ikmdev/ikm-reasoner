
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkFunctionalObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkFunctionalObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkFunctionalObjectPropertyAxiomFilter
		extends
		ElkFunctionalObjectPropertyAxiomVisitor<ElkFunctionalObjectPropertyAxiom> {

	// nothing else

}