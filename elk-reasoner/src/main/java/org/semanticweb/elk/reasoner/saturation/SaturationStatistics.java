
package org.semanticweb.elk.reasoner.saturation;



import org.semanticweb.elk.reasoner.incremental.IncrementalProcessingStatistics;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ClassConclusionStatistics;
import org.semanticweb.elk.reasoner.saturation.context.ContextStatistics;
import org.semanticweb.elk.reasoner.saturation.rules.RuleStatistics;
import org.semanticweb.elk.util.logging.LogLevel;
import org.slf4j.Logger;

/**
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class SaturationStatistics {

	private final ClassConclusionStatistics conclusionsStatistics_ = new ClassConclusionStatistics();

	private final RuleStatistics ruleStatistics_ = new RuleStatistics();

	private final ContextStatistics contextStatistics_ = new ContextStatistics();

	private final IncrementalProcessingStatistics processingStatistics_ = new IncrementalProcessingStatistics();

	public void startMeasurements() {
		conclusionsStatistics_.startMeasurements();
		ruleStatistics_.startMeasurements();
		conclusionsStatistics_.startMeasurements();
		processingStatistics_.startMeasurements();
	}

	public void reset() {
		conclusionsStatistics_.reset();
		ruleStatistics_.reset();
		contextStatistics_.reset();
		processingStatistics_.reset();
	}

	public synchronized void add(SaturationStatistics statistics) {
		this.conclusionsStatistics_.add(statistics.conclusionsStatistics_);
		this.ruleStatistics_.add(statistics.ruleStatistics_);
		this.contextStatistics_.add(statistics.contextStatistics_);
		this.processingStatistics_.add(statistics.processingStatistics_);
	}

	public void check(Logger logger) {
		contextStatistics_.check(logger);
		conclusionsStatistics_.check(logger);
	}

	public void print(Logger logger) {
		if (!logger.isDebugEnabled()) {
			return;
		}

		conclusionsStatistics_.print(logger);
		ruleStatistics_.print(logger);
		contextStatistics_.print(logger, LogLevel.DEBUG);
		processingStatistics_.print(logger, LogLevel.DEBUG);
	}

	public RuleStatistics getRuleStatistics() {
		return ruleStatistics_;
	}

	public ClassConclusionStatistics getConclusionStatistics() {
		return conclusionsStatistics_;
	}

	public ContextStatistics getContextStatistics() {
		return contextStatistics_;
	}

	public IncrementalProcessingStatistics getIncrementalProcessingStatistics() {
		return processingStatistics_;
	}
}
