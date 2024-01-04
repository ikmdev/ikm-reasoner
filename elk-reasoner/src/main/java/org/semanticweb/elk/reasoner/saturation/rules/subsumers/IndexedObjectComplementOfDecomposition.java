
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectComplementOf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ClassInconsistency;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.ClassInconsistencyOfObjectComplementOf;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link SubsumerDecompositionRule} producing {@link ClassInconsistency} when
 * processing the {@link IndexedObjectComplementOf} which negation
 * {@link IndexedClassExpression} is contained in the {@code Context}
 * 
 * @see IndexedObjectComplementOf#getNegated()
 * @see ContradictionFromNegationRule
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class IndexedObjectComplementOfDecomposition extends
		AbstractSubsumerDecompositionRule<IndexedObjectComplementOf> {

	public static final String NAME = "IndexedObjectComplementOf Decomposition";

	private static IndexedObjectComplementOfDecomposition INSTANCE_ = new IndexedObjectComplementOfDecomposition();

	public static IndexedObjectComplementOfDecomposition getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(IndexedObjectComplementOf premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		if (premises.getComposedSubsumers().contains(premise.getNegated())) {
			producer.produce(new ClassInconsistencyOfObjectComplementOf(premises.getRoot(),
					premise));
		}
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(SubsumerDecompositionRuleVisitor<?> visitor,
			IndexedObjectComplementOf premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
