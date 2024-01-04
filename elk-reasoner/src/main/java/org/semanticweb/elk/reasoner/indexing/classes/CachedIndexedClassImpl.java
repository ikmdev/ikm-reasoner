
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClass;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassEntity;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedEntity;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;

/**
 * Implements an equality view for instances of {@link IndexedClass}
 * 
 * @author "Yevgeny Kazakov"
 */
class CachedIndexedClassImpl extends
		CachedIndexedClassEntityImpl<CachedIndexedClass> implements
		CachedIndexedClass {

	/**
	 * The represented {@link ElkClass}
	 */
	private final ElkClass elkClass_;

	/**
	 * The equivalent {@link ModifiableIndexedClassExpression} if there exists
	 * one or {@code null} otherwise
	 */
	private ModifiableIndexedClassExpression definition_;

	/**
	 * The {@link ElkAxiom} from which {@link #definition_} originates
	 */
	private ElkAxiom definitionReason_;

	CachedIndexedClassImpl(ElkClass entity) {
		super(CachedIndexedClass.Helper.structuralHashCode(entity));
		elkClass_ = entity;
	}

	@Override
	public final ElkClass getElkEntity() {
		return elkClass_;
	}

	@Override
	public IndexedClassExpression getDefinition() {
		return this.definition_;
	}

	@Override
	public ElkAxiom getDefinitionReason() {
		return this.definitionReason_;
	}

	@Override
	public boolean setDefinition(ModifiableIndexedClassExpression definition,
			ElkAxiom reason) {
		if (definition_ != null)
			return false;
		// else
		this.definition_ = definition;
		this.definitionReason_ = reason;
		return true;
	}

	@Override
	public void removeDefinition() {
		this.definition_ = null;
	}

	@Override
	public final CachedIndexedClass structuralEquals(Object other) {
		return CachedIndexedClass.Helper.structuralEquals(this, other);
	}

	@Override
	public final <O> O accept(IndexedClassExpression.Visitor<O> visitor) {
		return accept((IndexedClassEntity.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(IndexedEntity.Visitor<O> visitor) {
		return accept((IndexedClassEntity.Visitor<O>) visitor);
	}

	@Override
	public final <O> O accept(IndexedClassEntity.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedClass accept(CachedIndexedClassExpression.Filter filter) {
		return filter.filter(this);
	}

}