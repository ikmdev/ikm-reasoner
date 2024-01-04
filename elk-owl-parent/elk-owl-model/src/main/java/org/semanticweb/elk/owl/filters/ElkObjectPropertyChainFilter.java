
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyChain;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyChainVisitor;

/**
 * A filter producing objects in {@link ElkObjectPropertyChain} from objects of
 * this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkObjectPropertyChainFilter extends
		ElkObjectPropertyChainVisitor<ElkObjectPropertyChain> {

	// nothing else

}