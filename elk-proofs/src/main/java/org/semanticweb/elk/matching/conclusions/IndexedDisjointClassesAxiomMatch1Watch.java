
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.inferences.DisjointSubsumerFromSubsumerMatch1;
import org.semanticweb.elk.matching.inferences.InferenceMatch;



public interface IndexedDisjointClassesAxiomMatch1Watch extends InferenceMatch {

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> extends DisjointSubsumerFromSubsumerMatch1.Visitor<O> {

		// combined interface

	}

}
