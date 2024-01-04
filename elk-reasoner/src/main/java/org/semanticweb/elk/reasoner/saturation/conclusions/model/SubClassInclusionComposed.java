
package org.semanticweb.elk.reasoner.saturation.conclusions.model;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;



/**
 * A {@link SubClassInclusion} in which super-class was composed from other
 * class expressions.<br>
 * 
 * Notation:
 * 
 * <pre>
 * [C] ⊑ +D
 * </pre>
 * 
 * It is logically equivalent to axiom {@code SubClassInclusion(C D)}<br>
 * 
 * The parameters can be obtained as follows:<br>
 * 
 * C = {@link #getDestination()}<br>
 * D = {@link #getSubsumer()}<br>
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
public interface SubClassInclusionComposed extends SubClassInclusion {

	public static final String NAME = "Composed Subsumer";

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory {

		SubClassInclusionComposed getSubClassInclusionComposed(
				IndexedContextRoot destination,
				IndexedClassExpression subsumer);

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

		public O visit(SubClassInclusionComposed conclusion);

	}

}
