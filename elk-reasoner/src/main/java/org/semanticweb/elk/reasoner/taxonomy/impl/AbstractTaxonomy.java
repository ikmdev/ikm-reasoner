
package org.semanticweb.elk.reasoner.taxonomy.impl;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.hashing.TaxonomyEqualator;
import org.semanticweb.elk.reasoner.taxonomy.hashing.TaxonomyHasher;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;

/**
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the nodes in this taxonomy.
 */
public abstract class AbstractTaxonomy<T extends ElkEntity>
		implements Taxonomy<T> {

	@Override
	public int hashCode() {
		return TaxonomyHasher.hash(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof Taxonomy<?>)) {
			return false;
		}

		try {
			return TaxonomyEqualator.equals(this, (Taxonomy<T>) obj);
		} catch (ClassCastException e) {
			return false;
		}
	}

}
