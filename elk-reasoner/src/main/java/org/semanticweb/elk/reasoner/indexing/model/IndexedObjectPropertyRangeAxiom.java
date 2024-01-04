
package org.semanticweb.elk.reasoner.indexing.model;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;

/**
 * An {@link IndexedAxiom} constructed from an {@link IndexedObjectProperty} and
 * {@link IndexedClassExpression}.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [Range(R,C)]
 * </pre>
 * 
 * It is logically equivalent to the OWL axiom {@code ObjectPropertyRange(R C)}
 * <br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R = {@link #getProperty()}<br>
 * D = {@link #getRange()}<br>
 * 
 * @author "Yevgeny Kazakov"
 */
public interface IndexedObjectPropertyRangeAxiom extends IndexedAxiom {

	/**
	 * @return the {@link IndexedObjectProperty} representing the property of
	 *         the {@link ElkObjectPropertyRangeAxiom} represented by this
	 *         {@link IndexedObjectPropertyRangeAxiom}
	 * 
	 * @see ElkObjectPropertyRangeAxiom#getProperty()
	 */
	IndexedObjectProperty getProperty();

	/**
	 * @return the {@link IndexedClassExpression} representing the range of the
	 *         {@link ElkObjectPropertyRangeAxiom} represented by this
	 *         {@link IndexedObjectPropertyRangeAxiom}
	 * 
	 * @see ElkObjectPropertyRangeAxiom#getRange()
	 */
	IndexedClassExpression getRange();

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		IndexedObjectPropertyRangeAxiom getIndexedObjectPropertyRangeAxiom(
				ElkAxiom originalAxiom, IndexedObjectProperty property,
				IndexedClassExpression range);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedObjectPropertyRangeAxiom axiom);

	}

}
