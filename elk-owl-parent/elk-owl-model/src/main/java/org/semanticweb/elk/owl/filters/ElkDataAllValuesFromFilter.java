
package org.semanticweb.elk.owl.filters;

import org.semanticweb.elk.owl.interfaces.ElkDataAllValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkDataAllValuesFromVisitor;

/**
 * A filter producing objects in {@link ElkDataAllValuesFrom} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataAllValuesFromFilter extends
		ElkDataAllValuesFromVisitor<ElkDataAllValuesFrom> {

	// nothing else

}
