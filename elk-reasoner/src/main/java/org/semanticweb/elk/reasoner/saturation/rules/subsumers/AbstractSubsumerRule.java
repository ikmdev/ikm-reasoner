
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.AbstractRule;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;

/**
 * A skeleton implementation of {@link SubsumerRule}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
abstract class AbstractSubsumerRule<S extends IndexedClassExpression>
		extends AbstractRule<S> implements SubsumerRule<S> {

	@Override
	public void accept(RuleVisitor<?> visitor, S premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((SubsumerRuleVisitor<?>) visitor, premise, premises, producer);
	}

}
