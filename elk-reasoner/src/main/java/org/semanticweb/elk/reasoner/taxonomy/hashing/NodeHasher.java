
package org.semanticweb.elk.reasoner.taxonomy.hashing;

import java.util.LinkedHashMap;
import java.util.Map;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.util.hashing.HashGenerator;
import org.semanticweb.elk.util.hashing.Hasher;

/**
 * Helper class for hashing Nodes based on getMembers(). Implements some caching
 * to ensure good performance even when some nodes have unusually many members.
 * 
 * @author Markus Kroetzsch
 * @author Frantisek Simancik
 * 
 */
public class NodeHasher implements Hasher<Node<? extends ElkEntity>> {

	/**
	 * We use one static instance for hashing (and caching!) all nodes.
	 */
	public static NodeHasher INSTANCE = new NodeHasher();

	private NodeHasher() {
	}

	/**
	 * IRI-based hasher used to cache members of a node.
	 */
	final Hasher<ElkEntity> elkEntityHasher = new Hasher<ElkEntity>() {

		@Override
		public int hash(ElkEntity elkEntity) {
			return elkEntity.getIri().hashCode();
		}
	};

	/**
	 * For nodes with at least this number of member classes, hash codes are
	 * cached in the hashCache.
	 */
	final int cacheNodeMemberNo = 50;

	/**
	 * Maximum number of large class nodes for which the hash value is cached.
	 */
	final int cacheMaxSize = 100;

	/**
	 * A simple Least Recently Used cache for the hashcodes of nodes that have a
	 * particularly large number of members. There should not be many such nodes
	 * in typical ontologies. Normally, this occurs if many classes are
	 * equivalent to owl:Nothing due to some modelling error.
	 */
	final LinkedHashMap<Node<? extends ElkEntity>, Integer> hashCache = new LinkedHashMap<Node<? extends ElkEntity>, Integer>() {
		private static final long serialVersionUID = 1;

		@Override
		protected boolean removeEldestEntry(
				Map.Entry<Node<? extends ElkEntity>, Integer> eldest) {
			return size() > cacheMaxSize;
		}
	};

	/**
	 * Compute the hash for an entity node. This method implements a simple
	 * cache for nodes with unusually large numbers of members. This mainly
	 * covers the case where a huge number of classes is equal to owl:Nothing
	 * due to modelling errors.
	 * 
	 * @param node
	 * @return the has for the given entity node
	 */
	@Override
	public int hash(Node<? extends ElkEntity> node) {
		if (node.size() >= cacheNodeMemberNo) {
			if (hashCache.containsKey(node)) {
				return hashCache.get(node);
			}
			// else
			int hash = HashGenerator.combineMultisetHash(true, node,
					elkEntityHasher);
			hashCache.put(node, hash);
			return hash;
		}
		// else
		return HashGenerator.combineMultisetHash(true, node, elkEntityHasher);
	}
}