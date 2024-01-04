
/**
 * @author Yevgeny Kazakov, Jul 3, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDeclarationAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEntity;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDeclarationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to a <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Entity_Declarations_and_Typing"
 * >Declaration Axiom<a> in the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 */
public class ElkDeclarationAxiomImpl extends ElkObjectImpl implements
		ElkDeclarationAxiom {

	private final ElkEntity entity_;

	ElkDeclarationAxiomImpl(ElkEntity entity) {
		this.entity_ = entity;
	}

	@Override
	public ElkEntity getEntity() {
		return entity_;
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDeclarationAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDeclarationAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDeclarationAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
