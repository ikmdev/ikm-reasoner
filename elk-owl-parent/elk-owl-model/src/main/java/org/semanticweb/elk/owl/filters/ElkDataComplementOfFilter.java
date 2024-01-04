
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataComplementOf;
import org.semanticweb.elk.owl.visitors.ElkDataComplementOfVisitor;

/**
 * A filter producing objects in {@link ElkDataComplementOf} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataComplementOfFilter extends
		ElkDataComplementOfVisitor<ElkDataComplementOf> {

	// nothing else

}