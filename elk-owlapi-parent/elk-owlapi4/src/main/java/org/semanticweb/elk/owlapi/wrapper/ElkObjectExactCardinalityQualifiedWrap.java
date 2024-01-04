 
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectExactCardinalityQualified;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectExactCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;

/**
 * Implements the {@link ElkObjectExactCardinalityQualified} interface by
 * wrapping qualified instances of {@link OWLObjectExactCardinality}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkObjectExactCardinalityQualifiedWrap<T extends OWLObjectExactCardinality>
		extends ElkObjectExactCardinalityUnqualifiedWrap<T> implements
		ElkObjectExactCardinalityQualified {

	ElkObjectExactCardinalityQualifiedWrap(T owlObjectExactCardinality) {
		super(owlObjectExactCardinality);
	}

	@Override
	public ElkClassExpression getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkObjectExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectExactCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
