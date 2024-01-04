
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointDataPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Disjoint_Data_Properties">Disjoint Data
 * Properties Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDisjointDataPropertiesAxiomImpl extends
		ElkDataPropertyExpressionListObject implements
		ElkDisjointDataPropertiesAxiom {

	ElkDisjointDataPropertiesAxiomImpl(
			List<? extends ElkDataPropertyExpression> disjointDataPropertyExpressions) {
		super(disjointDataPropertyExpressions);
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkDisjointDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDisjointDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDisjointDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointDataPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
