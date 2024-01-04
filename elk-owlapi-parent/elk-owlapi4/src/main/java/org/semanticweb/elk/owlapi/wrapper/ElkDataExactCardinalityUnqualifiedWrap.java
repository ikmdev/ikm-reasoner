
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityUnqualified;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassExpressionVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityUnqualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionVisitor;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;

/**
 * Implements the {@link ElkDataExactCardinalityUnqualified} interface by
 * wrapping instances of {@link OWLDataExactCardinality}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataExactCardinalityUnqualifiedWrap<T extends OWLDataExactCardinality>
		extends ElkClassExpressionWrap<T>
		implements ElkDataExactCardinalityUnqualified {

	public ElkDataExactCardinalityUnqualifiedWrap(T owlDataExactCardinality) {
		super(owlDataExactCardinality);
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
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityUnqualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityUnqualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}
}