
package org.semanticweb.elk.reasoner.indexing.classes;

import org.semanticweb.elk.reasoner.completeness.Feature;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedComplexPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.IndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedPropertyChain;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedComplexPropertyChain}
 * 
 * @author "Yevgeny Kazakov"
 * 
 */
final class CachedIndexedComplexPropertyChainImpl
		extends
		CachedIndexedPropertyChainImpl<CachedIndexedComplexPropertyChain, CachedIndexedComplexPropertyChain>
		implements CachedIndexedComplexPropertyChain {

	private final ModifiableIndexedObjectProperty leftProperty_;

	private final ModifiableIndexedPropertyChain rightProperty_;

	/**
	 * Used for creating auxiliary inclusions during binarization.
	 * 
	 * @param leftProperty
	 * @param rightProperty
	 */
	public CachedIndexedComplexPropertyChainImpl(
			ModifiableIndexedObjectProperty leftProperty,
			ModifiableIndexedPropertyChain rightProperty) {
		super(CachedIndexedComplexPropertyChain.Helper.structuralHashCode(
				leftProperty, rightProperty));
		this.leftProperty_ = leftProperty;
		this.rightProperty_ = rightProperty;
	}

	@Override
	public final ModifiableIndexedObjectProperty getFirstProperty() {
		return leftProperty_;
	}

	@Override
	public final ModifiableIndexedPropertyChain getSuffixChain() {
		return rightProperty_;
	}

	@Override
	public final CachedIndexedComplexPropertyChain structuralEquals(Object other) {
		return CachedIndexedComplexPropertyChain.Helper.structuralEquals(this,
				other);
	}

	@Override
	public final boolean updateOccurrenceNumbers(ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {

		if (totalOccurrenceNo == 0 && increment.totalIncrement > 0) {
			// first occurrence of this expression
			if (!rightProperty_.addRightChain(this))
				return false;
			if (!leftProperty_.addLeftChain(this)) {
				// revert all changes
				rightProperty_.removeRightChain(this);
				return false;
			}
		}

		totalOccurrenceNo += increment.totalIncrement;

		if (totalOccurrenceNo == 0 && increment.totalIncrement < 0) {
			// no occurrences of this conjunction left
			if (!rightProperty_.removeRightChain(this)) {
				// revert all changes
				totalOccurrenceNo -= increment.totalIncrement;
				return false;
			}
			if (!leftProperty_.removeLeftChain(this)) {
				// revert all changes
				rightProperty_.addRightChain(this);
				totalOccurrenceNo -= increment.totalIncrement;
				return false;
			}
		}
		
		// not fully supported with reflexive properties
		index.occurrenceChanged(Feature.OBJECT_PROPERTY_CHAIN,
				increment.totalIncrement);
		
		// success!
		return true;
	}

	/**
	 * @param ipc
	 * @return the property chain which is composable with the given property in
	 *         this chain or null
	 */
	public final IndexedPropertyChain getComposable(IndexedPropertyChain ipc) {
		return ipc == leftProperty_ ? rightProperty_
				: (ipc == rightProperty_ ? leftProperty_ : null);
	}

	@Override
	public final <O> O accept(IndexedPropertyChain.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public CachedIndexedComplexPropertyChain accept(
			CachedIndexedPropertyChain.Filter filter) {
		return filter.filter(this);
	}

}
