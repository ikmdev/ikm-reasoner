
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObjectSomeValuesFrom;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedRangeFiller;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;
import org.semanticweb.elk.reasoner.saturation.rules.subsumers.PropagationFromExistentialFillerRule;

/**
 * Implements {@link CachedIndexedObjectSomeValuesFrom}
 * 
 * @author "Yevgeny Kazakov"
 */
class CachedIndexedObjectSomeValuesFromImpl
		extends
		CachedIndexedComplexClassExpressionImpl<CachedIndexedObjectSomeValuesFrom>
		implements CachedIndexedObjectSomeValuesFrom {

	private final ModifiableIndexedRangeFillerImpl rangeFiller_;

	CachedIndexedObjectSomeValuesFromImpl(
			ModifiableIndexedObjectProperty property,
			ModifiableIndexedClassExpression filler) {
		super(CachedIndexedObjectSomeValuesFrom.Helper.structuralHashCode(
				property, filler));
		this.rangeFiller_ = new ModifiableIndexedRangeFillerImpl(property,
				filler);
	}

	@Override
	public final ModifiableIndexedObjectProperty getProperty() {
		return rangeFiller_.getProperty();
	}

	@Override
	public final ModifiableIndexedClassExpression getFiller() {
		return rangeFiller_.getFiller();
	}

	@Override
	public ModifiableIndexedRangeFiller getRangeFiller() {
		return rangeFiller_;
	}

	@Override
	public final CachedIndexedObjectSomeValuesFrom structuralEquals(Object other) {
		return CachedIndexedObjectSomeValuesFrom.Helper.structuralEquals(this,
				other);
	}

	@Override
	public final boolean updateOccurrenceNumbers(ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {

		if (negativeOccurrenceNo == 0 && increment.negativeIncrement > 0) {
			// first negative occurrence of this expression
			if (!PropagationFromExistentialFillerRule.addRuleFor(this, index))
				return false;
		}

		negativeOccurrenceNo += increment.negativeIncrement;

		if (negativeOccurrenceNo == 0 && increment.negativeIncrement < 0) {
			// no negative occurrences of this expression left
			if (!PropagationFromExistentialFillerRule
					.removeRuleFor(this, index)) {
				// revert the changes
				negativeOccurrenceNo -= increment.negativeIncrement;
				return false;
			}
		}
		positiveOccurrenceNo += increment.positiveIncrement;
		return true;

	}

	@Override
	public final <O> O accept(IndexedClassExpression.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedObjectSomeValuesFrom accept(
			CachedIndexedClassExpression.Filter filter) {
		return filter.filter(this);
	}

}
