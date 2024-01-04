
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectPropertyRangeAxiom;

/**
 * Implements {@link IndexedObjectPropertyRangeAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the defined class originates
 * @param <P>
 *            the type of the property
 * @param <C>
 *            the type of the range class
 */
class IndexedObjectPropertyRangeAxiomImpl<A extends ElkAxiom, P extends IndexedObjectProperty, C extends IndexedClassExpression>
		extends
			IndexedAxiomImpl<A>
		implements IndexedObjectPropertyRangeAxiom {

	private final P property_;

	private final C range_;

	IndexedObjectPropertyRangeAxiomImpl(A originalAxiom, P property, C range) {
		super(originalAxiom);
		this.property_ = property;
		this.range_ = range;
	}

	@Override
	public final P getProperty() {
		return this.property_;
	}

	@Override
	public final C getRange() {
		return this.range_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
