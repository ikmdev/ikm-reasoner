
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkEquivalentClassesAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Equivalent_Classes">Equivalent Class
 * Axiom<a> in the OWL 2 specification.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 */
public class ElkEquivalentClassesAxiomImpl extends ElkClassExpressionListObject
		implements ElkEquivalentClassesAxiom {

	ElkEquivalentClassesAxiomImpl(
			List<? extends ElkClassExpression> equivalentClassExpressions) {
		super(equivalentClassExpressions);
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkEquivalentClassesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkEquivalentClassesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkEquivalentClassesAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkEquivalentClassesAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
