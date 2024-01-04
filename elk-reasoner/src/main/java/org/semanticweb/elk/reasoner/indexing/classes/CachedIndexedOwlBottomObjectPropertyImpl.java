
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.completeness.Feature;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedOwlBottomObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedOwlBottomObjectProperty}.
 * 
 * @author Peter Skocovsky
 */
public class CachedIndexedOwlBottomObjectPropertyImpl
		extends CachedIndexedObjectPropertyImpl
		implements CachedIndexedOwlBottomObjectProperty {

	CachedIndexedOwlBottomObjectPropertyImpl(final ElkObjectProperty entity) {
		super(entity);
	}

	@Override
	public final boolean updateOccurrenceNumbers(
			final ModifiableOntologyIndex index,
			final OccurrenceIncrement increment) {
		if (super.updateOccurrenceNumbers(index, increment)) {

			// positive occurrences are unsupported
			index.occurrenceChanged(Feature.BOTTOM_OBJECT_PROPERTY_POSITIVE,
					increment.positiveIncrement);

			return true;
		}
		// else
		return false;
	}

}
