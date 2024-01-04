
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataComplementOf;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataComplementOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkDataComplementOf.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkDataComplementOfImpl extends ElkObjectImpl implements
		ElkDataComplementOf {

	private final ElkDataRange dataRange_;

	ElkDataComplementOfImpl(ElkDataRange range) {

		this.dataRange_ = range;
	}

	@Override
	public ElkDataRange getDataRange() {
		return dataRange_;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataComplementOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataComplementOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
