
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;

/**
 * A {@link ObjectPropertyConclusion} representing a derived object property
 * range axiom.<br>
 * 
 * Notation:
 * 
 * <pre>
 * Range(R,C)
 * </pre>
 * 
 * It is logically equivalent to axiom {@code ObjectPropertyRange(R C)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * R = {@link #getProperty()}<br>
 * C = {@link #getRange()}<br>
 * 
 * @author Yevgeny Kazakov
 */
public interface PropertyRange extends ObjectPropertyConclusion {

	public IndexedObjectProperty getProperty();

	public IndexedClassExpression getRange();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		PropertyRange getPropertyRange(IndexedObjectProperty property,
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

		public O visit(PropertyRange conclusion);

	}

}
