
package org.semanticweb.elk.reasoner.query;

import java.util.Collection;
import java.util.Collections;

import org.liveontologies.puli.Proof;
import org.semanticweb.elk.reasoner.entailments.impl.DerivedClassInclusionEntailsObjectPropertyDomainAxiomImpl;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.ObjectPropertyDomainAxiomEntailment;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.saturation.SaturationState;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SaturationConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;
import org.semanticweb.elk.reasoner.saturation.context.Context;

/**
 * Query whether an
 * {@link org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom
 * ElkObjectPropertyDomainAxiom} is entailed.
 * 
 * @author Peter Skocovsky
 */
public class ObjectPropertyDomainEntailmentQuery extends
		AbstractIndexedEntailmentQuery<ObjectPropertyDomainAxiomEntailment> {

	/**
	 * Existential with property from the queried axiom and {@code owl:Thing} as
	 * filler.
	 */
	private final IndexedClassExpression existential_;

	/**
	 * The domain from the queried axiom.
	 */
	private final IndexedClassExpression domain_;

	/**
	 * @param query
	 *            What entailment is queried.
	 * @param existential
	 *            Indexed individual that corresponds to an Existential with
	 *            property from the queried axiom and {@code owl:Thing} as
	 *            filler.
	 * @param domain
	 *            Indexed class expression that corresponds to the domain from
	 *            the queried axiom.
	 */
	public ObjectPropertyDomainEntailmentQuery(
			final ObjectPropertyDomainAxiomEntailment query,
			final IndexedClassExpression existential,
			final IndexedClassExpression domain) {
		super(query);
		this.existential_ = existential;
		this.domain_ = domain;
	}

	@Override
	public Collection<? extends IndexedContextRoot> getPositivelyIndexed() {
		return Collections.singleton(existential_);
	}

	@Override
	public <C extends Context> Proof<EntailmentInference> getEvidence(
			final boolean atMostOne, final SaturationState<C> saturationState,
			final SaturationConclusion.Factory conclusionFactory)
			throws ElkQueryException {
		return new Proof<EntailmentInference>() {

			@Override
			public Collection<? extends EntailmentInference> getInferences(
					final Object conclusion) {

				if (!getQuery().equals(conclusion)) {
					return Collections.emptySet();
				}
				// else

				final C context = saturationState.getContext(existential_);
				if (context == null) {
					// not entailed
					return Collections.emptySet();
				}
				// else

				final SubClassInclusionComposed subsumption = conclusionFactory
						.getSubClassInclusionComposed(existential_, domain_);
				if (context.containsConclusion(subsumption)) {
					return Collections.singleton(
							new DerivedClassInclusionEntailsObjectPropertyDomainAxiomImpl(
									getQuery(), subsumption));
				}
				// else

				return Collections.emptySet();
			}

		};
	}

}
