
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;

/**
 * Implements the {@link ElkObjectMaxCardinalityQualified} interface by wrapping
 * instances of {@link OWLObjectMaxCardinality}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectMaxCardinalityUnqualifiedWrap<T extends OWLObjectMaxCardinality>
		extends ElkClassExpressionWrap<T> implements
		ElkObjectMaxCardinalityUnqualified {

	ElkObjectMaxCardinalityUnqualifiedWrap(T owlObjectMaxCardinality) {
		super(owlObjectMaxCardinality);
	}

	@Override
	public int getCardinality() {
		return getCardinality(owlObject);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkClassExpressionVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
