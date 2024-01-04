
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.Taxonomy;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;

/**
 * A fresh TaxonomyNode containing an object that does not occur in a taxonomy.
 * Such nodes are returned to queries when FreshEntityPolicy is set to ALLOW.
 * 
 * @author Frantisek Simancik
 * @author "Yevgeny Kazakov"
 * @author Peter Skocovsky
 * 
 * @param <T>
 *            the type of objects in this node
 */
public class FreshTaxonomyNode<T extends ElkEntity> extends FreshNode<T>
		implements TaxonomyNode<T> {

	protected final Taxonomy<T> taxonomy;

	public FreshTaxonomyNode(T member, Taxonomy<T> taxonomy) {
		super(member);
		this.taxonomy = taxonomy;
	}

	@Override
	public Taxonomy<T> getTaxonomy() {
		return taxonomy;
	}

	@Override
	public Set<? extends TaxonomyNode<T>> getDirectSuperNodes() {
		return Collections.singleton(taxonomy.getTopNode());
	}

	@Override
	public Set<? extends TaxonomyNode<T>> getAllSuperNodes() {
		return Collections.singleton(taxonomy.getTopNode());
	}

	@Override
	public Set<? extends TaxonomyNode<T>> getDirectSubNodes() {
		return Collections.singleton(taxonomy.getBottomNode());
	}

	@Override
	public Set<? extends TaxonomyNode<T>> getAllSubNodes() {
		return Collections.singleton(taxonomy.getBottomNode());
	}

}
