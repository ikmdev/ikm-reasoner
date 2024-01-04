
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkClassAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDisjointUnionAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Corresponds to an <a href=
 * "http://www.w3.org/TR/owl2-syntax/#Disjoint_Union_of_Class_Expressions"
 * >Disjoint Union of Class Expressions Axiom<a> in the OWL 2 specification.
 * 
 * @author Markus Kroetzsch
 */
public class ElkDisjointUnionAxiomImpl extends ElkClassExpressionListObject
		implements ElkDisjointUnionAxiom {

	private final ElkClass definedClass_;

	ElkDisjointUnionAxiomImpl(ElkClass definedClass,
			List<? extends ElkClassExpression> disjointClassExpressions) {
		super(disjointClassExpressions);
		this.definedClass_ = definedClass;
	}

	@Override
	public ElkClass getDefinedClass() {
		return definedClass_;
	}

	@Override
	public <O> O accept(ElkClassAxiomVisitor<O> visitor) {
		return accept((ElkDisjointUnionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkDisjointUnionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkDisjointUnionAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkDisjointUnionAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
