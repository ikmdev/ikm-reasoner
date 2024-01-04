
package org.semanticweb.elk.reasoner.saturation.properties.inferences;



import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.ObjectPropertyConclusion;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;
import org.semanticweb.elk.reasoner.tracing.TracingInferencePrinter;

public abstract class AbstractPropertyRangeInference extends
		AbstractObjectPropertyInference implements PropertyRangeInference {

	private final IndexedObjectProperty property_;

	private final IndexedClassExpression range_;

	protected AbstractPropertyRangeInference(IndexedObjectProperty property,
			IndexedClassExpression range) {
		this.property_ = property;
		this.range_ = range;
	}

	public IndexedObjectProperty getProperty() {
		return property_;
	}

	public IndexedClassExpression getRange() {
		return range_;
	}

	@Override
	public PropertyRange getConclusion(
			ObjectPropertyConclusion.Factory factory) {
		return factory.getPropertyRange(getProperty(), getRange());
	}

	// we assume that different objects represent different inferences
	@Override
	public int hashCode() {
		return System.identityHashCode(this);
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	@Override
	public String toString() {
		return TracingInferencePrinter.toString(this);
	}

	@Override
	public final <O> O accept(ObjectPropertyInference.Visitor<O> visitor) {
		return accept((PropertyRangeInference.Visitor<O>) visitor);
	}

}
