
package org.semanticweb.elk.reasoner.tracing;

import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;



/**
 * Objects that can be produced by inferences and be premises of inferences
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface Conclusion {

	public <O> O accept(Visitor<O> visitor);

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
				IndexedAxiom.Visitor<O>,
				SaturationConclusion.Visitor<O> {

		// combined interface

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	interface Factory
			extends
				IndexedAxiom.Factory,
				SaturationConclusion.Factory {

		// combined interface

	}

}
