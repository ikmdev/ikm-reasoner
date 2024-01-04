
package org.semanticweb.elk.reasoner.taxonomy.impl;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.hashing.InstanceTaxonomyEqualator;
import org.semanticweb.elk.reasoner.taxonomy.hashing.InstanceTaxonomyHasher;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;

/**
 * @author Peter Skocovsky
 *
 * @param <T>
 *            The type of members of the type nodes in this taxonomy.
 * @param <I>
 *            The type of members of the instance nodes in this taxonomy.
 */
public abstract class AbstractInstanceTaxonomy<T extends ElkEntity, I extends ElkEntity>
		extends AbstractTaxonomy<T> implements InstanceTaxonomy<T, I> {

	@Override
	public int hashCode() {
		return InstanceTaxonomyHasher.hash(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(final Object obj) {

		if (!(obj instanceof Taxonomy<?>)) {
			return false;
		}

		try {
			return InstanceTaxonomyEqualator.equals(this, (Taxonomy<T>) obj);
		} catch (ClassCastException e) {
			return false;
		}
	}

}
