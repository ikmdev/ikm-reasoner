
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;



/**
 * A {@link ClassInference} producing a {@link DisjointSubsumer} conclusion
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface DisjointSubsumerInference extends ClassInference {

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
				DisjointSubsumerFromSubsumer.Visitor<O> {

		// combined interface

	}

}
