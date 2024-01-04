
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubObjectPropertyOfAxiomVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Object_Subproperties">Object Subproperty
 * Axiom<a> in the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 */
public class ElkSubObjectPropertyOfAxiomImpl extends ElkObjectImpl implements
		ElkSubObjectPropertyOfAxiom {

	private final ElkSubObjectPropertyExpression subProperty_;
	private final ElkObjectPropertyExpression superProperty_;

	ElkSubObjectPropertyOfAxiomImpl(
			ElkSubObjectPropertyExpression subObjectPropertyExpression,
			ElkObjectPropertyExpression superObjectPropertyExpression) {
		this.subProperty_ = subObjectPropertyExpression;
		this.superProperty_ = superObjectPropertyExpression;
	}

	@Override
	public ElkSubObjectPropertyExpression getSubObjectPropertyExpression() {
		return subProperty_;
	}

	@Override
	public ElkObjectPropertyExpression getSuperObjectPropertyExpression() {
		return superProperty_;
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSubObjectPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSubObjectPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSubObjectPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubObjectPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
