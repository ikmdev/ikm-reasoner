
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataPropertyRangeAxiom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyRangeAxiomVisitor;

/**
 * A filter producing objects in {@link ElkDataPropertyRangeAxiom} from objects
 * of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataPropertyRangeAxiomFilter extends
		ElkDataPropertyRangeAxiomVisitor<ElkDataPropertyRangeAxiom> {

	// nothing else

}