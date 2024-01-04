
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfDisjointSubsumersMatch1;
import org.semanticweb.elk.matching.inferences.ClassInconsistencyOfDisjointSubsumersMatch2;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface DisjointSubsumerMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O>
			extends ClassInconsistencyOfDisjointSubsumersMatch1.Visitor<O>,
			ClassInconsistencyOfDisjointSubsumersMatch2.Visitor<O> {

		// combined interface

	}

}
