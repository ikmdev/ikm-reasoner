
package org.semanticweb.elk.reasoner.taxonomy.hashing;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.util.hashing.HashGenerator;

/**
 * A class for computing the structural hash of a
 * Taxonomy. This is mainly useful during testing to check if the reasoner
 * produces the same taxonomy.
 * 
 * 
 * @author Frantisek Simancik
 * @author Markus Kroetzsch
 */
public class TaxonomyHasher {

	/**
	 * Compute the hash code of a taxonomy.
	 * 
	 * @param taxonomy
	 * @return hash
	 */
	public static int hash(Taxonomy<? extends ElkEntity> taxonomy) {
		return HashGenerator.combineMultisetHash(true, taxonomy.getNodes(), TaxonomyNodeHasher.INSTANCE);
	}

}
