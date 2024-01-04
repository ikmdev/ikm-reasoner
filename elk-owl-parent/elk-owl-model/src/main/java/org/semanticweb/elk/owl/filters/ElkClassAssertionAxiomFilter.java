
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkClassAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkClassAssertionAxiom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkClassAssertionAxiomFilter extends
		ElkClassAssertionAxiomVisitor<ElkClassAssertionAxiom> {

	// nothing else

}
