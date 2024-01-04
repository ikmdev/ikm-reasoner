
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkAsymmetricObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkAsymmetricObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkAsymmetricObjectPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkAsymmetricObjectPropertyAxiomFilter
		extends
		ElkAsymmetricObjectPropertyAxiomVisitor<ElkAsymmetricObjectPropertyAxiom> {

	// nothing else

}
