
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyRangeAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *   (1)               (2)
 *  R ⊑ S  ObjectPropertyRange(S C)  
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *    ObjectPropertyRange(R C)
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkPropertyRangePropertyExpansion extends AbstractElkInference {

	public final static String NAME = "Property Range Expansion";

	private final ElkObjectPropertyExpression subProperty_, superProperty_;

	private final ElkClassExpression range_;

	ElkPropertyRangePropertyExpansion(ElkObjectPropertyExpression subProperty,
			ElkObjectPropertyExpression superProperty,
			ElkClassExpression range) {
		this.superProperty_ = superProperty;
		this.range_ = range;
		this.subProperty_ = subProperty;
	}

	public ElkObjectPropertyExpression getSubProperty() {
		return subProperty_;
	}

	public ElkObjectPropertyExpression getSuperProperty() {
		return superProperty_;
	}

	public ElkClassExpression getRange() {
		return range_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 2;
	}

	@Override
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		switch (index) {
		case 0:
			return getFirstPremise(factory);
		case 1:
			return getSecondPremise(factory);
		default:
			return failGetPremise(index);
		}
	}

	public ElkSubObjectPropertyOfAxiom getFirstPremise(
			ElkObject.Factory factory) {
		return factory.getSubObjectPropertyOfAxiom(subProperty_,
				superProperty_);
	}

	public ElkObjectPropertyRangeAxiom getSecondPremise(
			ElkObject.Factory factory) {
		return factory.getObjectPropertyRangeAxiom(superProperty_, range_);
	}

	@Override
	public ElkObjectPropertyRangeAxiom getConclusion(
			ElkObject.Factory factory) {
		return factory.getObjectPropertyRangeAxiom(subProperty_, range_);
	}

	public <O> O accept(Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkPropertyRangePropertyExpansion getElkPropertyRangePropertyExpansion(
				ElkObjectPropertyExpression subProperty,
				ElkObjectPropertyExpression superProperty,
				ElkClassExpression range);

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

		O visit(ElkPropertyRangePropertyExpansion inference);

	}

}
