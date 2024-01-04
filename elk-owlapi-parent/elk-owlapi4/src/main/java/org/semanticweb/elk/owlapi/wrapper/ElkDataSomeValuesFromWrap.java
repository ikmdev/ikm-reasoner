
package org.semanticweb.elk.owlapi.wrapper;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.interfaces.ElkDataSomeValuesFrom;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataSomeValuesFromVisitor;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;

/**
 * Implements the {@link ElkDataSomeValuesFrom} interface by wrapping instances
 * of {@link OWLDataSomeValuesFrom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataSomeValuesFromWrap<T extends OWLDataSomeValuesFrom> extends
		ElkClassExpressionWrap<T> implements ElkDataSomeValuesFrom {

	public ElkDataSomeValuesFromWrap(T owlDataSomeValuesFrom) {
		super(owlDataSomeValuesFrom);
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		return Collections.singletonList(converter.convert(getProperty(owlObject)));
	}

	@Override
	public ElkDataRange getDataRange() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return visitor.visit(this);
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