
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkObjectSomeValuesFromVisitor;

/**
 * A filter producing objects in {@link ElkObjectSomeValuesFrom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectSomeValuesFromFilter extends
		ElkObjectSomeValuesFromVisitor<ElkObjectSomeValuesFrom> {

	// nothing else

}