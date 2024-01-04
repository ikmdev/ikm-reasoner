
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkNamedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedIndividual;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassEntity;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEntity;

/**
 * Implements {@link CachedIndexedIndividual}
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 */
class CachedIndexedIndividualImpl extends
		CachedIndexedClassEntityImpl<CachedIndexedIndividual> implements
		CachedIndexedIndividual {

	/**
	 * The represented {@link ElkNamedIndividual}
	 */
	private final ElkNamedIndividual elkNamedIndividual_;

	CachedIndexedIndividualImpl(ElkNamedIndividual entity) {
		super(CachedIndexedIndividual.Helper.structuralHashCode(entity));
		this.elkNamedIndividual_ = entity;
	}

	@Override
	public ElkNamedIndividual getElkEntity() {
		return elkNamedIndividual_;
	}

	@Override
	public CachedIndexedIndividual structuralEquals(Object other) {
		return CachedIndexedIndividual.Helper.structuralEquals(this, other);
	}

	@Override
	public final <O> O accept(IndexedClassEntity.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(IndexedEntity.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public final <O> O accept(IndexedClassExpression.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedIndividual accept(
			CachedIndexedClassExpression.Filter filter) {
		return filter.filter(this);
	}

}