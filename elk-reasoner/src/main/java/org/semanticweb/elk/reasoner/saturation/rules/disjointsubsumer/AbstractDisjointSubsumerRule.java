
package org.semanticweb.elk.reasoner.saturation.rules.disjointsubsumer;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.DisjointSubsumer;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link DisjointSubsumerRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractDisjointSubsumerRule
		extends AbstractRule<DisjointSubsumer> implements DisjointSubsumerRule {

	@Override
	public void accept(RuleVisitor<?> visitor, DisjointSubsumer premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((DisjointSubsumerRuleVisitor<?>) visitor, premise, premises,
				producer);
	}

}
