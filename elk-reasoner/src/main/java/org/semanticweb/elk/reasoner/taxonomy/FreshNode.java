 
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Iterator;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.Node;
import org.semanticweb.elk.util.collections.Operations;

/**
 * A fresh Node containing an object that does not occur in a taxonomy. Such
 * nodes are returned to queries when FreshEntityPolicy is set to ALLOW.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 */
public class FreshNode<T extends ElkEntity> implements Node<T> {

	private final T member_;

	public FreshNode(T member) {
		this.member_ = member;
	}
	
	public T getMember() {
		return member_;
	}

	@Override
	public ComparatorKeyProvider<ElkEntity> getKeyProvider() {
		return null;
	}
	
	@Override
	public Iterator<T> iterator() {
		return Operations.singletonIterator(member_);
	}
	
	@Override
	public boolean contains(T member) {
		return member == null
				? this.member_ == null
				: member.getIri().equals(this.member_.getIri());
	}
	
	@Override
	public int size() {
		return 1;
	}
	
	@Override
	public T getCanonicalMember() {
		return member_;
	}

}
