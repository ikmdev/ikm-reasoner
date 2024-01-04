
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link SubsumerDecompositionRule} that processes
 * {@link IndexedObjectSomeValuesFrom} and produces the corresponding
 * {@link ForwardLink} or {@link BackwardLink} for the context corresponding to
 * its filler (or range filler).
 * 
 * @see IndexedObjectSomeValuesFrom#getFiller()
 * @see IndexedObjectSomeValuesFrom#getRangeFiller()
 * 
 * @author "Yevgeny Kazakov"
 */
public class IndexedObjectSomeValuesFromDecomposition extends
		AbstractSubsumerDecompositionRule<IndexedObjectSomeValuesFrom> {

	public static final String NAME = "IndexedObjectSomeValuesFrom Decomposition";

	private static SubsumerDecompositionRule<IndexedObjectSomeValuesFrom> INSTANCE_ = new IndexedObjectSomeValuesFromDecomposition();

	public static SubsumerDecompositionRule<IndexedObjectSomeValuesFrom> getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(IndexedObjectSomeValuesFrom premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		IndexedObjectSomeValuesFrom.Helper.produceDecomposedExistentialLink(
				producer, premises.getRoot(), premise);
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(SubsumerDecompositionRuleVisitor<?> visitor,
			IndexedObjectSomeValuesFrom premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
