
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkLiteral;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;

/**
 * Implementation of {@link ElkDataPropertyAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkDataPropertyAssertionAxiomImpl
		extends
		ElkPropertyAssertionAxiomImpl<ElkDataPropertyExpression, ElkIndividual, ElkLiteral>
		implements ElkDataPropertyAssertionAxiom {

	ElkDataPropertyAssertionAxiomImpl(ElkDataPropertyExpression property,
			ElkIndividual subject, ElkLiteral object) {
		super(property, subject, object);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkDataPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDataPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
