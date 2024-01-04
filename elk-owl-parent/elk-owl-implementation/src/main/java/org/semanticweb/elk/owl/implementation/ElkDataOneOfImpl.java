
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataOneOf;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkDataOneOfVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataRangeVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * ELK implementation of ElkDataOneOf.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDataOneOfImpl extends ElkObjectListObject<ElkLiteral> implements
		ElkDataOneOf {

	ElkDataOneOfImpl(List<? extends ElkLiteral> members) {
		super(members);
	}

	@Override
	public List<? extends ElkLiteral> getLiterals() {
		return getObjects();
	}

	@Override
	public <O> O accept(ElkDataRangeVisitor<O> visitor) {
		return accept((ElkDataOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataOneOfVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataOneOfVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
