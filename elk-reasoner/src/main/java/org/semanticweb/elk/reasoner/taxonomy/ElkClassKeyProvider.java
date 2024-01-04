
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Comparator;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.predefined.PredefinedElkIris;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;

/**
 * {@link ComparatorKeyProvider} for
 * {@link org.semanticweb.elk.owl.interfaces.ElkClass ElkClass}.
 * 
 * @author Peter Skocovsky
 */
public class ElkClassKeyProvider extends ElkEntityKeyProvider
		implements ComparatorKeyProvider<ElkEntity> {

	/**
	 * The instance of this class.
	 */
	public static final ElkClassKeyProvider INSTANCE = new ElkClassKeyProvider();

	/**
	 * The comparator for {@link org.semanticweb.elk.owl.interfaces.ElkClass
	 * ElkClass}-es.
	 */
	private static final Comparator<ElkEntity> COMPARATOR = new Comparator<ElkEntity>() {
		@Override
		public int compare(ElkEntity o1, ElkEntity o2) {
			return PredefinedElkIris.compare(o1.getIri(), o2.getIri());
		}
	};

	@Override
	public Comparator<ElkEntity> getComparator() {
		return COMPARATOR;
	}

}
