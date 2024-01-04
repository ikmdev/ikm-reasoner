
/**
 * @author Yevgeny Kazakov, Apr 8, 2011
 */
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkSubAnnotationPropertyOfAxiom;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;
import org.semanticweb.elk.owl.visitors.ElkSubAnnotationPropertyOfAxiomVisitor;

/**
 * ELK implementation of {@link ElkSubAnnotationPropertyOfAxiom}.
 * 
 * @author Yevgeny Kazakov
 * @author Markus Kroetzsch
 * 
 */
public class ElkSubAnnotationPropertyOfAxiomImpl extends ElkObjectImpl
		implements ElkSubAnnotationPropertyOfAxiom {

	private final ElkAnnotationProperty subProperty_;
	private final ElkAnnotationProperty superProperty_;

	ElkSubAnnotationPropertyOfAxiomImpl(ElkAnnotationProperty subProperty,
			ElkAnnotationProperty superProperty) {
		this.subProperty_ = subProperty;
		this.superProperty_ = superProperty;
	}

	@Override
	public ElkAnnotationProperty getSubAnnotationProperty() {
		return subProperty_;
	}

	@Override
	public ElkAnnotationProperty getSuperAnnotationProperty() {
		return superProperty_;
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkSubAnnotationPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAxiomVisitor<O> visitor) {
		return accept((ElkSubAnnotationPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkSubAnnotationPropertyOfAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkSubAnnotationPropertyOfAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

}
