
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedSubObjectPropertyOfAxiom;

/**
 * Implements {@link IndexedSubObjectPropertyOfAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the defined class originates
 * @param <C>
 *            the type of sub-property chain
 * @param <P>
 *            the type of super-property
 */
class IndexedSubObjectPropertyOfAxiomImpl<A extends ElkAxiom, C extends IndexedPropertyChain, P extends IndexedObjectProperty>
		extends
			IndexedAxiomImpl<A>
		implements IndexedSubObjectPropertyOfAxiom {

	private final C subPropertyChain_;

	private final P superProperty_;

	IndexedSubObjectPropertyOfAxiomImpl(A originalAxiom, C subPropertyChain,
			P superProperty) {
		super(originalAxiom);
		this.subPropertyChain_ = subPropertyChain;
		this.superProperty_ = superProperty;
	}

	@Override
	public final C getSubPropertyChain() {
		return this.subPropertyChain_;
	}

	@Override
	public final P getSuperProperty() {
		return this.superProperty_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
