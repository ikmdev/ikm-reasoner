
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;

/**
 * An {@link OrphanNode} with one member type node
 * 
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 * @param <I>
 *            the type of instances of this node
 */
public class OrphanInstanceNode<T extends ElkEntity, I extends ElkEntity>
		extends OrphanNode<I> implements InstanceNode<T, I> {

	final OrphanTypeNode<T, I> typeNode;

	private final ComparatorKeyProvider<? super I> instanceKeyProvider_;

	public OrphanInstanceNode(final Iterable<? extends I> members,
			final int size, final I canonical,
			ComparatorKeyProvider<? super I> instanceKeyProvider,
			final OrphanTypeNode<T, I> typeNode) {
		super(members, size, canonical, instanceKeyProvider);
		this.typeNode = typeNode;
		this.instanceKeyProvider_ = instanceKeyProvider;
	}

	public OrphanInstanceNode(final Collection<? extends I> members,
			ComparatorKeyProvider<? super I> instanceKeyProvider,
			final OrphanTypeNode<T, I> typeNode) {
		super(members, members.size(),
				Collections.min(members, instanceKeyProvider.getComparator()),
				instanceKeyProvider);
		this.typeNode = typeNode;
		this.instanceKeyProvider_ = instanceKeyProvider;
	}

	@Override
	public Set<? extends OrphanTypeNode<T, I>> getDirectTypeNodes() {
		return Collections.singleton(typeNode);
	}

	@Override
	public Set<? extends OrphanTypeNode<T, I>> getAllTypeNodes() {
		return Collections.singleton(typeNode);
	}

	@Override
	public Taxonomy<T> getTaxonomy() {
		return typeNode.getTaxonomy();
	}

	@Override
	public ComparatorKeyProvider<? super I> getKeyProvider() {
		return instanceKeyProvider_;
	}

}
