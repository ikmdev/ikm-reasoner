
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectIntersectionOf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusion;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionDecomposedFirstConjunct;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionDecomposedSecondConjunct;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link SubsumerDecompositionRule} that processes an
 * {@link IndexedObjectIntersectionOf} and produces {@link SubClassInclusion}s for its
 * conjuncts
 * 
 * @see IndexedObjectIntersectionOf#getFirstConjunct()
 * @see IndexedObjectIntersectionOf#getSecondConjunct()
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class IndexedObjectIntersectionOfDecomposition extends
		AbstractSubsumerDecompositionRule<IndexedObjectIntersectionOf> {

	public static final String NAME = "ObjectIntersectionOf Decomposition";

	private static IndexedObjectIntersectionOfDecomposition INSTANCE_ = new IndexedObjectIntersectionOfDecomposition();

	public static IndexedObjectIntersectionOfDecomposition getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(IndexedObjectIntersectionOf premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		producer.produce(new SubClassInclusionDecomposedFirstConjunct(premises.getRoot(),
				premise));
		producer.produce(new SubClassInclusionDecomposedSecondConjunct(premises.getRoot(),
				premise));
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(SubsumerDecompositionRuleVisitor<?> visitor,
			IndexedObjectIntersectionOf premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);

	}

}
