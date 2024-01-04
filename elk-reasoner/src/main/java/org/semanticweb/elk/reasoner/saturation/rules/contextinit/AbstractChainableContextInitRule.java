
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.ChainableSubsumerRule;
import org.semanticweb.elk.util.collections.chains.ModifiableLinkImpl;

/**
 * A skeleton implementation of {@link ChainableSubsumerRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractChainableContextInitRule extends
		ModifiableLinkImpl<ChainableContextInitRule> implements
		ChainableContextInitRule {

	AbstractChainableContextInitRule(ChainableContextInitRule tail) {
		super(tail);
	}
	
	@Override
	public void applyTracing(ContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		// by default apply normally
		apply(premise, premises, producer);
	}

	@Override
	public void accept(RuleVisitor<?> visitor, ContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((ContextInitRuleVisitor<?>) visitor, premise, premises, producer);
	}

	@Override
	public void accept(ContextInitRuleVisitor<?> visitor,
			ContextInitialization premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		accept((LinkedContextInitRuleVisitor<?>) visitor, premise, premises,
				producer);
	}
}
