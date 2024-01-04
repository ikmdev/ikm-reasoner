
package org.semanticweb.elk.reasoner.taxonomy;

import java.util.Set;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.predefined.PredefinedElkClassFactory;
import org.semanticweb.elk.reasoner.taxonomy.impl.AbstractDistinctBottomTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.impl.AbstractUpdateableGenericTaxonomy;
import org.semanticweb.elk.reasoner.taxonomy.impl.BottomGenericTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.impl.ConcurrentNodeStore;
import org.semanticweb.elk.reasoner.taxonomy.impl.NonBottomGenericTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.ComparatorKeyProvider;
import org.semanticweb.elk.reasoner.taxonomy.model.GenericTaxonomyNode;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNodeFactory;

/**
 * Class taxonomy that is suitable for concurrent processing. Taxonomy objects
 * are only constructed for consistent ontologies, and some consequences of this
 * are hardcoded here.
 * 
 * @author Yevgeny Kazakov
 * @author Frantisek Simancik
 * @author Markus Kroetzsch
 * @author Pavel Klinov
 * @author Peter Skocovsky
 */
public class ConcurrentClassTaxonomy
		extends AbstractUpdateableGenericTaxonomy<
				ElkClass,
				GenericTaxonomyNode.Projection<ElkClass>,
				NonBottomGenericTaxonomyNode.Projection<ElkClass>
		> {
	
	private final GenericTaxonomyNode.Projection<ElkClass> bottomNode_;
	
	public ConcurrentClassTaxonomy(final PredefinedElkClassFactory elkFactory,
			final ComparatorKeyProvider<ElkEntity> classKeyProvider) {
		super(
				new ConcurrentNodeStore<
						ElkClass,
						NonBottomGenericTaxonomyNode.Projection<ElkClass>
				>(classKeyProvider),
				new TaxonomyNodeFactory<
						ElkClass,
						NonBottomGenericTaxonomyNode.Projection<ElkClass>,
						AbstractDistinctBottomTaxonomy<
								ElkClass,
								GenericTaxonomyNode.Projection<ElkClass>,
								NonBottomGenericTaxonomyNode.Projection<ElkClass>
						>
				>() {
					@Override
					public NonBottomGenericTaxonomyNode.Projection<ElkClass> createNode(
							final Iterable<? extends ElkClass> members,
							final int size,
							final AbstractDistinctBottomTaxonomy<
									ElkClass,
									GenericTaxonomyNode.Projection<ElkClass>,
									NonBottomGenericTaxonomyNode.Projection<ElkClass>
							> taxonomy) {
						return new NonBottomGenericTaxonomyNode.Projection<ElkClass>(
								taxonomy, members, size);
					}
				},
				elkFactory.getOwlThing());
		this.bottomNode_ = new BottomGenericTaxonomyNode.Projection<ElkClass>(
				this, elkFactory.getOwlNothing());
	}

	@Override
	public GenericTaxonomyNode.Projection<ElkClass> getBottomNode() {
		return bottomNode_;
	}

	@Override
	protected Set<? extends GenericTaxonomyNode.Projection<ElkClass>> toTaxonomyNodes(
			final Set<? extends NonBottomGenericTaxonomyNode.Projection<ElkClass>> nodes) {
		return nodes;
	}
	
}
