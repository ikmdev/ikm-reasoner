
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkInverseObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkInverseObjectPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Inverse_Object_Properties_2">Inverse
 * Object Properties Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 * 
 */
public class ElkInverseObjectPropertiesAxiomImpl extends ElkObjectImpl
		implements ElkInverseObjectPropertiesAxiom {

	private final ElkObjectPropertyExpression first_;
	private final ElkObjectPropertyExpression second_;

	ElkInverseObjectPropertiesAxiomImpl(ElkObjectPropertyExpression first,
			ElkObjectPropertyExpression second) {
		this.first_ = first;
		this.second_ = second;
	}

	@Override
	public ElkObjectPropertyExpression getFirstObjectPropertyExpression() {
		return first_;
	}

	@Override
	public ElkObjectPropertyExpression getSecondObjectPropertyExpression() {
		return second_;
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkInverseObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkInverseObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkInverseObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkInverseObjectPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
