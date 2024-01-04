
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentDataPropertiesAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkEquivalentDataPropertiesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Equivalent_Data_Properties">Equivalent
 * Data Properties Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkEquivalentDataPropertiesAxiomImpl extends
		ElkDataPropertyExpressionListObject implements
		ElkEquivalentDataPropertiesAxiom {

	ElkEquivalentDataPropertiesAxiomImpl(
			List<? extends ElkDataPropertyExpression> equivalentDataPropertyExpressions) {
		super(equivalentDataPropertyExpressions);
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkEquivalentDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkEquivalentDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkEquivalentDataPropertiesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEquivalentDataPropertiesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
