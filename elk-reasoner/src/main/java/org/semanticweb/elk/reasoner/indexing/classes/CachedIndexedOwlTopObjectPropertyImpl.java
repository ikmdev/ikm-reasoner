
package org.semanticweb.elk.reasoner.indexing.classes;



import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.reasoner.completeness.Feature;
import org.semanticweb.elk.reasoner.indexing.model.CachedIndexedOwlTopObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.ModifiableOntologyIndex;
import org.semanticweb.elk.reasoner.indexing.model.OccurrenceIncrement;

/**
 * Implements {@link CachedIndexedOwlTopObjectProperty}.
 * 
 * @author Peter Skocovsky
 */
public class CachedIndexedOwlTopObjectPropertyImpl
		extends CachedIndexedObjectPropertyImpl
		implements CachedIndexedOwlTopObjectProperty {

	CachedIndexedOwlTopObjectPropertyImpl(final ElkObjectProperty entity) {
		super(entity);
	}

	@Override
	public final boolean updateOccurrenceNumbers(
			final ModifiableOntologyIndex index,
			final OccurrenceIncrement increment) {
		if (super.updateOccurrenceNumbers(index, increment)) {

			// negative occurrences are unsupported
			index.occurrenceChanged(
					Feature.TOP_OBJECT_PROPERTY_NEGATIVE,
					increment.negativeIncrement);

			return true;
		}
		// else
		return false;
	}

}
