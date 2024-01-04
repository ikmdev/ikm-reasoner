
package org.semanticweb.elk.reasoner.taxonomy.hashing;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;
import org.semanticweb.elk.util.hashing.HashGenerator;
import org.semanticweb.elk.util.hashing.Hasher;

/**
 * Helper class for hashing TaxonomyNodes based on getMembers(),
 * getDirectSubNodes(), and getDirectSuperNodes().
 * 
 * @author Frantisek Simancik
 * 
 */
public class TaxonomyNodeHasher implements Hasher<TaxonomyNode<? extends ElkEntity>> {

	/**
	 * We use one static instance for hashing all nodes.
	 */
	public static TaxonomyNodeHasher INSTANCE = new TaxonomyNodeHasher();

	private TaxonomyNodeHasher() {
	}

	@Override
	public int hash(TaxonomyNode<? extends ElkEntity> node) {
		int memberHash = NodeHasher.INSTANCE.hash(node);

		int subClassHash = "subClassOf".hashCode();
		for (Node<? extends ElkEntity> o : node.getDirectSubNodes()) {
			subClassHash = HashGenerator.combineMultisetHash(false,
					subClassHash, NodeHasher.INSTANCE.hash(o));
		}

		int superClassHash = "superClassOf".hashCode();
		for (Node<? extends ElkEntity> o : node.getDirectSuperNodes()) {
			superClassHash = HashGenerator.combineMultisetHash(false,
					superClassHash, NodeHasher.INSTANCE.hash(o));
		}

		return HashGenerator.combineListHash(memberHash, subClassHash,
				superClassHash);
	}

}
