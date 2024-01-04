
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.util.collections.chains.Link;

/**
 * A {@link SubsumerRule} that is linked to other such
 * {@link LinkedSubsumerRule}s, thus representing a chain of
 * {@link SubsumerRule}s
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public interface LinkedSubsumerRule extends
		SubsumerRule<IndexedClassExpression>, Link<LinkedSubsumerRule> {

	public void accept(LinkedSubsumerRuleVisitor<?> visitor,
			IndexedClassExpression premise, ContextPremises premises,
			ClassInferenceProducer producer);

}
