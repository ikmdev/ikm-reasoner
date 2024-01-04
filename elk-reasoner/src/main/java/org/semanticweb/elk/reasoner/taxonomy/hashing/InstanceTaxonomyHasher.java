
package org.semanticweb.elk.reasoner.taxonomy.hashing;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceTaxonomy;
import org.semanticweb.elk.util.hashing.HashGenerator;

/**
 * A class for computing the structural hash of a
 * Taxonomy. This is mainly useful during testing to check if the reasoner
 * produces the same taxonomy.
 * 
 * 
 * @author Frantisek Simancik
 */
public class InstanceTaxonomyHasher {

	/**
	 * Compute the hash code of a taxonomy.
	 * 
	 * @param taxonomy
	 * @return hash
	 */
	public static int hash(InstanceTaxonomy<? extends ElkEntity, ? extends ElkEntity> taxonomy) {
		int typeHash = HashGenerator.combineMultisetHash(true, taxonomy.getNodes(), TypeNodeHasher.INSTANCE);
		int instanceHash = HashGenerator.combineMultisetHash(true, taxonomy.getInstanceNodes(), InstanceNodeHasher.INSTANCE);		
		return HashGenerator.combineListHash(typeHash, instanceHash);
	}

}
