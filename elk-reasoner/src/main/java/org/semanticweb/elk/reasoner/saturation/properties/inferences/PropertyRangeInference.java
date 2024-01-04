
package org.semanticweb.elk.reasoner.saturation.properties.inferences;

import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;



/**
 * An {@link ObjectPropertyInference} that produces {@link PropertyRange}
 * conclusions.
 * 
 * @author Yevgeny Kazakov
 */
public interface PropertyRangeInference extends ObjectPropertyInference {

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
				PropertyRangeInherited.Visitor<O> {

		// combined interface

	}

}
