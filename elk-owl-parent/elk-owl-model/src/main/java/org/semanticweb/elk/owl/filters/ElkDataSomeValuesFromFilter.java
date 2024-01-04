
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkDataSomeValuesFromVisitor;

/**
 * A filter producing objects in {@link ElkDataSomeValuesFrom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataSomeValuesFromFilter extends
		ElkDataSomeValuesFromVisitor<ElkDataSomeValuesFrom> {

	// nothing else

}