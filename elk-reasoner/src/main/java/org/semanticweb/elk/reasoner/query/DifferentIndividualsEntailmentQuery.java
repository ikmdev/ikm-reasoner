
package org.semanticweb.elk.reasoner.query;

import java.util.Collection;
import java.util.Collections;

import org.semanticweb.elk.reasoner.entailments.impl.EntailedDisjointClassesEntailsDifferentIndividualsAxiomImpl;
import org.semanticweb.elk.reasoner.entailments.model.DifferentIndividualsAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;

/**
 * Query whether an
 * {@link org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom
 * ElkDifferentIndividualsAxiom} is entailed.
 * <p>
 * Premises contain one {@link DisjointClassesEntailmentQuery} querying whether
 * nominals of individuals from the queried axiom are disjoint.
 * 
 * @author Peter Skocovsky
 */
public class DifferentIndividualsEntailmentQuery extends
		AbstractEntailmentQueryWithPremises<DifferentIndividualsAxiomEntailment, DisjointClassesEntailmentQuery> {

	/**
	 * @param query
	 *            What entailment is queried.
	 * @param disjointness
	 *            {@link DisjointClassesEntailmentQuery} querying whether
	 *            nominals of individuals from the queried axiom are disjoint.
	 */
	public DifferentIndividualsEntailmentQuery(
			final DifferentIndividualsAxiomEntailment query,
			final DisjointClassesEntailmentQuery disjointness) {
		super(query, Collections.singletonList(disjointness));
	}

	@Override
	public Collection<? extends EntailmentInference> getEntailmentInference() {

		return Collections.singleton(
				new EntailedDisjointClassesEntailsDifferentIndividualsAxiomImpl(
						getQuery(), getPremises().get(0).getQuery()));
	}

}
