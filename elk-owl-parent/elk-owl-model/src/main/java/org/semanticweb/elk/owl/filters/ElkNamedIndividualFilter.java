
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.owl.visitors.ElkNamedIndividualVisitor;

/**
 * A filter producing objects in {@link ElkNamedIndividual} from objects of this
 * type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkNamedIndividualFilter extends
		ElkNamedIndividualVisitor<ElkNamedIndividual> {

	// nothing else

}