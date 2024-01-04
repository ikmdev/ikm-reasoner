
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectHasSelf;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedObjectSomeValuesFrom}
 * 
 * @author "Yevgeny Kazakov"
 */
class CachedIndexedObjectHasSelfImpl extends
		CachedIndexedComplexClassExpressionImpl<CachedIndexedObjectHasSelf>
		implements CachedIndexedObjectHasSelf {

	private final ModifiableIndexedObjectProperty property_;

	CachedIndexedObjectHasSelfImpl(ModifiableIndexedObjectProperty property) {
		super(CachedIndexedObjectHasSelf.Helper.structuralHashCode(property));
		this.property_ = property;
	}

	@Override
	public final ModifiableIndexedObjectProperty getProperty() {
		return property_;
	}

	@Override
	public final CachedIndexedObjectHasSelf structuralEquals(Object other) {
		return CachedIndexedObjectHasSelf.Helper.structuralEquals(this, other);
	}

	@Override
	public final boolean updateOccurrenceNumbers(ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {

		// TODO: support composition rules

		negativeOccurrenceNo += increment.negativeIncrement;
		positiveOccurrenceNo += increment.positiveIncrement;
		return true;

	}

	@Override
	public final <O> O accept(IndexedClassExpression.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedObjectHasSelf accept(
			CachedIndexedClassExpression.Filter filter) {
		return filter.filter(this);
	}

}
