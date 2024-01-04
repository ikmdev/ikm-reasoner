
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEquivalentClassesAxiom;

/**
 * Implements {@link IndexedEquivalentClassesAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 * @param <C>
 *            the type of the members
 */
class IndexedEquivalentClassesAxiomImpl<A extends ElkAxiom, C extends IndexedClassExpression>
		extends IndexedAxiomImpl<A> implements IndexedEquivalentClassesAxiom {

	private final C firstMember_, secondMember_;

	protected IndexedEquivalentClassesAxiomImpl(A originalAxiom, C firstMember,
			C secondMember) {
		super(originalAxiom);
		this.firstMember_ = firstMember;
		this.secondMember_ = secondMember;
	}

	@Override
	public C getFirstMember() {
		return this.firstMember_;
	}

	@Override
	public C getSecondMember() {
		return this.secondMember_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
