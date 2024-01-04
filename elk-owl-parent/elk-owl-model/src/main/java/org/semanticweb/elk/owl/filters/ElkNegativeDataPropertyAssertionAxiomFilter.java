
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkNegativeDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkNegativeDataPropertyAssertionAxiomVisitor;

/**
 * A filter producing objects in {@link ElkNegativeDataPropertyAssertionAxiom}
 * from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkNegativeDataPropertyAssertionAxiomFilter
		extends
		ElkNegativeDataPropertyAssertionAxiomVisitor<ElkNegativeDataPropertyAssertionAxiom> {

	// nothing else

}