
package org.semanticweb.elk.owl.implementation;

import org.semanticweb.elk.owl.interfaces.ElkAnnotation;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationProperty;
import org.semanticweb.elk.owl.interfaces.ElkAnnotationValue;
import org.semanticweb.elk.owl.visitors.ElkAnnotationVisitor;
import org.semanticweb.elk.owl.visitors.ElkObjectVisitor;

/**
 * @author Pavel Klinov
 *
 *         pavel.klinov@uni-ulm.de
 *
 */
public class ElkAnnotationImpl implements ElkAnnotation {

	private final ElkAnnotationProperty property_;
	private final ElkAnnotationValue value_;

	ElkAnnotationImpl(ElkAnnotationProperty property,
			ElkAnnotationValue value) {
		this.property_ = property;
		this.value_ = value;
	}

	@Override
	public <O> O accept(ElkAnnotationVisitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkObjectVisitor<O> visitor) {
		return accept((ElkAnnotationVisitor<O>) visitor);
	}

	@Override
	public ElkAnnotationProperty getProperty() {
		return property_;
	}

	@Override
	public ElkAnnotationValue getValue() {
		return value_;
	}

}
