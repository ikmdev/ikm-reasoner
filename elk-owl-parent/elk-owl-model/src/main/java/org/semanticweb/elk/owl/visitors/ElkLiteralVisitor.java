
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkLiteral;

/**
 * Visitor interface for {@link ElkLiteral}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkLiteralVisitor<O> {

	O visit(ElkLiteral expression);

}
