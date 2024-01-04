
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.conversion.ElkUnexpectedIndexingException;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedClassEntity;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements {@link CachedIndexedClassEntity}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of objects this object can be structurally equal to
 */
abstract class CachedIndexedClassEntityImpl<T extends CachedIndexedClassEntity<T>>
		extends CachedIndexedClassExpressionImpl<T, T> implements
		CachedIndexedClassEntity<T> {

	// logger for events
	private static final Logger LOGGER_ = LoggerFactory
			.getLogger(CachedIndexedClassEntityImpl.class);

	/**
	 * This counts how many times this object occurred in the ontology.
	 */
	protected int totalOccurrenceNo = 0;

	CachedIndexedClassEntityImpl(int structuralHash) {
		super(structuralHash);
	}
	
	@Override
	public boolean updateOccurrenceNumbers(final ModifiableOntologyIndex index,
			OccurrenceIncrement increment) {

		totalOccurrenceNo += increment.totalIncrement;
		return true;
	}

	@Override
	public final boolean occurs() {
		return totalOccurrenceNo > 0;
	}

	@Override
	public String printOccurrenceNumbers() {
		return "[all=" + totalOccurrenceNo + "]";
	}

	public void checkOccurrenceNumbers() {
		if (LOGGER_.isTraceEnabled())
			LOGGER_.trace(toString() + " occurences: "
					+ printOccurrenceNumbers());
		if (totalOccurrenceNo < 0)
			throw new ElkUnexpectedIndexingException(toString()
					+ " has a negative occurrence: " + printOccurrenceNumbers());
	}

}
