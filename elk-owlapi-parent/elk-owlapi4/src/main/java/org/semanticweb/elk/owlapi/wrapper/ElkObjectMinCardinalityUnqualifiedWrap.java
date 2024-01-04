
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;

/**
 * Implements the {@link ElkObjectMaxCardinalityQualified} interface by wrapping
 * instances of {@link OWLObjectMinCardinality}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectMinCardinalityUnqualifiedWrap<T extends OWLObjectMinCardinality>
		extends ElkClassExpressionWrap<T> implements
		ElkObjectMinCardinalityUnqualified {

	ElkObjectMinCardinalityUnqualifiedWrap(T owlObjectMinCardinality) {
		super(owlObjectMinCardinality);
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
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
