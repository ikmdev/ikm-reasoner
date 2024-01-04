
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationPropertyRangeAxiom;
import org.semanticweb.elk.owl.iris.ElkIri;
import org.semanticweb.elk.owl.visitors.ElkAnnotationAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkAnnotationPropertyRangeAxiomVisitor;
import org.semanticweb.elk.owl.visitors.ElkPropertyRangeAxiomVisitor;

/**
 * Implementation of {@link ElkAnnotationPropertyRangeAxiom}.
 * 
 * @author Markus Kroetzsch
 * @author "Yevgeny Kazakov"
 */
public class ElkAnnotationPropertyRangeAxiomImpl extends
		ElkPropertyRangeAxiomImpl<ElkAnnotationProperty, ElkIri> implements
		ElkAnnotationPropertyRangeAxiom {

	ElkAnnotationPropertyRangeAxiomImpl(ElkAnnotationProperty property,
			ElkIri range) {
		super(property, range);
	}

	@Override
	public <O> O accept(ElkAnnotationPropertyRangeAxiomVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkAnnotationAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyRangeAxiomVisitor<O>) visitor);
	}

	@Override
	public <O> O accept(ElkPropertyRangeAxiomVisitor<O> visitor) {
		return accept((ElkAnnotationPropertyRangeAxiomVisitor<O>) visitor);
	}

}
