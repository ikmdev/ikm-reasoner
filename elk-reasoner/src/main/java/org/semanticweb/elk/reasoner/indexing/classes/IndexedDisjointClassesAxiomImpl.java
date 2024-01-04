
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDisjointClassesAxiom;

/**
 * Implements {@link IndexedDisjointClassesAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the defined class originates
 * @param <M>
 *            the type of the disjont class members list
 */
class IndexedDisjointClassesAxiomImpl<A extends ElkAxiom, M extends IndexedClassExpressionList>
		extends
			IndexedAxiomImpl<A>
		implements IndexedDisjointClassesAxiom {

	/**
	 * the {@link IndexedClassExpression}s stated to be disjoint. Note that same
	 * may appear two times in this list, in which case they should be
	 * inconsistent (disjoint with itself)
	 */
	private final M members_;

	protected IndexedDisjointClassesAxiomImpl(A originalAxiom, M members) {
		super(originalAxiom);
		this.members_ = members;
	}

	@Override
	public final M getMembers() {
		return members_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
