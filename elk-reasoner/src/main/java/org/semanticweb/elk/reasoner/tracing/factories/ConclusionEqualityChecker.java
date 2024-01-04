 
package org.semanticweb.elk.reasoner.tracing.factories;



import org.semanticweb.elk.reasoner.tracing.Conclusion;
import org.semanticweb.elk.reasoner.tracing.DummyConclusionVisitor;

/**
 * A convenience class for checking equality of several {@link Conclusion}s to a
 * given {@link Conclusion}.
 * 
 * @author Yevgeny Kazakov
 */
class ConclusionEqualityChecker extends DummyConclusionVisitor<Void> {

	private final Conclusion conclusion_;

	/**
	 * {@code true} if some of the visited conclusions are equal to
	 * {@link #conclusion_}
	 */
	private boolean result_ = false;

	ConclusionEqualityChecker(Conclusion conclusion) {
		this.conclusion_ = conclusion;
	}

	public boolean getResult() {
		return result_;
	}

	@Override
	protected Void defaultVisit(Conclusion candidate) {
		result_ |= conclusion_.equals(candidate);
		return null;
	}

}