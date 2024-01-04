
package org.semanticweb.elk.matching.root;



/**
 * A {@link IndexedContextRootMatch.Visitor} that always returns {@code null}.
 * Can be used to prototype other visitors by overriding the defaultVisit
 * method.
 * 
 * @author Yevgeny Kazakov
 *
 * @param <O>
 *            the type of the output
 */
public class IndexedContextRootMatchDummyVisitor<O>
		implements IndexedContextRootMatch.Visitor<O> {

	protected O defaultVisit(IndexedContextRootMatch match) {
		return null;
	}

	@Override
	public O visit(IndexedContextRootClassExpressionMatch match) {
		return defaultVisit(match);
	}

	@Override
	public O visit(IndexedContextRootIndividualMatch match) {
		return defaultVisit(match);
	}

}
