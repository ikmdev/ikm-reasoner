
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A decomposition rules for {@link SubClassInclusion}s. The rule typically does not
 * depend on the other {@link ClassConclusion}s stored in the {@link Context}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <P>
 */
public interface SubsumerDecompositionRule<P extends IndexedClassExpression>
		extends SubsumerRule<P> {

	public void accept(SubsumerDecompositionRuleVisitor<?> visitor, P premise,
			ContextPremises premises, ClassInferenceProducer producer);

}
