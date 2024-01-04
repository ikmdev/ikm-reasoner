
package org.semanticweb.elk.reasoner.saturation.properties.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubPropertyChain;



/**
 * An {@link ObjectPropertyInference} that produces {@link SubPropertyChain}
 * conclusions
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface SubPropertyChainInference extends ObjectPropertyInference {

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
				SubPropertyChainExpandedSubObjectPropertyOf.Visitor<O>,
				SubPropertyChainTautology.Visitor<O> {

		// combined interface

	}

}
