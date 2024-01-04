
package org.semanticweb.elk.owlapi.wrapper;

import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.visitors.ElkObjectPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkReflexiveObjectPropertyAxiomVisitor;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;

/**
 * Implements the {@link ElkReflexiveObjectPropertyAxiom} interface by wrapping
 * instances of {@link OWLReflexiveObjectPropertyAxiom}
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <T>
 *            the type of the wrapped object
 */
public class ElkReflexiveObjectPropertyAxiomWrap<T extends OWLReflexiveObjectPropertyAxiom>
		extends ElkObjectPropertyAxiomWrap<T> implements
		ElkReflexiveObjectPropertyAxiom {

	public ElkReflexiveObjectPropertyAxiomWrap(T owlReflexiveObjectPropertyAxiom) {
		super(owlReflexiveObjectPropertyAxiom);
	}

	@Override
	public ElkObjectPropertyExpression getProperty() {
		return converter.convert(getProperty(owlObject));
	}

	@Override
	public <O> O accept(ElkObjectPropertyAxiomVisitor<O> visitor) {
		return accept((ElkReflexiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyAxiomVisitor<O> visitor) {
		return accept((ElkReflexiveObjectPropertyAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkReflexiveObjectPropertyAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
