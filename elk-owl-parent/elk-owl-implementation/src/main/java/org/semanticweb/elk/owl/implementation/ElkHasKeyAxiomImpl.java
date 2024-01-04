
package org.semanticweb.elk.owl.implementation;

import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDataPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkHasKeyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkHasKeyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * Implementation of {@link ElkHasKeyAxiom}
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 */
public class ElkHasKeyAxiomImpl implements ElkHasKeyAxiom {

	private final ElkClassExpression classExp_;
	private final List<? extends ElkObjectPropertyExpression> objectPropExprs_;
	private final List<? extends ElkDataPropertyExpression> dataPropExprs_;

	ElkHasKeyAxiomImpl(ElkClassExpression object,
			List<? extends ElkObjectPropertyExpression> objectPropertyKeys,
			List<? extends ElkDataPropertyExpression> dataPropertyKeys) {
		classExp_ = object;
		objectPropExprs_ = objectPropertyKeys;
		dataPropExprs_ = dataPropertyKeys;
	}

	@Override
	public ElkClassExpression getClassExpression() {
		return classExp_;
	}

	@Override
	public List<? extends ElkObjectPropertyExpression> getObjectPropertyExpressions() {
		return objectPropExprs_;
	}

	@Override
	public List<? extends ElkDataPropertyExpression> getDataPropertyExpressions() {
		return dataPropExprs_;
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkHasKeyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkHasKeyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkHasKeyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}
}