
package org.semanticweb.elk.matching;



import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.semanticweb.elk.matching.conclusions.ConclusionMatch;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionDelegatingFactory;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ConclusionMatchExpressionRecycleFactory
		extends ConclusionMatchExpressionDelegatingFactory {

	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(ConclusionMatchExpressionRecycleFactory.class);

	private final Map<Object, Object> cache_ = new HashMap<Object, Object>();

	private final ConclusionMatch.Visitor<?> newMatchVisitor_;

	private final Collection<ConclusionMatch> newConclusions_;

	ConclusionMatchExpressionRecycleFactory(ElkObject.Factory elkObjectFactory,
			Collection<ConclusionMatch> newMatches,
			ConclusionMatch.Visitor<?> newMatchVisitor) {
		super(elkObjectFactory);
		this.newMatchVisitor_ = newMatchVisitor;
		this.newConclusions_ = newMatches;
	}

	@Override
	protected <C extends ConclusionMatch> C filter(C candidate) {
		@SuppressWarnings("unchecked")
		C previous = (C) cache_.get(candidate);
		if (previous != null) {
			LOGGER_.trace("{}: recycled", previous);
			return previous;
		}
		// else
		LOGGER_.trace("{}: new", candidate);
		cache_.put(candidate, candidate);
		newConclusions_.add(candidate);
		candidate.accept(newMatchVisitor_);
		return candidate;
	}

}
