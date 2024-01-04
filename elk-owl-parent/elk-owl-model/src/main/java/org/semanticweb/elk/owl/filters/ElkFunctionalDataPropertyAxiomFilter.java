
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkFunctionalDataPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkFunctionalDataPropertyAxiomVisitor;

/**
 * A filter producing objects in {@link ElkFunctionalDataPropertyAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkFunctionalDataPropertyAxiomFilter extends
		ElkFunctionalDataPropertyAxiomVisitor<ElkFunctionalDataPropertyAxiom> {

	// nothing else

}