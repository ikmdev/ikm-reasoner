
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;

/**
 * Visitor pattern interface for instances of {@link ElkClassExpression}.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkClassExpressionVisitor<O> extends ElkClassVisitor<O>,
		ElkDataPropertyListRestrictionQualifiedVisitor<O>,
		ElkObjectComplementOfVisitor<O>, ElkObjectIntersectionOfVisitor<O>,
		ElkObjectOneOfVisitor<O>, ElkObjectUnionOfVisitor<O>,
		ElkPropertyRestrictionVisitor<O> {

	// combined visitor
}
