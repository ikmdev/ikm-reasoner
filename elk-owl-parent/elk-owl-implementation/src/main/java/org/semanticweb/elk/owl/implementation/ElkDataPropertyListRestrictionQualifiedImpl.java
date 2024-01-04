 
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyListRestrictionQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of {@link ElkDataPropertyListRestrictionQualified}
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 */
public abstract class ElkDataPropertyListRestrictionQualifiedImpl implements
		ElkDataPropertyListRestrictionQualified {

	private final List<? extends ElkDataPropertyExpression> dataProperties_;
	private final ElkDataRange dataRange_;

	ElkDataPropertyListRestrictionQualifiedImpl(
			List<? extends ElkDataPropertyExpression> dataProps,
			ElkDataRange dataRange) {
		this.dataProperties_ = dataProps;
		this.dataRange_ = dataRange;
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		return dataProperties_;
	}

	@Override
	public ElkDataRange getDataRange() {
		return dataRange_;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDataPropertyListRestrictionQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkDataPropertyListRestrictionQualifiedVisitor<O>) visitor);
	}
}