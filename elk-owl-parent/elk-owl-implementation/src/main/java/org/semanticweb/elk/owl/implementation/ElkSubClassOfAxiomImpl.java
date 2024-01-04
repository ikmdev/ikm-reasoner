
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubClassOfAxiomVisitor;

/**
 * Corresponds to a <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Subclass_Axioms">Subclass Axiom<a> in the
 * OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * 
 */
public class ElkSubClassOfAxiomImpl extends ElkObjectImpl implements
		ElkSubClassOfAxiom {

	private final ElkClassExpression subClassExpression_,
			superClassExpression_;

	ElkSubClassOfAxiomImpl(ElkClassExpression subClassExpression,
			ElkClassExpression superClassExpression) {
		this.subClassExpression_ = subClassExpression;
		this.superClassExpression_ = superClassExpression;
	}

	@Override
	public ElkClassExpression getSubClassExpression() {
		return subClassExpression_;
	}

	@Override
	public ElkClassExpression getSuperClassExpression() {
		return superClassExpression_;
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkSubClassOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSubClassOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSubClassOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubClassOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
