
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;

/**
 * A {@link ClassConclusion} representing an inclusion between an axiom and an
 * existential restriction.<br>
 * 
 * Notation:
 * 
 * <pre>
 * C ⊑ <∃P>.[D]
 * </pre>
 * 
 * It is logically equivalent to axiom
 * {@code SubClassOf(C ObjectSomeValuesFrom(P D))}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getSource()}<br>
 * P = {@link #getRelation()}<br>
 * D = {@link #getDestination()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface BackwardLink extends SubClassConclusion {

	public static final String NAME = "Backward Link";

	/**
	 * @return the property expression of the existential relation between
	 *         {@link #getSource()} and {@link #getDestination()}
	 */
	public IndexedObjectProperty getRelation();

	/**
	 * @return the representation of the concept from which the existential
	 *         restriction corresponding to this {@link BackwardLink} follows
	 */
	public IndexedContextRoot getSource();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		BackwardLink getBackwardLink(IndexedContextRoot destination,
				IndexedObjectProperty relation, IndexedContextRoot source);

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

		public O visit(BackwardLink conclusion);

	}

}
