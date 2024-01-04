
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectAllValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkObjectAllValuesFromVisitor;

/**
 * A filter producing objects in {@link ElkObjectAllValuesFrom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectAllValuesFromFilter extends
		ElkObjectAllValuesFromVisitor<ElkObjectAllValuesFrom> {

	// nothing else

}