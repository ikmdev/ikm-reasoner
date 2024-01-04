
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDataSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataSomeValuesFromVisitor;

/**
 * Implementation of {@link ElkDataSomeValuesFrom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataSomeValuesFromImpl extends
		ElkDataPropertyListRestrictionQualifiedImpl implements
		ElkDataSomeValuesFrom {

	ElkDataSomeValuesFromImpl(
			List<? extends ElkDataPropertyExpression> properties,
			ElkDataRange range) {
		super(properties, range);
	}

	@Override
	public <O> O accept(
			ElkDataPropertyListRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataSomeValuesFromVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataSomeValuesFromVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
