
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Iterator;
import java.util.Map;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.util.collections.ArrayHashMap;

/**
 * A base {@link Node} whose subclasses are supposed to be associated with no
 * (or minimal number) of other nodes.
 * 
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects stored in the nodes
 * 
 * @see SingletoneTaxonomy
 */
public abstract class OrphanNode<T extends ElkEntity> implements Node<T> {

	/**
	 * the members of the node
	 */
	private final Map<Object, T> members;
	/**
	 * the representative of the node; should be among the members
	 */
	private final T canonical;

	public OrphanNode(final Iterable<? extends T> members, final int size,
			final T canonical, ComparatorKeyProvider<? super T> keyProvider) {
		this.members = new ArrayHashMap<Object, T>(size);
		for (T member : members) {
			this.members.put(keyProvider.getKey(member), member);
		}
		this.canonical = canonical;
	}

	@Override
	public Iterator<T> iterator() {
		return members.values().iterator();
	}

	@Override
	public boolean contains(final T member) {
		return members.containsKey(getKeyProvider().getKey(member));
	}

	@Override
	public int size() {
		return members.size();
	}

	@Override
	public T getCanonicalMember() {
		return canonical;
	}

}
