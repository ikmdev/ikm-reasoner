
package org.semanticweb.elk.owlapi.wrapper;

import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataAllValuesFrom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataAllValuesFromVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyListRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;

/**
 * Implements the {@link ElkDataAllValuesFrom} interface by wrapping instances
 * of {@link OWLDataAllValuesFrom}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataAllValuesFromWrap<T extends OWLDataAllValuesFrom>
		extends ElkClassExpressionWrap<T> implements ElkDataAllValuesFrom {

	public ElkDataAllValuesFromWrap(T owlDataAllValuesFrom) {
		super(owlDataAllValuesFrom);
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		return Collections
				.singletonList(converter.convert(getProperty(owlObject)));
	}

	@Override
	public ElkDataRange getDataRange() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkDataAllValuesFromVisitor<O>) visitor);
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