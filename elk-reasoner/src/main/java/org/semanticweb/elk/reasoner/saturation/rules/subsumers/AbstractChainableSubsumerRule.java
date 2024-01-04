
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.util.collections.chains.ModifiableLinkImpl;

/**
 * A skeleton implementation of {@link ChainableSubsumerRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractChainableSubsumerRule extends
		ModifiableLinkImpl<ChainableSubsumerRule> implements
		ChainableSubsumerRule {

	AbstractChainableSubsumerRule(ChainableSubsumerRule tail) {
		super(tail);
	}
	
	@Override
	public void applyTracing(IndexedClassExpression premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		// by default apply normally
		apply(premise, premises, producer);
	}

	@Override
	public void accept(RuleVisitor<?> visitor, IndexedClassExpression premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((SubsumerRuleVisitor<?>) visitor, premise, premises, producer);
	}

	@Override
	public void accept(SubsumerRuleVisitor<?> visitor,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		accept((LinkedSubsumerRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

}
