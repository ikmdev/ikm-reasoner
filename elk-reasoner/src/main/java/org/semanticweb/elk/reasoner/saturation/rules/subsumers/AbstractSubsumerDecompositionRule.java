
package org.semanticweb.elk.reasoner.saturation.rules.subsumers;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

abstract class AbstractSubsumerDecompositionRule<S extends IndexedClassExpression>
		extends AbstractSubsumerRule<S> implements SubsumerDecompositionRule<S> {

	@Override
	public void accept(SubsumerRuleVisitor<?> visitor, S premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		accept((SubsumerDecompositionRuleVisitor<?>) visitor, premise,
				premises, producer);

	}

}
