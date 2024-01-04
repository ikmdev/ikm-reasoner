
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.reasoner.indexing.conversion.ElkIndexingException;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedSubObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableIndexedSubObject;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * A {@link CachedIndexedObject.Factory} that constructs objects using another
 * {@link CachedIndexedObject.Factory} and updates the occurrence counts for the
 * constructed objects using the provided {@link OccurrenceIncrement}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @see ModifiableIndexedObject#updateOccurrenceNumbers
 */
class UpdatingCachedIndexedObjectFactory extends
		DelegatingCachedIndexedObjectFactory {

	private final OccurrenceIncrement increment_;

	private final ModifiableOntologyIndex index_;

	public UpdatingCachedIndexedObjectFactory(
			CachedIndexedObject.Factory baseFactory,
			ModifiableOntologyIndex index, OccurrenceIncrement increment) {
		super(baseFactory);
		this.index_ = index;
		this.increment_ = increment;
	}

	@Override
	<T extends CachedIndexedSubObject<T>> T filter(T input) {
		T result = resolve(input);
		update(result);
		if (!result.occurs()) {
			index_.remove(result);
		}
		return result;
	}

	<T extends CachedIndexedObject<T>> T resolve(T input) {
		T result = index_.resolve(input);
		if (result == null) {
			result = input;
		}
		if (!result.occurs()) {
			index_.add(result);
		}
		return result;
	}

	<T extends ModifiableIndexedSubObject> T update(T input) {
		if (!input.updateOccurrenceNumbers(index_, increment_))
			throw new ElkIndexingException(input.toString()
					+ ": cannot update in Index for " + increment_
					+ " occurrences!");
		return input;
	}

	<T extends ModifiableIndexedAxiom> T update(T input) {
		if (increment_.totalIncrement > 0) {
			for (int i = 0; i < increment_.totalIncrement; i++) {
				if (!input.addOccurrence(index_))
					throw new ElkIndexingException(input.toString()
							+ ": cannot be added to Index!");
			}
		}
		if (increment_.totalIncrement < 0) {
			for (int i = 0; i < -increment_.totalIncrement; i++) {
				if (!input.removeOccurrence(index_))
					throw new ElkIndexingException(input.toString()
							+ ": cannot be removed from Index!");
			}
		}
		return input;
	}
	
}
