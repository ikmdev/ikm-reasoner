
package org.semanticweb.elk.reasoner.saturation.rules.forwardlink;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.inferences.BackwardLinkReversedExpanded;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;

/**
 * A {@link ForwardLinkRule} applied when processing {@link ForwardLink}
 * producing the corresponding {@link BackwardLink}
 * 
 * @author "Yevgeny Kazakov"
 */
public class BackwardLinkFromForwardLinkRule extends AbstractForwardLinkRule {

	public static final String NAME = "BackwardLink from ForwardLink";

	private static final BackwardLinkFromForwardLinkRule INSTANCE_ = new BackwardLinkFromForwardLinkRule();

	public static BackwardLinkFromForwardLinkRule getInstance() {
		return INSTANCE_;
	}

	@Override
	public String toString() {
		return NAME;
	}

	@Override
	public void apply(ForwardLink premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		IndexedPropertyChain relation = premise.getChain();
		if (relation instanceof IndexedObjectProperty) {
			return;
		}
		// else
		List<IndexedObjectProperty> superProperties = relation
				.getToldSuperProperties();
		List<ElkAxiom> superPropertiesReasons = relation
				.getToldSuperPropertiesReasons();
		for (int i = 0; i < superProperties.size(); i++) {
			producer.produce(new BackwardLinkReversedExpanded(premise,
					superProperties.get(i), superPropertiesReasons.get(i)));
		}
	}

	@Override
	public boolean isTracingRule() {
		return true;
	}

	@Override
	public void accept(ForwardLinkRuleVisitor<?> visitor, ForwardLink premise,
			ContextPremises premises, ClassInferenceProducer producer) {
		visitor.visit(this, premise, premises, producer);
	}

}
