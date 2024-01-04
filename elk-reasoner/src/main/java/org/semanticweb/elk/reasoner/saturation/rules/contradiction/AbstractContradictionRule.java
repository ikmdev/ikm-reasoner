
package org.semanticweb.elk.reasoner.saturation.rules.contradiction;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link ContradictionRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractContradictionRule
		extends AbstractRule<ClassInconsistency> implements ContradictionRule {

	@Override
	public void accept(RuleVisitor<?> visitor, ClassInconsistency premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((ContradictionRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

}
