
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.ClassInconsistencyPropagatedMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface ClassInconsistencyMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends ClassInconsistencyPropagatedMatch2.Visitor<O> {

		// combined interface

	}

}
