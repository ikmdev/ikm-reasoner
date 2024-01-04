
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;



/**
 * A {@link ClassInference} that produces {@link ClassInconsistency}s
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ClassInconsistencyInference extends ClassInference {

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
				ClassInconsistencyOfOwlNothing.Visitor<O>,
				ClassInconsistencyOfDisjointSubsumers.Visitor<O>,
				ClassInconsistencyOfObjectComplementOf.Visitor<O>,
				ClassInconsistencyPropagated.Visitor<O> {

		// combined interface

	}

}
