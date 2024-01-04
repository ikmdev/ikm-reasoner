
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkNegativeObjectPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;

/**
 * Implementation of {@link ElkNegativeObjectPropertyAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkNegativeObjectPropertyAssertionAxiomImpl
		extends
		ElkPropertyAssertionAxiomImpl<ElkObjectPropertyExpression, ElkIndividual, ElkIndividual>
		implements ElkNegativeObjectPropertyAssertionAxiom {

	ElkNegativeObjectPropertyAssertionAxiomImpl(
			ElkObjectPropertyExpression property, ElkIndividual subject,
			ElkIndividual object) {
		super(property, subject, object);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(
			ElkNegativeObjectPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
