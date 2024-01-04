
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;

/**
 * Implements the {@link ElkDataMinCardinalityQualified} interface by wrapping
 * instances of {@link OWLDataMinCardinality}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataMinCardinalityUnqualifiedWrap<T extends OWLDataMinCardinality> extends
		ElkClassExpressionWrap<T> implements ElkDataMinCardinalityUnqualified {

	public ElkDataMinCardinalityUnqualifiedWrap(T owlDataMinCardinality) {
		super(owlDataMinCardinality);
	}

	@Override
	public int getCardinality() {
		return getCardinality(owlObject);
	}

	@Override
	public ElkDataPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}