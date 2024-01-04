
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubDataPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkDataPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubDataPropertyOfAxiomVisitor;

/**
 * ELK implementation of ElkSubDataPropertyOfAxiom.
 *
 * @author Markus Kroetzsch
 */
public class ElkSubDataPropertyOfAxiomImpl extends ElkObjectImpl implements
		ElkSubDataPropertyOfAxiom {

	private final ElkDataPropertyExpression subProperty_;
	private final ElkDataPropertyExpression superProperty_;

	ElkSubDataPropertyOfAxiomImpl(ElkDataPropertyExpression subProperty,
			ElkDataPropertyExpression superProperty) {
		this.subProperty_ = subProperty;
		this.superProperty_ = superProperty;
	}

	@Override
	public ElkDataPropertyExpression getSubDataPropertyExpression() {
		return subProperty_;
	}

	@Override
	public ElkDataPropertyExpression getSuperDataPropertyExpression() {
		return superProperty_;
	}

	@Override
	public <O> O accept(ElkDataPropertyAxiomVisitor<O> visitor) {
		return accept((ElkSubDataPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSubDataPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSubDataPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubDataPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
