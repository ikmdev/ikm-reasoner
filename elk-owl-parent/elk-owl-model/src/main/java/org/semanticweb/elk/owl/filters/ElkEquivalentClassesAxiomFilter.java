
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.visitors.ElkEquivalentClassesAxiomVisitor;

/**
 * A filter producing objects in {@link ElkEquivalentClassesAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkEquivalentClassesAxiomFilter extends
		ElkEquivalentClassesAxiomVisitor<ElkEquivalentClassesAxiom> {

	// nothing else

}