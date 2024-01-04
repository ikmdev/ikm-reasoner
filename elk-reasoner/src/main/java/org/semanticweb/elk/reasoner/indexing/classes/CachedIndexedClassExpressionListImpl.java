
package org.semanticweb.elk.reasoner.indexing.classes;

import java.util.List;

import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpressionList;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedClassExpressionList}
 * 
 * @author "Yevgeny Kazakov"
 */
class CachedIndexedClassExpressionListImpl
		extends
			CachedIndexedObjectImpl<CachedIndexedClassExpressionList, CachedIndexedClassExpressionList>
		implements
			CachedIndexedClassExpressionList {

	/**
	 * The elements of the list
	 */
	private final List<? extends ModifiableIndexedClassExpression> elements_;
	
	/**
	 * Counts how often this {@link IndexedClassExpressionList} occurs in the
	 * ontology.
	 */
	int totalOccurrenceNo_ = 0;

	CachedIndexedClassExpressionListImpl(
			List<? extends ModifiableIndexedClassExpression> members) {
		super(CachedIndexedClassExpressionList.Helper.structuralHashCode(members));
		this.elements_ = members;
	}
	
	@Override
	public final boolean occurs() {
		return totalOccurrenceNo_ > 0;
	}

	@Override
	public final List<? extends ModifiableIndexedClassExpression> getElements() {
		return elements_;
	}

	@Override
	public final CachedIndexedClassExpressionList structuralEquals(Object other) {
		return CachedIndexedClassExpressionList.Helper.structuralEquals(this,
				other);
	}

	@Override
	public boolean updateOccurrenceNumbers(ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {
		
		totalOccurrenceNo_ += increment.totalIncrement;
		
		return true;
	}
		
	@Override
	public CachedIndexedClassExpressionList accept(
			CachedIndexedObject.Filter filter) {
		return filter.filter(this);
	}
	
	@Override
	public final <O> O accept(IndexedObject.Visitor<O> visitor) {
		return visitor.visit(this);
	}


}
