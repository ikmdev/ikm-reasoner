
package org.semanticweb.elk.reasoner.taxonomy;


import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.reasoner.taxonomy.model.TaxonomyNode;


/**
 * @author Pavel Klinov
 *
 * pavel.klinov@uni-ulm.de
 * 
 * @param <T> 
 */
public interface TaxonomyNodeVisitor<T extends ElkEntity> {

	public void visit(TaxonomyNode<T> node, List<TaxonomyNode<T>> pathFromStart);
}
