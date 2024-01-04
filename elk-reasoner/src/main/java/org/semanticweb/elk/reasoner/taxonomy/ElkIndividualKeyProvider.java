
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Comparator;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;

/**
 * {@link ComparatorKeyProvider} for
 * {@link org.semanticweb.elk.owl.interfaces.ElkNamedIndividual
 * ElkNamedIndividual}.
 * 
 * @author Peter Skocovsky
 */
public class ElkIndividualKeyProvider extends ElkEntityKeyProvider
		implements ComparatorKeyProvider<ElkEntity> {

	/**
	 * The instance of this class.
	 */
	public static final ElkIndividualKeyProvider INSTANCE = new ElkIndividualKeyProvider();

	/**
	 * The comparator for
	 * {@link org.semanticweb.elk.owl.interfaces.ElkNamedIndividual
	 * ElkNamedIndividual}-s.
	 */
	private static Comparator<ElkEntity> COMPARATOR = new Comparator<ElkEntity>() {
		@Override
		public int compare(ElkEntity o1, ElkEntity o2) {
			return o1.getIri().compareTo(o2.getIri());
		}
	};

	@Override
	public Comparator<ElkEntity> getComparator() {
		return COMPARATOR;
	}

}
