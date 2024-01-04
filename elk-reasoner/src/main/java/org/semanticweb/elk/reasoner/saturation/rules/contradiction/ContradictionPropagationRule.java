
package org.semanticweb.elk.reasoner.saturation.rules.contradiction;



import java.util.Map;

import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.context.SubContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyPropagated;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link ContradictionRule} applied when processing {@link ClassInconsistency}
 * producing {@link ClassInconsistency} in all contexts linked by non-reflexive
 * {@link BackwardLink}s in the {@code ContextPremises} (i.e.,
 * {@link BackwardLink}s such that {@link BackwardLink#getTraceRoot()} is
 * different from {@link ContextPremises#getRoot()}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ContradictionPropagationRule extends AbstractContradictionRule {

	private static final ContradictionPropagationRule INSTANCE_ = new ContradictionPropagationRule();

	public static final String NAME = "Contradiction Propagation over Backward Links";

	private ContradictionPropagationRule() {
		// do not allow creation of instances outside of this class
	}

	public static ContradictionPropagationRule getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(ClassInconsistency premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		final Map<IndexedObjectProperty, ? extends SubContextPremises> subPremises = premises
				.getSubContextPremisesByObjectProperty();
		// no need to propagate over reflexive links
		for (IndexedObjectProperty propRelation : subPremises.keySet()) {
			for (IndexedContextRoot target : subPremises.get(propRelation)
					.getLinkedRoots()) {
				// producer.produce(target, premise);
				producer.produce(new ClassInconsistencyPropagated(
						premises.getRoot(), propRelation, target));
			}
		}
	}

	@Override
	public boolean isTracingRule() {
		return false;
	}

	@Override
	public void accept(ContradictionRuleVisitor<?> visitor,
			ClassInconsistency premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}