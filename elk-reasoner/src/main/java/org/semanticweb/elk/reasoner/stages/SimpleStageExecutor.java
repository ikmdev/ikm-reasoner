 
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * A simple {@link ReasonerStageExecutor}. If a stage has not been done, first,
 * all its dependencies are executed, and then this stage itself.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class SimpleStageExecutor extends AbstractStageExecutor {

	@Override
	public void execute(ReasonerStage stage) throws ElkException {
		stage.preExecute();
		stage.execute();
		stage.postExecute();
	}
}
