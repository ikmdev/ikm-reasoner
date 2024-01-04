
package org.semanticweb.elk.reasoner.saturation.rules.factories;

import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.reasoner.saturation.SaturationUtils;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ClassConclusionTracingContextUnsaturationVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ContextInitializingClassConclusionInsertionVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.RuleApplicationClassConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference.Visitor;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInferenceConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A {@link RuleApplicationFactory} that works similarly to
 * {@link RuleApplicationAdditionFactory} except for marking the source
 * {@link Context} of the produced {@link ClassConclusion}s as not saturated.
 * 
 * @author Yevgeny Kazakov
 * @author Pavel Klinov
 */
public class RuleApplicationAdditionUnSaturationFactory
		extends
			RuleApplicationAdditionFactory<RuleApplicationInput> {

	public RuleApplicationAdditionUnSaturationFactory(
			final InterruptMonitor interrupter,
			SaturationState<? extends Context> saturationState) {
		super(interrupter, saturationState);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Visitor<Boolean> getInferenceProcessor(
			Reference<Context> activeContext, RuleVisitor<?> ruleVisitor,
			SaturationStateWriter<? extends Context> writer,
			SaturationStatistics localStatistics) {
		// the visitor used for inserting conclusion
		return new ClassInferenceConclusionVisitor<Boolean>(
				// measuring time, if necessary
				SaturationUtils.getTimedConclusionVisitor(
						SaturationUtils.compose(SaturationUtils.compose(
								// count processed conclusions, if necessary
								SaturationUtils
										.getClassInferenceCountingVisitor(
												localStatistics),
								// insert conclusions initializing contexts if
								// necessary
								new ContextInitializingClassConclusionInsertionVisitor(
										activeContext, writer),
								// if new, mark the source context as
								// unsaturated
								new ClassConclusionTracingContextUnsaturationVisitor(
										writer),
								// count conclusions used in the rules, if
								// necessary
								SaturationUtils
										.getClassConclusionCountingVisitor(
												localStatistics),
								// apply rules
								new RuleApplicationClassConclusionVisitor(
										activeContext,
										getSaturationState().getOntologyIndex()
												.getContextInitRuleHead(),
										ruleVisitor, writer))),
						localStatistics));
	}
}
