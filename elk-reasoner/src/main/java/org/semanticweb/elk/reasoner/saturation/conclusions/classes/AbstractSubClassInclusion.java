
package org.semanticweb.elk.reasoner.saturation.conclusions.classes;

import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.reasoner.saturation.rules.RuleVisitor;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.LinkedSubsumerRule;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.SubsumerDecompositionVisitor;

/**
 * An implementation of {@link SubClassInclusion}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * 
 * @param <S>
 *            the type of the super-expression of {@link IndexedClassExpression}
 */
public abstract class AbstractSubClassInclusion<S extends IndexedClassExpression>
		extends AbstractClassConclusion implements SubClassInclusion {

	/**
	 * the implied {@code IndexedClassExpression} represented by this
	 * {@link SubClassInclusion}
	 */
	private final S expression_;

	protected AbstractSubClassInclusion(IndexedContextRoot subExpression,
			S superExpression) {
		super(subExpression);
		if (superExpression == null)
			throw new NullPointerException("Subsumer cannot be null!");
		this.expression_ = superExpression;
	}
	
	@Override
	public S getSubsumer() {
		return expression_;
	}

	void applyCompositionRules(RuleVisitor<?> ruleAppVisitor,
			ContextPremises premises, ClassInferenceProducer producer) {
		LinkedSubsumerRule compositionRule = expression_
				.getCompositionRuleHead();
		while (compositionRule != null) {
			compositionRule.accept(ruleAppVisitor, expression_, premises,
					producer);
			compositionRule = compositionRule.next();
		}
	}

	void applyDecompositionRules(RuleVisitor<?> ruleAppVisitor,
			ContextPremises premises, ClassInferenceProducer producer) {
		expression_.accept(new SubsumerDecompositionVisitor(ruleAppVisitor,
				premises, producer));
	}
	
	@Override
	public <O> O accept(ClassConclusion.Visitor<O> visitor) {
		return accept((SubClassInclusion.Visitor<O>) visitor);
	}

}
