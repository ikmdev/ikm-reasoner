
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMaxCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;

/**
 * Implements the {@link ElkObjectMaxCardinalityQualified} interface by wrapping
 * qualified instances of {@link OWLObjectMaxCardinality}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectMaxCardinalityQualifiedWrap<T extends OWLObjectMaxCardinality>
		extends ElkObjectMaxCardinalityUnqualifiedWrap<T> implements
		ElkObjectMaxCardinalityQualified {

	ElkObjectMaxCardinalityQualifiedWrap(T owlObjectMaxCardinality) {
		super(owlObjectMaxCardinality);
	}

	@Override
	public ElkClassExpression getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMaxCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
