
package org.semanticweb.elk.reasoner.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.reasoner.entailments.impl.EntailedClassInclusionCycleEntailsEquivalentClassesAxiomImpl;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.EquivalentClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;

/**
 * Query whether an
 * {@link org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom
 * ElkEquivalentClassesAxiom} is entailed.
 * <p>
 * Premises are {@link SubClassOfEntailmentQuery}-ies over all the classes from
 * the query, such that superclass of one is the subclass of the next one and
 * superclass of the last one is the subclass of the first one.
 * 
 * @author Peter Skocovsky
 */
public class EquivalentClassesEntailmentQuery extends
		AbstractEntailmentQueryWithPremises<EquivalentClassesAxiomEntailment, SubClassOfEntailmentQuery> {

	/**
	 * @param query
	 *            What entailment is queried.
	 * @param subsumptionCycle
	 *            {@link SubClassOfEntailmentQuery}-ies over all the classes
	 *            from the query, such that superclass of one is the subclass of
	 *            the next one and superclass of the last one is the subclass of
	 *            the first one.
	 */
	public EquivalentClassesEntailmentQuery(
			final EquivalentClassesAxiomEntailment query,
			final List<SubClassOfEntailmentQuery> subsumptionCycle) {
		super(query, subsumptionCycle);
	}

	@Override
	public Collection<? extends EntailmentInference> getEntailmentInference() {

		final List<SubClassOfAxiomEntailment> premises = new ArrayList<SubClassOfAxiomEntailment>();

		for (final SubClassOfEntailmentQuery subsumption : getPremises()) {
			premises.add(subsumption.getQuery());
		}

		return Collections.singleton(
				new EntailedClassInclusionCycleEntailsEquivalentClassesAxiomImpl(
						getQuery(), premises));
	}

}
