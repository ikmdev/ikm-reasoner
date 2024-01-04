
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDisjointObjectPropertiesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointObjectPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Disjoint_Object_Properties">Disjoint
 * Object Properties Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDisjointObjectPropertiesAxiomImpl extends
		ElkObjectPropertyExpressionListObject implements
		ElkDisjointObjectPropertiesAxiom {

	ElkDisjointObjectPropertiesAxiomImpl(
			List<? extends ElkObjectPropertyExpression> disjointObjectPropertyExpressions) {
		super(disjointObjectPropertyExpressions);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDisjointObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDisjointObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDisjointObjectPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointObjectPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
