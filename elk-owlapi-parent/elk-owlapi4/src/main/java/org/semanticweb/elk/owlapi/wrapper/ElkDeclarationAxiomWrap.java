
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDeclarationAxiomVisitor;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;

/**
 * Implements the {@link ElkDeclarationAxiom} interface by wrapping instances of
 * {@link OWLDeclarationAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkDeclarationAxiomWrap<T extends OWLDeclarationAxiom> extends
		ElkAxiomWrap<T> implements ElkDeclarationAxiom {

	public ElkDeclarationAxiomWrap(T owlDeclarationAxiom) {
		super(owlDeclarationAxiom);
	}

	@Override
	public ElkEntity getEntity() {
		return converter.convert(this.owlObject.getEntity());
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDeclarationAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDeclarationAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
