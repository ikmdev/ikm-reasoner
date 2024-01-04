
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TypeNode;
import org.semanticweb.elk.util.collections.ArrayHashSet;

/**
 * An {@link OrphanTaxonomyNode} with instances.
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 * @param <I>
 *            the type of instances of this node
 */
public class OrphanTypeNode<T extends ElkEntity, I extends ElkEntity>
		extends OrphanTaxonomyNode<T> implements TypeNode<T, I> {

	final Set<OrphanInstanceNode<T, I>> instanceNodes;

	public OrphanTypeNode(final Iterable<? extends T> members, final int size,
			final T canonical, final Taxonomy<T> taxonomy,
			final int estimatedInstanceNodes) {
		super(members, size, canonical, taxonomy);
		this.instanceNodes = new ArrayHashSet<OrphanInstanceNode<T, I>>(
				estimatedInstanceNodes);
	}

	public void addInstanceNode(OrphanInstanceNode<T, I> instaneNode) {
		instanceNodes.add(instaneNode);
	}

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSuperNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getAllSuperNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getDirectSubNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends TypeNode<T, I>> getAllSubNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends OrphanInstanceNode<T, I>> getDirectInstanceNodes() {
		return Collections.unmodifiableSet(instanceNodes);
	}

	@Override
	public Set<? extends OrphanInstanceNode<T, I>> getAllInstanceNodes() {
		return getDirectInstanceNodes();
	}

}
