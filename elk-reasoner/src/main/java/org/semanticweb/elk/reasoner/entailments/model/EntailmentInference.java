
package org.semanticweb.elk.reasoner.entailments.model;

import org.liveontologies.puli.Inference;

/**
 * Instances of this interface explain how was some {@link Entailment} entailed.
 * This {@link Entailment} can be retrieved from {@link #getConclusion()}. If it
 * was entailed from other entailments, they can be obtained from
 * {@link #getPremises()}.
 * 
 * @author Peter Skocovsky
 */
public interface EntailmentInference extends Inference<Entailment> {

	<O> O accept(Visitor<O> visitor);

	public static interface Visitor<O>
			extends AxiomEntailmentInference.Visitor<O>,
			OntologyInconsistencyEntailmentInference.Visitor<O> {
		// combined interface
	}

}
