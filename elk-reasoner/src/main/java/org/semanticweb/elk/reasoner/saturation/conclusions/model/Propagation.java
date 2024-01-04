
package org.semanticweb.elk.reasoner.saturation.conclusions.model;


import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;

/**
 * A {@link ClassConclusion} representing a concept inclusion axiom between two
 * existential restrictions.<br>
 * 
 * Notation:
 * 
 * <pre>
 * ∃[R].[C] ⊑ ∃S.D
 * </pre>
 * 
 * It is logically equivalent to axiom
 * {@code SubClassOf(ObjectSomeValuesFrom(R C) ObjectSomeValuesFrom(S D))}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * R = {@link #getSubDestination()}<br>
 * ∃S.D = {@link #getCarry()} (from which S and D can be obtained)<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface Propagation extends SubClassConclusion {

	public static final String NAME = "Propagation";

	/**
	 * @return the {@link IndexedObjectProperty} that is the relation over which
	 *         this {@link Propagation} is applied
	 */
	public IndexedObjectProperty getRelation();

	/**
	 * @return the {@link IndexedObjectSomeValuesFrom} that is propagated by
	 *         this {@link Propagation}
	 */
	public IndexedObjectSomeValuesFrom getCarry();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		Propagation getPropagation(IndexedContextRoot destination,
				IndexedObjectProperty relation,
				IndexedObjectSomeValuesFrom carry);

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

		public O visit(Propagation conclusion);

	}

}
