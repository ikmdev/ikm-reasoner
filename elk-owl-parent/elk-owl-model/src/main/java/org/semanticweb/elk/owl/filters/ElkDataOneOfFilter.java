
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkDataOneOf;
import org.semanticweb.elk.owl.visitors.ElkDataOneOfVisitor;

/**
 * A filter producing objects in {@link ElkDataOneOf} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkDataOneOfFilter extends ElkDataOneOfVisitor<ElkDataOneOf> {

	// nothing else

}