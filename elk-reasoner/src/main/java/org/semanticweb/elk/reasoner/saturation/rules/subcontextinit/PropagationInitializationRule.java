
package org.semanticweb.elk.reasoner.saturation.rules.subcontextinit;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.Propagation;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.context.SubContext;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * 
 * A {@link SubContextInitRule} generating {@link Propagation}s when
 * initializing {@link SubContext}s
 * 
 * @author "Yevgeny Kazakov"
 */
public class PropagationInitializationRule extends AbstractSubContextInitRule {

	public static final String NAME = "Propagations For SubContext";

	private static final PropagationInitializationRule INSTANCE_ = new PropagationInitializationRule();

	public static PropagationInitializationRule getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(SubContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		IndexedObjectSomeValuesFrom.Helper.generatePropagations(
				premise.getSubDestination(), premises, producer);
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(SubContextInitRuleVisitor<?> visitor,
			SubContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
