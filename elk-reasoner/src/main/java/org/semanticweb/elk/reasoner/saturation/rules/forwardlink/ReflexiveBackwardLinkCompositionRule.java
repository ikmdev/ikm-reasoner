
package org.semanticweb.elk.reasoner.saturation.rules.forwardlink;



import java.util.Collection;
import java.util.Set;

import org.semanticweb.elk.reasoner.indexing.model.IndexedComplexPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.BackwardLink;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ForwardLink;
import org.semanticweb.elk.reasoner.saturation.context.ContextPremises;
import org.semanticweb.elk.reasoner.saturation.rules.ClassInferenceProducer;
import org.semanticweb.elk.util.collections.LazySetIntersection;
import org.semanticweb.elk.util.collections.Multimap;

/**
 * A {@link ForwardLinkRule} applied when processing this {@link ForwardLink}
 * producing {@link BackwardLink}s resulted by composition of this
 * {@link ForwardLink} with existing reflexive {@link BackwardLink}s using
 * property chain axioms
 * 
 * @see NonReflexiveBackwardLinkCompositionRule
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
public class ReflexiveBackwardLinkCompositionRule extends
		AbstractForwardLinkRule {
	// local rule

	public static final String NAME = "ForwardLink Reflexive BackwardLink Composition";

	/**
	 * 
	 */
	private final ForwardLink forwardLink_;

	/**
	 * @param forwardLink
	 */
	private ReflexiveBackwardLinkCompositionRule(ForwardLink forwardLink) {
		this.forwardLink_ = forwardLink;
	}

	/**
	 * @param link
	 *            a {@link ForwardLink} for which to create the rule
	 * @return {@link ReflexiveBackwardLinkCompositionRule}s for the given
	 *         {@link ForwardLink}
	 */
	public static ReflexiveBackwardLinkCompositionRule getRuleFor(
			ForwardLink link) {
		return new ReflexiveBackwardLinkCompositionRule(link);
	}

	@Override
	public String toString() {
		return NAME;
	}

	private void apply(
			final Multimap<IndexedObjectProperty, IndexedComplexPropertyChain> compsByBackwardRelation,
			ContextPremises premises, ClassInferenceProducer producer) {
		/* compose the link with all reflexive backward links */
		final Set<IndexedObjectProperty> reflexiveBackwardRelations = premises
				.getLocalReflexiveObjectProperties();

		for (IndexedObjectProperty backwardRelation : new LazySetIntersection<IndexedObjectProperty>(
				compsByBackwardRelation.keySet(), reflexiveBackwardRelations)) {
			Collection<IndexedComplexPropertyChain> compositions = compsByBackwardRelation
					.get(backwardRelation);
			for (IndexedComplexPropertyChain composition : compositions) {
				IndexedContextRoot root = premises.getRoot();
				IndexedObjectSomeValuesFrom.Helper.produceComposedLink(producer,
						root, backwardRelation, root, forwardLink_.getChain(),
						forwardLink_.getTarget(), composition);
			}
		}
	}
	
	@Override
	public void apply(ForwardLink premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		/* compose the link with all reflexive backward links */
		apply(forwardLink_.getChain().getSaturated()
				.getNonRedundantCompositionsByLeftSubProperty(), premises,
				producer);
	}
	
	@Override
	public void applyTracing(ForwardLink premise, ContextPremises premises,
			ClassInferenceProducer producer) {
		/* compose the link with all reflexive backward links */
		apply(forwardLink_.getChain().getSaturated()
				.getNonRedundantCompositionsByLeftSubProperty(), premises,
				producer);
		apply(forwardLink_.getChain().getSaturated()
				.getRedundantCompositionsByLeftSubProperty(), premises,
				producer);
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
