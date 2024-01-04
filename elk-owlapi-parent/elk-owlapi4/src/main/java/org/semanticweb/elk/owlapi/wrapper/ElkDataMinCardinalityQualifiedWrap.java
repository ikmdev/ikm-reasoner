
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDataMinCardinalityQualified;
import org.semanticweb.elk.owl.interfaces.ElkDataRange;
import org.semanticweb.elk.owl.visitors.ElkCardinalityRestrictionQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataMinCardinalityQualifiedVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRestrictionQualifiedVisitor;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;

/**
 * Implements the {@link ElkDataMinCardinalityQualified} interface by wrapping
 * qualified instances of {@link OWLDataMinCardinality}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDataMinCardinalityQualifiedWrap<T extends OWLDataMinCardinality>
		extends ElkDataMinCardinalityUnqualifiedWrap<T> implements
		ElkDataMinCardinalityQualified {

	public ElkDataMinCardinalityQualifiedWrap(T owlDataMinCardinality) {
		super(owlDataMinCardinality);
	}

	@Override
	public ElkDataRange getFiller() {
		return converter.convert(getFiller(owlObject));
	}

	@Override
	public <O> O accept(ElkCardinalityRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRestrictionQualifiedVisitor<O> visitor) {
		return accept((ElkDataMinCardinalityQualifiedVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataMinCardinalityQualifiedVisitor<O> visitor) {
		return visitor.visit(this);
	}

}