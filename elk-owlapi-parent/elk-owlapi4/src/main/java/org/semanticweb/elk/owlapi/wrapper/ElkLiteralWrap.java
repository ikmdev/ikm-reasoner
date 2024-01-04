
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDatatype;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkAnnotationValueVisitor;
import org.semanticweb.elk.owl.visitors.ElkLiteralVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.owlapi.model.OWLLiteral;

/**
 * Implements the {@link ElkLiteral} interface by wrapping instances of
 * {@link OWLLiteral}
 * 
 * @author "Yevgeny Kazakov"
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkLiteralWrap<T extends OWLLiteral> extends ElkObjectWrap<T>
		implements ElkLiteral {

	public ElkLiteralWrap(T owlLiteral) {
		super(owlLiteral);
	}

	@Override
	public String getLexicalForm() {
		return this.owlObject.getLiteral();
	}

	@Override
	public ElkDatatype getDatatype() {
		return converter.convert(this.owlObject.getDatatype());
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkLiteralVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkLiteralVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkAnnotationValueVisitor<O> visitor) {
		return accept((ElkLiteralVisitor<O>) visitor);
	}
}