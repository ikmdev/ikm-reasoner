
package org.semanticweb.elk.reasoner.saturation.rules.subcontextinit;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubContextInitialization;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link SubContextInitRule}
 * 
 * @author "Yevgeny Kazakov"
 */
abstract class AbstractSubContextInitRule extends AbstractRule<SubContextInitialization>
		implements SubContextInitRule {

	@Override
	public void accept(RuleVisitor<?> visitor, SubContextInitialization premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((SubContextInitRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

}
