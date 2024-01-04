
package org.semanticweb.elk.reasoner.saturation.conclusions.model;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;
import org.semanticweb.elk.reasoner.saturation.rules.Rule;



/**
 * A {@link SaturationConclusion} that represents a derived class axiom produced
 * by {@link ClassInference}s. {@link ClassConclusion}s are stored in
 * {@link ContextPremises} to which {@link Rule}s are applied producing
 * {@link ClassInference}s.
 * 
 * @see Rule
 * @see ClassInference
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ClassConclusion extends SaturationConclusion {

	/**
	 * @return the root of the {@link Context} in which this conclusion should
	 *         participate in inferences; it cannot be {@code null}.
	 */
	public IndexedContextRoot getDestination();

	/**
	 * 
	 * @return the {@link IndexedContextRoot} that identifies this inference for
	 *         tracing; every {@link ClassInference} that produces this
	 *         {@link ClassConclusion} should be have the same value of
	 *         {@link ClassInference#getTraceRoot()}
	 * 
	 * @see ClassInference#getTraceRoot()
	 */
	public IndexedContextRoot getTraceRoot();

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				ContextInitialization.Factory,
				ClassInconsistency.Factory,
				DisjointSubsumer.Factory,
				InitializationConclusion.Factory,
				ForwardLink.Factory,
				SubClassConclusion.Factory,
				SubClassInclusion.Factory {

		// combined interface

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends
				ContextInitialization.Visitor<O>,
				ClassInconsistency.Visitor<O>,
				DisjointSubsumer.Visitor<O>,
				InitializationConclusion.Visitor<O>,
				ForwardLink.Visitor<O>,
				SubClassConclusion.Visitor<O>,
				SubClassInclusion.Visitor<O> {

		// combined interface

	}

}
