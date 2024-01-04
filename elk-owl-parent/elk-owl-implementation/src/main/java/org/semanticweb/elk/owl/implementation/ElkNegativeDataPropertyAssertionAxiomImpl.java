
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.interfaces.ElkNegativeDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.visitors.ElkNegativeDataPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;

/**
 * Implementation of {@link ElkNegativeDataPropertyAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkNegativeDataPropertyAssertionAxiomImpl
		extends
		ElkPropertyAssertionAxiomImpl<ElkDataPropertyExpression, ElkIndividual, ElkLiteral>
		implements ElkNegativeDataPropertyAssertionAxiom {

	ElkNegativeDataPropertyAssertionAxiomImpl(
			ElkDataPropertyExpression property, ElkIndividual subject,
			ElkLiteral object) {
		super(property, subject, object);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkNegativeDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkNegativeDataPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
