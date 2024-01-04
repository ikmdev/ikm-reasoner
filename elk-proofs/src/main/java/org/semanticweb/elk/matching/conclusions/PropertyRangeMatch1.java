
package org.semanticweb.elk.matching.conclusions;



import org.semanticweb.elk.reasoner.saturation.conclusions.model.PropertyRange;

public class PropertyRangeMatch1
		extends AbstractObjectPropertyConclusionMatch<PropertyRange> {

	PropertyRangeMatch1(PropertyRange parent) {
		super(parent);
	}

	@Override
	public <O> O accept(ObjectPropertyConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		PropertyRangeMatch1 getPropertyRangeMatch1(PropertyRange parent);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(PropertyRangeMatch1 conclusionMatch);

	}

}
