
package org.semanticweb.elk.reasoner.taxonomy.hashing;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.reasoner.taxonomy.model.TypeNode;
import org.semanticweb.elk.util.hashing.HashGenerator;
import org.semanticweb.elk.util.hashing.Hasher;

/**
 * Helper class for hashing TypeNodes based on getMembers(),
 * getDirectSubNodes(), getDirectSuperNodes(), and getDirectInstanceNodes().
 * 
 * @author Frantisek Simancik
 * 
 */
public class TypeNodeHasher implements
		Hasher<TypeNode<? extends ElkEntity, ? extends ElkEntity>> {

	/**
	 * We use one static instance for hashing all nodes.
	 */
	public static TypeNodeHasher INSTANCE = new TypeNodeHasher();

	private TypeNodeHasher() {
	}

	@Override
	public int hash(TypeNode<? extends ElkEntity, ? extends ElkEntity> node) {
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

		int instanceHash = "instanceOf".hashCode();
		for (Node<? extends ElkEntity> o : node.getDirectInstanceNodes()) {
			instanceHash = HashGenerator.combineMultisetHash(false,
					instanceHash, NodeHasher.INSTANCE.hash(o));
		}

		return HashGenerator.combineListHash(memberHash, subClassHash,
				superClassHash, instanceHash);
	}

}
