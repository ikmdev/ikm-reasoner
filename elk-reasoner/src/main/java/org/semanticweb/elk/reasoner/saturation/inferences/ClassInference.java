
package org.semanticweb.elk.reasoner.saturation.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * A {@link SaturationInference} that produces {@link ClassConclusion}s
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 */
public interface ClassInference extends SaturationInference {

	/**
	 * @return the {@link IndexedContextRoot} of the {@link Context} where this
	 *         {@link ClassInference} was produced; {@code null} for
	 *         {@link InitializationInference}s.
	 */
	public IndexedContextRoot getOrigin();

	/**
	 * @return the {@link IndexedContextRoot} of the {@link Context} in which
	 *         the conclusion of this {@link ClassInference} should participate
	 *         in inferences. It cannot be {@code null}.
	 */
	public IndexedContextRoot getDestination();

	/**
	 * @return the {@link IndexedContextRoot}, which is the same as
	 *         {@link ClassConclusion#getTraceRoot()} for the conclusion of this
	 *         {@link ClassInference} and for some of the premises of this
	 *         {@link ClassInference}, if it has any (i.e., it is not an
	 *         {@link InitializationInference}). This value is used for tracing
	 *         of inferences. It cannot be {@code null}.
	 * 
	 * @see ClassConclusion#getTraceRoot()
	 */
	public IndexedContextRoot getTraceRoot();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * Visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public static interface Visitor<O>
			extends
				ClassInconsistencyInference.Visitor<O>,
				DisjointSubsumerInference.Visitor<O>,
				ForwardLinkInference.Visitor<O>,
				InitializationInference.Visitor<O>,
				LinkComposition.Visitor<O>,
				SubClassInference.Visitor<O>,
				SubClassInclusionInference.Visitor<O> {

		// combined interface

	}

}
