
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkNegativeObjectPropertyAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkNegativeObjectPropertyAssertionAxiom}
 * from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkNegativeObjectPropertyAssertionAxiomFilter
		extends
		ElkNegativeObjectPropertyAssertionAxiomVisitor<ElkNegativeObjectPropertyAssertionAxiom> {

	// nothing else

}