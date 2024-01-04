
package org.semanticweb.elk.reasoner.saturation.rules.propagations;



import java.util.Map;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.context.SubContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link PropagationRule} producing {@link SubClassInclusion}s in the source
 * {@link Context}s of relevant non-reflexive {@link BackwardLink}s stored in
 * the {@link ContextPremises} with which this rule applies.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class SubsumerPropagationRule extends AbstractPropagationRule {

	public static final String NAME = "Propagation";

	private static final SubsumerPropagationRule INSTANCE_ = new SubsumerPropagationRule();

	public static final SubsumerPropagationRule getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(Propagation premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		final Map<IndexedObjectProperty, ? extends SubContextPremises> subContextMap = premises
				.getSubContextPremisesByObjectProperty();
		IndexedObjectProperty subRoot = premise.getSubDestination();
		SubContextPremises targets = subContextMap.get(subRoot);
		for (IndexedContextRoot target : targets.getLinkedRoots()) {
			producer.produce(new SubClassInclusionComposedObjectSomeValuesFrom(premise, target));
		}
		if (premises.getLocalReflexiveObjectProperties().contains(subRoot)) {
			producer.produce(new SubClassInclusionComposedObjectSomeValuesFrom(premise, premises.getRoot()));
		}
	}

	@Override
	public boolean isTracingRule() {
		return false;
	}

	@Override
	public void accept(PropagationRuleVisitor<?> visitor, Propagation premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
