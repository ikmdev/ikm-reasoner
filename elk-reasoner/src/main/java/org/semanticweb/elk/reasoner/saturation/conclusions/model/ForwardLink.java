
package org.semanticweb.elk.reasoner.saturation.conclusions.model;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;

/**
 * A {@link ClassConclusion} representing an inclusion between an axiom and an
 * existential restriction.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [C] ⊑ <∃P>.D
 * </pre>
 * 
 * It is logically equivalent to axiom
 * {@code SubClassOf(C ObjectSomeValuesFrom(P D))}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * P = {@link #getChain()}<br>
 * D = {@link #getTarget()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ForwardLink extends ClassConclusion {

	public static final String NAME = "Forward Link";

	/**
	 * @return the {@link IndexedPropertyChain} in the existential restriction
	 *         corresponding to this {@link ForwardLink}
	 */
	public IndexedPropertyChain getChain();

	/**
	 * @return the {@link IndexedContextRoot} corresponding to the filler of the
	 *         existential restriction corresponding to this {@link ForwardLink}
	 */
	public IndexedContextRoot getTarget();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		ForwardLink getForwardLink(IndexedContextRoot destination,
				IndexedPropertyChain relation, IndexedContextRoot target);

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

		public O visit(ForwardLink conclusion);

	}

}
