
package org.semanticweb.elk.reasoner.stages;

import org.semanticweb.elk.exceptions.ElkException;
import org.semanticweb.elk.util.logging.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link ReasonerStageExecutor} which prints log messages about the executed
 * stages. If a stage has not been done, first, all its dependencies are
 * executed, and then this stage itself.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class LoggingStageExecutor extends AbstractStageExecutor {

	// logger for this class
	protected static final Logger LOGGER_ = LoggerFactory
			.getLogger(LoggingStageExecutor.class);

	@Override
	public void execute(ReasonerStage stage) throws ElkException {
		LOGGER_.debug("{} stage:", stage);

		Statistics.logOperationStart(stage.getName(), LOGGER_);

		try {
			stage.preExecute();
			stage.execute();
			stage.printInfo();
			stage.postExecute();
		} catch (ElkInterruptedException e) {
			LOGGER_.debug("{} was interrupted.", stage);
			stage.printInfo();
			throw e;
		} finally {
			Statistics.logOperationFinish(stage.getName(), LOGGER_);
			Statistics.logMemoryUsage(LOGGER_);
		}

		LOGGER_.debug("{}: done.", stage);
	}
}
