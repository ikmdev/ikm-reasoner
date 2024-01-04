
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectSomeValuesFrom;

/**
 * An {@link IndexedContextRoot} constructed from an
 * {@link IndexedObjectProperty} and an {@link IndexedClassExpression}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * C ⊓ ∃R-
 * </pre>
 * 
 * It is logically equivalent to an OWL class expression
 * {@code ObjectIntersectionOf(C ObjectSomeValuesFrom(ObjectInverseOf(R) owl:Thing))}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getFiller()}<br>
 * R = {@link #getProperty()}<br>
 * 
 * @see IndexedObjectSomeValuesFrom#getRangeFiller()
 * 
 */
public interface IndexedRangeFiller extends IndexedContextRoot {

	/**
	 * @return The representation of the {@link ElkObjectProperty} which range
	 *         this {@link IndexedRangeFiller} subsumes. It is the property of
	 *         the {@link ElkObjectSomeValuesFrom} corresponding to this
	 *         {@link IndexedRangeFiller}.
	 */
	IndexedObjectProperty getProperty();

	/**
	 * @return The representation of the {@link ElkClassExpression} which this
	 *         {@link IndexedRangeFiller} subsumes. It is the filler of the
	 *         {@link ElkObjectSomeValuesFrom} corresponding to this
	 *         {@link IndexedRangeFiller}.
	 */
	IndexedClassExpression getFiller();

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedRangeFiller element);

	}

}
