
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceNode;
import org.semanticweb.elk.reasoner.taxonomy.model.InstanceTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TypeNode;

/**
 * A fresh TypeNode containing an object that does not occur in a taxonomy. Such
 * nodes are returned to queries when FreshEntityPolicy is set to ALLOW.
 * 
 * @author Frantisek Simancik
 * @author Peter Skocovsky
 * @param <T>
 *            the type of objects in the nodes of this taxonomy
 * @param <I>
 *            the type of instances of nodes of this taxonomy
 * 
 */
public class FreshTypeNode<T extends ElkEntity, I extends ElkEntity>
		extends FreshNode<T> implements TypeNode<T, I> {

	protected InstanceTaxonomy<T, I> taxonomy;

	public FreshTypeNode(T member, InstanceTaxonomy<T, I> taxonomy) {
		super(member);
		this.taxonomy = taxonomy;
	}

	@Override
	public Taxonomy<T> getTaxonomy() {
		return taxonomy;
	}

	@Override
	public Set<TypeNode<T, I>> getDirectSuperNodes() {
		return Collections.singleton(taxonomy.getTopNode());
	}

	@Override
	public Set<TypeNode<T, I>> getAllSuperNodes() {
		return Collections.singleton(taxonomy.getTopNode());
	}

	@Override
	public Set<TypeNode<T, I>> getDirectSubNodes() {
		return Collections.singleton(taxonomy.getBottomNode());
	}

	@Override
	public Set<TypeNode<T, I>> getAllSubNodes() {
		return Collections.singleton(taxonomy.getBottomNode());
	}

	@Override
	public Set<? extends InstanceNode<T, I>> getDirectInstanceNodes() {
		return Collections.emptySet();
	}

	@Override
	public Set<? extends InstanceNode<T, I>> getAllInstanceNodes() {
		return Collections.emptySet();
	}

}
