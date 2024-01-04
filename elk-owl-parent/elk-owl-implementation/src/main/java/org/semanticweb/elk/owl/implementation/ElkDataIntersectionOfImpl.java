
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataIntersectionOf;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataIntersectionOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkDataIntersectionOf.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkDataIntersectionOfImpl extends ElkDataRangeListObject implements
		ElkDataIntersectionOf {

	ElkDataIntersectionOfImpl(List<? extends ElkDataRange> ranges) {
		super(ranges);
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataIntersectionOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataIntersectionOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
