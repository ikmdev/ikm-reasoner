 
package org.semanticweb.elk.reasoner.taxonomy.model;

import org.semanticweb.elk.owl.interfaces.ElkEntity;

/**
 * Instances of this interface are associated with a taxonomy.
 * 
 * @author Peter Skocovsky
 *
 * @param <T>
 *            the type of objects stored in the associated taxonomy
 */
public interface HasTaxonomy<T extends ElkEntity> {

	/**
	 * Returns the associated taxonomy.
	 * 
	 * @return the associated taxonomy.
	 */
	Taxonomy<T> getTaxonomy();

}
