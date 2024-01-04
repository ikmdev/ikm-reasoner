
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkObjectMinCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectMinCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
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
public class ElkObjectMinCardinalityQualifiedWrap<T extends OWLObjectMinCardinality>
		extends ElkObjectMinCardinalityUnqualifiedWrap<T> implements
		ElkObjectMinCardinalityQualified {

	ElkObjectMinCardinalityQualifiedWrap(T owlObjectMinCardinality) {
		super(owlObjectMinCardinality);
	}

	@Override
	public ElkClassExpression getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectMinCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
