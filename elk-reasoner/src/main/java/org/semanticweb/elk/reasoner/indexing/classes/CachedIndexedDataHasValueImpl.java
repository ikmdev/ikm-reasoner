
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.owl.interfaces.ElkDataHasValue;
import org.semanticweb.elk.owl.interfaces.ElkDataProperty;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedDataHasValue;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedDataHasValue}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
class CachedIndexedDataHasValueImpl
		extends
			CachedIndexedComplexClassExpressionImpl<CachedIndexedDataHasValue>
		implements
			CachedIndexedDataHasValue {

	private final ElkDataProperty property_;

	private final ElkLiteral filler_;

	private CachedIndexedDataHasValueImpl(ElkDataProperty property,
			ElkLiteral filler) {
		super(CachedIndexedDataHasValue.Helper.structuralHashCode(property,
				filler));
		this.property_ = property;
		this.filler_ = filler;
	}

	CachedIndexedDataHasValueImpl(ElkDataHasValue elkDataHasValue) {
		this((ElkDataProperty) elkDataHasValue.getProperty(),
				elkDataHasValue.getFiller());
	}

	@Override
	public final ElkDataProperty getRelation() {
		return property_;
	}

	@Override
	public final ElkLiteral getFiller() {
		return filler_;
	}

	@Override
	public final CachedIndexedDataHasValue structuralEquals(Object other) {
		return CachedIndexedDataHasValue.Helper.structuralEquals(this, other);
	}

	@Override
	public final boolean updateOccurrenceNumbers(
			final ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {
		positiveOccurrenceNo += increment.positiveIncrement;
		negativeOccurrenceNo += increment.negativeIncrement;
		return true;
	}

	@Override
	public final <O> O accept(IndexedClassExpression.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedDataHasValue accept(
			CachedIndexedClassExpression.Filter filter) {
		return filter.filter(this);
	}

}