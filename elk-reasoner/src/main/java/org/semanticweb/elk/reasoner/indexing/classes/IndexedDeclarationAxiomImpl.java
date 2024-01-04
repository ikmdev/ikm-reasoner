
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedDeclarationAxiom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEntity;

/**
 * Implements {@link IndexedDeclarationAxiom}
 * 
 * @author Yevgeny Kazakov
 *
 * @param <A>
 *            the type of the {@link ElkAxiom} from which this axiom originates
 * @param <E>
 *            the type of the {@link IndexedEntity} used in the axiom
 */
class IndexedDeclarationAxiomImpl<A extends ElkAxiom, E extends IndexedEntity>
		extends
			IndexedAxiomImpl<A>
		implements IndexedDeclarationAxiom {

	private final E entity_;

	IndexedDeclarationAxiomImpl(A originalAxiom, E entity) {
		super(originalAxiom);
		this.entity_ = entity;
	}

	@Override
	public final E getEntity() {
		return this.entity_;
	}

	@Override
	public final <O> O accept(IndexedAxiom.Visitor<O> visitor) {
		return visitor.visit(this);
	}

}
