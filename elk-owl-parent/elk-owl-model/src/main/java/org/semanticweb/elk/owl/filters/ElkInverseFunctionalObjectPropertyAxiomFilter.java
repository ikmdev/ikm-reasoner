
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkInverseFunctionalObjectPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkInverseFunctionalObjectPropertyAxiom}
 * from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkInverseFunctionalObjectPropertyAxiomFilter
		extends
		ElkInverseFunctionalObjectPropertyAxiomVisitor<ElkInverseFunctionalObjectPropertyAxiom> {

	// nothing else

}