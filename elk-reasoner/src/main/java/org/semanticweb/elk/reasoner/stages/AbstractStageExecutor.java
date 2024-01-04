
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;

/**
 * An abstract base class which implements a very simple logic of executing
 * stages: first check the completion flag, if false - first complete all
 * dependencies, and finally execute the stage.
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * @author Peter Skocovsky
 */
public abstract class AbstractStageExecutor implements ReasonerStageExecutor {

	@Override
	public void complete(ReasonerStage stage) throws ElkException {
		if (!stage.isCompleted()) {

			// TODO: avoid recursive call, use accumulator
			for (ReasonerStage dependentStage : stage.getPreStages()) {
				complete(dependentStage);
			}
			execute(stage);
		}

	}

	protected abstract void execute(ReasonerStage stage) throws ElkException;
}
