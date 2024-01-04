
package org.semanticweb.elk.owl.filters;


import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkLiteralVisitor;

/**
 * A filter producing objects in {@link ElkLiteral} from objects of this type.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ElkLiteralFilter extends ElkLiteralVisitor<ElkLiteral> {

	// nothing else

}