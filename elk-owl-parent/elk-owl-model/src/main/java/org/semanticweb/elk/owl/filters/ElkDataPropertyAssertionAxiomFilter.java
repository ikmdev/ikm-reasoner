
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDataPropertyAssertionAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataPropertyAssertionAxiomFilter extends
		ElkDataPropertyAssertionAxiomVisitor<ElkDataPropertyAssertionAxiom> {

	// nothing else

}