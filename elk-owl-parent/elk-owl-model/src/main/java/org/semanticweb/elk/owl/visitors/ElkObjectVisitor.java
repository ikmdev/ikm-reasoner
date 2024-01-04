
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkObject;

/**
 * Visitor pattern interface for instances of {@link ElkObject}.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * @author Frantisek Simancik
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkObjectVisitor<O> extends ElkAxiomVisitor<O>,
		ElkClassExpressionVisitor<O>, ElkSubObjectPropertyExpressionVisitor<O>,
		ElkDataPropertyExpressionVisitor<O>, ElkIndividualVisitor<O>,
		ElkLiteralVisitor<O>, ElkEntityVisitor<O>, ElkDataRangeVisitor<O>,
		ElkFacetRestrictionVisitor<O>, ElkAnnotationVisitor<O>,
		ElkAnnotationSubjectVisitor<O>, ElkAnnotationValueVisitor<O> {

	// combined visitor
}
