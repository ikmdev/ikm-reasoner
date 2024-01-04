
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataMaxCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMaxCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;

/**
 * Implements the {@link ElkDataMaxCardinalityQualified} interface by wrapping
 * instances of {@link OWLDataMaxCardinality}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataMaxCardinalityQualifiedWrap<T extends OWLDataMaxCardinality>
		extends ElkDataMaxCardinalityUnqualifiedWrap<T> implements
		ElkDataMaxCardinalityQualified {

	public ElkDataMaxCardinalityQualifiedWrap(T owlDataMaxCardinality) {
		super(owlDataMaxCardinality);
	}

	@Override
	public ElkDataRange getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMaxCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMaxCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}