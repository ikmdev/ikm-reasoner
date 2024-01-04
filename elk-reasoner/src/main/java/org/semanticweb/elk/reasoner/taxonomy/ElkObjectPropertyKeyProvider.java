
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Comparator;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.predefined.PredefinedElkIris;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;

/**
 * {@link ComparatorKeyProvider} for
 * {@link org.semanticweb.elk.owl.interfaces.ElkObjectProperty
 * ElkObjectProperty}.
 * 
 * @author Peter Skocovsky
 */
public class ElkObjectPropertyKeyProvider extends ElkEntityKeyProvider
		implements ComparatorKeyProvider<ElkEntity> {

	/**
	 * The instance of this class.
	 */
	public static final ElkObjectPropertyKeyProvider INSTANCE = new ElkObjectPropertyKeyProvider();

	/**
	 * The comparator for
	 * {@link org.semanticweb.elk.owl.interfaces.ElkObjectProperty
	 * ElkObjectProperty}-es. Defines an ordering starting with
	 * {@link PredefinedElkIris#OWL_BOTTOM_OBJECT_PROPERTY},
	 * {@link PredefinedElkIris#OWL_TOP_OBJECT_PROPERTY}, followed by the
	 * remaining IRIs in alphabetical order.
	 */
	private static final Comparator<ElkEntity> COMPARATOR = new Comparator<ElkEntity>() {
		@Override
		public int compare(ElkEntity o1, ElkEntity o2) {
			boolean isOwl0 = o1.getIri()
					.equals(PredefinedElkIris.OWL_TOP_OBJECT_PROPERTY)
					|| o1.getIri().equals(
							PredefinedElkIris.OWL_BOTTOM_OBJECT_PROPERTY);
			boolean isOwl1 = o2.getIri()
					.equals(PredefinedElkIris.OWL_TOP_OBJECT_PROPERTY)
					|| o2.getIri().equals(
							PredefinedElkIris.OWL_BOTTOM_OBJECT_PROPERTY);

			if (isOwl0 == isOwl1) {
				return o1.getIri().compareTo(o2.getIri());
			}
			// else
			return isOwl0 ? -1 : 1;
		}
	};

	@Override
	public Comparator<ElkEntity> getComparator() {
		return COMPARATOR;
	}

}
