
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkDataAllValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;

/**
 * Implementation of {@link ElkDataAllValuesFrom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataAllValuesFromImpl extends
		ElkDataPropertyListRestrictionQualifiedImpl implements
		ElkDataAllValuesFrom {

	ElkDataAllValuesFromImpl(
			List<? extends ElkDataPropertyExpression> properties,
			ElkDataRange range) {
		super(properties, range);
	}

	@Override
	public <O> O accept(
			ElkDataPropertyListRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataAllValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataAllValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
