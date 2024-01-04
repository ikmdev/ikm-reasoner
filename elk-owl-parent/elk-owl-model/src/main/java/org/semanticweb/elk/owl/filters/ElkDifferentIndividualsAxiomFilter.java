
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.visitors.ElkDifferentIndividualsAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDifferentIndividualsAxiom} from
 * objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDifferentIndividualsAxiomFilter extends
		ElkDifferentIndividualsAxiomVisitor<ElkDifferentIndividualsAxiom> {

	// nothing else

}