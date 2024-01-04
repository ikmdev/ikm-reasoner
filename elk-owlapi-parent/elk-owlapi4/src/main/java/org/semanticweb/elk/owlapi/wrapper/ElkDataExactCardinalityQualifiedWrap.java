 
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataExactCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataExactCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;

/**
 * Implements the {@link ElkDataExactCardinalityQualified} interface by wrapping
 * qualified instances of {@link OWLDataExactCardinality}.
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataExactCardinalityQualifiedWrap<T extends OWLDataExactCardinality>
		extends ElkDataExactCardinalityUnqualifiedWrap<T> implements
		ElkDataExactCardinalityQualified {

	public ElkDataExactCardinalityQualifiedWrap(T owlDataExactCardinality) {
		super(owlDataExactCardinality);
	}

	@Override
	public ElkDataRange getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataExactCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataExactCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}