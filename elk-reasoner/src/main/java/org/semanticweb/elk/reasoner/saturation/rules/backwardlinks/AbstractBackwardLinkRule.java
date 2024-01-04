
package org.semanticweb.elk.reasoner.saturation.rules.backwardlinks;


import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link BackwardLinkRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractBackwardLinkRule extends AbstractRule<BackwardLink>
		implements BackwardLinkRule {

	@Override
	public void accept(RuleVisitor<?> visitor, BackwardLink premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((BackwardLinkRuleVisitor<?>) visitor, premise, premises, producer);
	}

}
