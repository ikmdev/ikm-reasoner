
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;



/**
 * A {@link ClassInference} producing a {@link BackwardLink} conclusion
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface BackwardLinkInference extends ClassInference {

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
			extends BackwardLinkComposition.Visitor<O>,
			BackwardLinkOfObjectHasSelf.Visitor<O>,
			BackwardLinkOfObjectSomeValuesFrom.Visitor<O>,
			BackwardLinkReversedExpanded.Visitor<O> {

		// combined interface

	}

}
