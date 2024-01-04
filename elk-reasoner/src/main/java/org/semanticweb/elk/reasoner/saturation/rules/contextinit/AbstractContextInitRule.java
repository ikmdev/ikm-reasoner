 
package org.semanticweb.elk.reasoner.saturation.rules.contextinit;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link ContextInitRule}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractContextInitRule
		extends AbstractRule<ContextInitialization> implements ContextInitRule {

	@Override
	public void accept(RuleVisitor<?> visitor, ContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((ContextInitRuleVisitor<?>) visitor, premise, premises, producer);
	}

}
