
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDataUnionOf;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataUnionOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkDataUnionOf.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkDataUnionOfImpl extends ElkDataRangeListObject implements
		ElkDataUnionOf {

	ElkDataUnionOfImpl(List<? extends ElkDataRange> ranges) {
		super(ranges);
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataUnionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataUnionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataUnionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
