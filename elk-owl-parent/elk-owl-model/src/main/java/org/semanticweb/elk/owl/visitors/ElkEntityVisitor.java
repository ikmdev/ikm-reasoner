
/**
 * @author Yevgeny Kazakov, Jul 3, 2011
 */
package org.semanticweb.elk.owl.visitors;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Visitor pattern interface for instances of {@link ElkEntity}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <O>
 *            the type of the output of this visitor
 */
public interface ElkEntityVisitor<O> extends ElkAnnotationPropertyVisitor<O>,
		ElkClassVisitor<O>, ElkDataPropertyVisitor<O>, ElkDatatypeVisitor<O>,
		ElkNamedIndividualVisitor<O>, ElkObjectPropertyVisitor<O> {

	// combined visitor

}
