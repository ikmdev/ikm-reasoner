
package org.semanticweb.elk.reasoner.saturation.rules.factories;

import org.semanticweb.elk.ModifiableReference;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;
import org.semanticweb.elk.reasoner.saturation.inferences.ContextInitializationNoPremises;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * An {@link AbstractRuleEngine} which produces {@link ClassInference}s and
 * retrieves active {@link Context}s using the provided
 * {@link SaturationStateWriter}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <I> 
 */
public class BasicRuleEngine<I extends RuleApplicationInput>
		extends
			AbstractRuleEngineWithStatistics<I> {

	/**
	 * a {@link SaturationStateWriter} to produce new {@link ClassInference}s
	 * and query for active {@link Context}s
	 */
	private final SaturationStateWriter<?> writer_;

	protected BasicRuleEngine(ModifiableReference<Context> activeContext,
			ClassInference.Visitor<Boolean> inferenceProcessor,
			WorkerLocalTodo localTodo, InterruptMonitor interrupter,
			SaturationStateWriter<?> writer,
			SaturationStatistics aggregatedStatistics,
			SaturationStatistics localStatistics) {
		super(activeContext, inferenceProcessor, localTodo, interrupter,
				aggregatedStatistics, localStatistics);
		this.writer_ = writer;
	}

	@Override
	public void submit(RuleApplicationInput job) {
		writer_.produce(new ContextInitializationNoPremises(job.getRoot()));
	}

	@Override
	protected Context getNextActiveContext() {
		return writer_.pollForActiveContext();
	}

	protected final SaturationStateWriter<?> getWriter() {
		return writer_;
	}
}
