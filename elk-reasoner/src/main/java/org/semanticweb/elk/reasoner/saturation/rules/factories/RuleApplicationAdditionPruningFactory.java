/*
 * #%L
 * ELK Reasoner
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2011 - 2013 Department of Computer Science, University of Oxford
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.semanticweb.elk.reasoner.saturation.rules.factories;

import org.semanticweb.elk.Reference;
import org.semanticweb.elk.reasoner.saturation.ExtendedContext;
import org.semanticweb.elk.reasoner.saturation.MainContextFactory;
import org.semanticweb.elk.reasoner.saturation.MapSaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.SaturationStateWriter;
import org.semanticweb.elk.reasoner.saturation.SaturationStatistics;
import org.semanticweb.elk.reasoner.saturation.SaturationUtils;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ClassConclusionInsertionVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.ClassConclusionOccurrenceCheckingVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.RelativizedContextReference;
import org.semanticweb.elk.reasoner.saturation.conclusions.classes.TracingRuleApplicationClassConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInference;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInferenceConclusionVisitor;
import org.semanticweb.elk.reasoner.saturation.inferences.DummyClassInferenceVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.Rule;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.util.concurrent.computation.InterruptMonitor;

/**
 * A {@link RuleApplicationFactory} that applies rules to
 * {@link ClassConclusion}s for which the {@link Context} with
 * {@link Context#getRoot()} that is equal
 * {@link ClassConclusion#getTraceRoot()} is not saturated according to the
 * {@link SaturationState}. To identify all such {@link ClassConclusion}s, the
 * conclusion of not saturated contexts are re-derived from the beginning (from
 * the corresponding {@link ContextInitialization} inferences) by applying only
 * tracing rules, i.e.,the {@link Rule}s for which {@link Rule#isTracingRule()}
 * returns {@code true}.
 * 
 * The produced {@link ClassInference}s whose conclusions do not appear in the
 * {@link SaturationState} are added to the queue of the respective
 * {@link Context} and can be later obtained by {@link Context#takeToDo()}. The
 * content of the {@link Context}s is otherwise not modified. To make sure that
 * all rules are applied, the {@link ClassConclusion}s stored in
 * {@link Context}s should be reachable from such {@link Context}s by applying
 * tracing rules.
 * 
 * @author "Yevgeny Kazakov"
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 */
public class RuleApplicationAdditionPruningFactory extends
		AbstractRuleApplicationFactory<ExtendedContext, RuleApplicationInput> {

	private final SaturationState<? extends Context> mainSaturationState_;

	public RuleApplicationAdditionPruningFactory(
			final InterruptMonitor interrupter,
			SaturationState<? extends Context> mainSaturationState) {
		/**
		 * We use a "local" {@link SaturationState} to iterate over
		 * {@link Conclusion}s stored within {@link Context}s of the main
		 * {@link SaturationState}. Iteration is done by applying all rules. We
		 * create (local) copies of the {@link Context}s in the main
		 * {@link SaturationState} to keep track of {@link Context}s to which
		 * the rules are already applied.
		 */
		super(interrupter,
				new MapSaturationState<ExtendedContext>(
						mainSaturationState.getOntologyIndex(),
						new MainContextFactory()));
		this.mainSaturationState_ = mainSaturationState;
	}

	@Override
	protected ClassInference.Visitor<Boolean> getInferenceProcessor(
			Reference<Context> activeContext, RuleVisitor<?> ruleVisitor,
			SaturationStateWriter<? extends ExtendedContext> localWriter,
			SaturationStatistics localStatistics) {
		// keep a reference to the processed inference
		CurrentClassInference inferenceRef = new CurrentClassInference();
		return SaturationUtils.compose(inferenceRef,
				new ClassInferenceConclusionVisitor<Boolean>(
						// measuring time, if necessary
						SaturationUtils.getTimedConclusionVisitor(
								SaturationUtils.compose(
										// count processed conclusions, if
										// necessary
										SaturationUtils
												.getClassInferenceCountingVisitor(
														localStatistics),
										// check if the conclusion appears in
										// the main saturation state
										new ClassConclusionOccurrenceCheckingVisitor(
												new RelativizedContextReference(
														activeContext,
														mainSaturationState_)) {
											@Override
											protected Boolean defaultVisit(
													ClassConclusion conclusion) {
												if (super.defaultVisit(
														conclusion)) {
													return true;
												}
												// else the inference should be
												// added to the main saturation
												mainSaturationState_
														.getContextCreatingWriter()
														.produce(inferenceRef
																.get());
												return false;
											}
										},
										// add the conclusion locally
										new ClassConclusionInsertionVisitor(
												localWriter),
										// if new, update conclusion counters,
										// if necessary
										SaturationUtils
												.getClassConclusionCountingVisitor(
														localStatistics),
										// and apply the rules
										new TracingRuleApplicationClassConclusionVisitor(
												mainSaturationState_,
												activeContext, ruleVisitor,
												localWriter)),
								localStatistics)));
	}

	public static class CurrentClassInference
			extends DummyClassInferenceVisitor<Boolean>
			implements Reference<ClassInference> {

		ClassInference visitedInference_;

		@Override
		protected Boolean defaultVisit(ClassInference inference) {
			this.visitedInference_ = inference;
			return true;
		}

		@Override
		public ClassInference get() {
			return visitedInference_;
		}

	}

}
