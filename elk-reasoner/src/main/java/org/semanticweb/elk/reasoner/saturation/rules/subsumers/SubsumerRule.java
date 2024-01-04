
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.Context;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.Rule;

/**
 * 
 * A {@link Rule} applied when processing {@link IndexedClassExpression}s of
 * {@link SubClassInclusion}s in a {@link Context}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <S> 
 */
public interface SubsumerRule<S extends IndexedClassExpression> extends Rule<S> {

	public void accept(SubsumerRuleVisitor<?> visitor, S subsumer,
			ContextPremises premises, ClassInferenceProducer producer);

}
