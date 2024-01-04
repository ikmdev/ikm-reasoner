
package org.semanticweb.elk.reasoner.query;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.completeness.Incompleteness;
import org.semanticweb.elk.reasoner.completeness.IncompletenessMonitor;

/**
 * A complete {@link QueryResult} whose {@link IncompletenessMonitor} always
 * returns {@code false}
 * {@link IncompletenessMonitor#isIncompletenessDetected()}
 * 
 * @author Yevgeny Kazakov
 *
 */
public class CompleteQueryResult implements QueryResult {

	private final ElkAxiom query_;

	private final boolean isEntailed_;

	public CompleteQueryResult(ElkAxiom query, boolean isEntailed) {
		this.query_ = query;
		this.isEntailed_ = isEntailed;
	}

	@Override
	public ElkAxiom getQuery() {
		return query_;
	}

	@Override
	public boolean entailmentProved() throws ElkQueryException {
		return isEntailed_;
	}

	@Override
	public IncompletenessMonitor getIncompletenessMonitor() {
		return Incompleteness.getNoIncompletenessMonitor();
	}

	@Override
	public boolean unlock() {
		return true;
	}

}
