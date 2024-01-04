
package org.semanticweb.elk.reasoner.saturation.conclusions.model;


/**
 * The interface for objects representing object property inferences. Used
 * primarily for tracing.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public interface ObjectPropertyConclusion extends SaturationConclusion {

	public <O> O accept(Visitor<O> visitor);

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory extends PropertyRange.Factory, SubPropertyChain.Factory {

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
				PropertyRange.Visitor<O>,
				SubPropertyChain.Visitor<O> {

		// combined interface

	}

}
