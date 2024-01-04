
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubClassOfAxiom;

/**
 * Implements {@link IndexedSubClassOfAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the defined class originates
 * @param <C>
 *            the type of sub- and super- classes
 */
class IndexedSubClassOfAxiomImpl<A extends ElkAxiom, C extends IndexedClassExpression>
		extends
			IndexedAxiomImpl<A>
		implements IndexedSubClassOfAxiom {

	private final C subClass_, superClass_;

	IndexedSubClassOfAxiomImpl(A originalAxiom, C subClass, C superClass) {
		super(originalAxiom);
		this.subClass_ = subClass;
		this.superClass_ = superClass;
	}

	@Override
	public final C getSubClass() {
		return this.subClass_;
	}

	@Override
	public final C getSuperClass() {
		return this.superClass_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
