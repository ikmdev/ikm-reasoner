
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAssertionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAssertionAxiomVisitor;

/**
 * Implementation of {@link ElkObjectPropertyAssertionAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 * 
 */
public class ElkObjectPropertyAssertionAxiomImpl
		extends
		ElkPropertyAssertionAxiomImpl<ElkObjectPropertyExpression, ElkIndividual, ElkIndividual>
		implements ElkObjectPropertyAssertionAxiom {

	ElkObjectPropertyAssertionAxiomImpl(ElkObjectPropertyExpression property,
			ElkIndividual subject, ElkIndividual object) {
		super(property, subject, object);
	}

	@Override
	public <O> O accept(ElkPropertyAssertionAxiomVisitor<O> visitor) {
		return accept((ElkObjectPropertyAssertionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectPropertyAssertionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
