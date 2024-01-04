
package org.semanticweb.elk.reasoner.saturation.rules.backwardlinks;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.util.collections.chains.ModifiableLinkImpl;

/**
 * A skeleton implementation of {@link LinkableBackwardLinkRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractLinkableBackwardLinkRule extends
		ModifiableLinkImpl<LinkableBackwardLinkRule> implements
		LinkableBackwardLinkRule {

	AbstractLinkableBackwardLinkRule(LinkableBackwardLinkRule tail) {
		super(tail);
	}

	@Override
	public void applyTracing(BackwardLink premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		// by default apply normally
		apply(premise, premises, producer);
	}
	
	@Override
	public void accept(RuleVisitor<?> visitor, BackwardLink premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((BackwardLinkRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

	@Override
	public void accept(BackwardLinkRuleVisitor<?> visitor,
			BackwardLink premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		accept((LinkedBackwardLinkRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

}
