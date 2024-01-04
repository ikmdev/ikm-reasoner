
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * An abstract interface for defining how reasoner stages are executed by the
 * reasoner. For example, a reasoner may issue log messages before and after
 * execution, measure benchmarking information, or restart the stage in case it
 * has been interrupted.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface ReasonerStageExecutor {

	/**
	 * Makes sure that the given stage is completed; it might not necessarily
	 * execute this stage if the results are already computed
	 * 
	 * @param stage
	 *            the reasoner stage to be completed
	 * @throws ElkException
	 *             if the stage cannot be competed successfully
	 */
	public void complete(ReasonerStage stage) throws ElkException;

}
