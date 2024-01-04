
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionObjectHasSelfPropertyRange;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link SubsumerDecompositionRule} that processes
 * {@link IndexedObjectHasSelf} and produces the corresponding reflexive
 * {@link ForwardLink} or {@link BackwardLink}.
 * 
 * @author "Yevgeny Kazakov"
 */
public class IndexedObjectHasSelfDecomposition extends
		AbstractSubsumerDecompositionRule<IndexedObjectHasSelf> {

	public static final String NAME = "IndexedObjectHasSelf Decomposition";

	private static SubsumerDecompositionRule<IndexedObjectHasSelf> INSTANCE_ = new IndexedObjectHasSelfDecomposition();

	public static SubsumerDecompositionRule<IndexedObjectHasSelf> getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(IndexedObjectHasSelf premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		IndexedObjectHasSelf.Helper.produceDecomposedExistentialLink(producer,
				premises.getRoot(), premise);
		for (IndexedClassExpression range : premise.getProperty()
				.getSaturated().getRanges()) {
			producer.produce(new SubClassInclusionObjectHasSelfPropertyRange(premises
					.getRoot(), premise, range));
		}
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(SubsumerDecompositionRuleVisitor<?> visitor,
			IndexedObjectHasSelf premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
