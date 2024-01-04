
package org.semanticweb.elk.reasoner.query;

import java.util.Collection;
import java.util.Collections;

import org.semanticweb.elk.reasoner.entailments.impl.EntailedEquivalentClassesEntailsSameIndividualAxiomImpl;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.SameIndividualAxiomEntailment;

/**
 * Query whether an
 * {@link org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom
 * ElkSameIndividualAxiom} is entailed.
 * <p>
 * Premises contain one {@link EquivalentClassesEntailmentQuery} querying
 * whether nominals of individuals from the queried axiom are equivalent.
 * 
 * @author Peter Skocovsky
 */
public class SameIndividualEntailmentQuery extends
		AbstractEntailmentQueryWithPremises<SameIndividualAxiomEntailment, EquivalentClassesEntailmentQuery> {

	/**
	 * @param query
	 *            What entailment is queried.
	 * @param equivalence
	 *            {@link EquivalentClassesEntailmentQuery} querying whether
	 *            nominals of individuals from the queried axiom are equivalent.
	 */
	public SameIndividualEntailmentQuery(
			final SameIndividualAxiomEntailment query,
			final EquivalentClassesEntailmentQuery equivalence) {
		super(query, Collections.singletonList(equivalence));
	}

	@Override
	public Collection<? extends EntailmentInference> getEntailmentInference() {

		return Collections.singleton(
				new EntailedEquivalentClassesEntailsSameIndividualAxiomImpl(
						getQuery(), getPremises().get(0).getQuery()));
	}

}
