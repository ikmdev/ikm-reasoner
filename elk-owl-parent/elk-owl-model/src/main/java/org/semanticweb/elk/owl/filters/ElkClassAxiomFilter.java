
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkClassAxiom;

/**
 * A filter producing objects in {@link ElkClassAxiom} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkClassAxiomFilter extends ElkDisjointClassesAxiomFilter,
		ElkDisjointUnionAxiomFilter, ElkEquivalentClassesAxiomFilter,
		ElkSubClassOfAxiomFilter {

	// combined visitor

}
