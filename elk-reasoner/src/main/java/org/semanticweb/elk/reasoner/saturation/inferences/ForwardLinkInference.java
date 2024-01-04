
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;



/**
 * A {@link ClassInference} producing a {@link ForwardLink} conclusion
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface ForwardLinkInference extends ClassInference {

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
				ForwardLinkComposition.Visitor<O>,
				ForwardLinkOfObjectHasSelf.Visitor<O>,
				ForwardLinkOfObjectSomeValuesFrom.Visitor<O> {

		// combined interface

	}

}
