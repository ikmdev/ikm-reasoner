
package org.semanticweb.elk.matching.root;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;



public interface IndexedContextRootMatch {

	ElkClassExpression toElkExpression(ElkObject.Factory factory);

	List<? extends ElkClassExpression> getFillerMatches(
			ElkObject.Factory factory);

	ElkClassExpression getMainFillerMatch(ElkObject.Factory factory);

	List<? extends ElkClassExpression> getRangeMatches();

	IndexedContextRootMatch extend(ElkClassExpression rangeMatch);

	<O> O accept(Visitor<O> visitor);

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O>
			extends IndexedContextRootClassExpressionMatch.Visitor<O>,
			IndexedContextRootIndividualMatch.Visitor<O> {

		// combined interface

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory
			extends IndexedContextRootClassExpressionMatch.Factory,
			IndexedContextRootIndividualMatch.Factory {

		// combined interface

	}

}
