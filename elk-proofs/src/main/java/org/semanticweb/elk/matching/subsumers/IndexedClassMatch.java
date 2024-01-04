
package org.semanticweb.elk.matching.subsumers;



import org.semanticweb.elk.owl.interfaces.ElkClass;

public class IndexedClassMatch
		extends AbstractIndexedClassEntityMatch<ElkClass> {

	IndexedClassMatch(ElkClass value) {
		super(value);
	}

	@Override
	public <O> O accept(IndexedClassEntityMatch.Visitor<O> visitor) {
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

		O visit(IndexedClassMatch match);

	}

}
