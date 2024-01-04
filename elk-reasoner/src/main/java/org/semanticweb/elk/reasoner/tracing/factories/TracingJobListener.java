
package org.semanticweb.elk.reasoner.tracing.factories;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;
import org.semanticweb.elk.reasoner.tracing.ModifiableTracingProof;

public interface TracingJobListener {

	/**
	 * called when tracing of the {@link IndexedContextRoot} finished producing
	 * the inferences in the {@link ModifiableTracingProof}.
	 * 
	 * @param context
	 * @param proof
	 */
	public void notifyJobFinished(final IndexedContextRoot context,
			ModifiableTracingProof<ClassInference> proof);

}
