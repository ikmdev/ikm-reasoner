
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;

/**
 * Implements the {@link ElkObjectExactCardinalityQualified} interface by
 * wrapping instances of {@link OWLObjectExactCardinality}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectExactCardinalityUnqualifiedWrap<T extends OWLObjectExactCardinality>
		extends ElkClassExpressionWrap<T> implements
		ElkObjectExactCardinalityUnqualified {

	ElkObjectExactCardinalityUnqualifiedWrap(T owlObjectExactCardinality) {
		super(owlObjectExactCardinality);
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
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
