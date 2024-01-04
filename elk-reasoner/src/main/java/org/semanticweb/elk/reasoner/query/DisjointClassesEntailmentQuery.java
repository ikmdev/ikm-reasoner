
package org.semanticweb.elk.reasoner.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.reasoner.entailments.impl.EntailedIntersectionInconsistencyEntailsDisjointClassesAxiomImpl;
import org.semanticweb.elk.reasoner.entailments.model.DisjointClassesAxiomEntailment;
import org.semanticweb.elk.reasoner.entailments.model.EntailmentInference;
import org.semanticweb.elk.reasoner.entailments.model.SubClassOfAxiomEntailment;

/**
 * Query whether an
 * {@link org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom
 * ElkDisjointClassesAxiom} is entailed.
 * <p>
 * Premises are {@link SubClassOfEntailmentQuery}-ies where subclasses are all
 * pairwise intersections of classes from the query and superclasses are
 * {@code owl:Nothing}.
 * 
 * @author Peter Skocovsky
 */
public class DisjointClassesEntailmentQuery extends
		AbstractEntailmentQueryWithPremises<DisjointClassesAxiomEntailment, SubClassOfEntailmentQuery> {

	/**
	 * @param query
	 *            What entailment is queried.
	 * @param premises
	 *            {@link SubClassOfEntailmentQuery}-ies where subclasses are all
	 *            pairwise intersections of classes from the query and
	 *            superclasses are {@code owl:Nothing}.
	 */
	public DisjointClassesEntailmentQuery(
			final DisjointClassesAxiomEntailment query,
			final List<SubClassOfEntailmentQuery> premises) {
		super(query, premises);
	}

	@Override
	public Collection<? extends EntailmentInference> getEntailmentInference() {

		final List<SubClassOfAxiomEntailment> premises = new ArrayList<SubClassOfAxiomEntailment>();

		for (final SubClassOfEntailmentQuery subsumption : getPremises()) {
			premises.add(subsumption.getQuery());
		}

		return Collections.singleton(
				new EntailedIntersectionInconsistencyEntailsDisjointClassesAxiomImpl(
						getQuery(), premises));
	}

}
