
package org.semanticweb.elk.matching.root;

import java.util.List;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;

public class IndexedContextRootClassExpressionMatch
		extends AbstractIndexedContextRootMatch<ElkClassExpression> {

	private IndexedContextRootClassExpressionMatch(ElkClassExpression value,
			List<? extends ElkClassExpression> rangeMatches) {
		super(value, rangeMatches);
	}

	IndexedContextRootClassExpressionMatch(ElkClassExpression value) {
		super(value);
	}

	@Override
	public ElkClassExpression getMainFillerMatch(ElkObject.Factory factory) {
		return getValue();
	}

	@Override
	public IndexedContextRootClassExpressionMatch extend(
			ElkClassExpression rangeMatch) {
		if (getValue().equals(rangeMatch)) {
			return this;
		}
		// else
		return new IndexedContextRootClassExpressionMatch(getValue(),
				extendRangeMatches(rangeMatch));
	}

	@Override
	public <O> O accept(IndexedContextRootMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(IndexedContextRootClassExpressionMatch match);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		IndexedContextRootClassExpressionMatch getIndexedContextRootClassExpressionMatch(
				ElkClassExpression value);

	}

}
