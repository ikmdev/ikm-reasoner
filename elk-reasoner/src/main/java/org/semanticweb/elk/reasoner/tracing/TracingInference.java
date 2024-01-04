
package org.semanticweb.elk.reasoner.tracing;

import org.liveontologies.puli.Inference;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiomInference;
import org.semanticweb.elk.reasoner.saturation.inferences.SaturationInference;



/**
 * An operation producing {@link Conclusion}s from other {@link Conclusion}s
 * called premises using some inference rule.
 * 
 * @author Yevgeny Kazakov
 *
 */
public interface TracingInference extends Inference<Conclusion> {

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
				IndexedAxiomInference.Visitor<O>,
				SaturationInference.Visitor<O> {

		// combined interface

	}

}
