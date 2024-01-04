
package org.semanticweb.elk.reasoner.reduction;

import org.semanticweb.elk.reasoner.ReasonerJob;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

/**
 * The type of the transitive reduction job. The input of the transitive
 * reduction job is an indexed class expression for which it is required to
 * check satisfiability, and if satisfiable, compute equivalent classes and
 * direct super-classes. This result is set as the output of the job.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <R>
 *            the type of the input for the transitive reduction job.
 */
public class TransitiveReductionJob<R extends IndexedClassExpression> extends
		ReasonerJob<R, TransitiveReductionOutput<R>> {

	public TransitiveReductionJob(R input) {
		super(input);
	}

	@Override
	protected void setOutput(TransitiveReductionOutput<R> output) {
		super.setOutput(output);
	}

}
