
package org.semanticweb.elk.owl.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  ReflexiveObjectProperty(R)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *        ⊤ ⊑ ∃R.Self
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionOfReflexiveObjectProperty
		extends AbstractElkInference {

	public final static String NAME = "Reflexive Property Transaltion";

	private final ElkObjectPropertyExpression property_;

	ElkClassInclusionOfReflexiveObjectProperty(
			ElkObjectPropertyExpression property) {
		this.property_ = property;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 1;
	}

	@Override
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		if (index == 0) {
			return getPremise(factory);
		}
		// else
		return failGetPremise(index);
	}

	public ElkReflexiveObjectPropertyAxiom getPremise(
			ElkObject.Factory factory) {
		return factory.getReflexiveObjectPropertyAxiom(property_);
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(factory.getOwlThing(),
				factory.getObjectHasSelf(property_));
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

		ElkClassInclusionOfReflexiveObjectProperty getElkClassInclusionOfReflexiveObjectProperty(
				ElkObjectPropertyExpression property);
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

		O visit(ElkClassInclusionOfReflexiveObjectProperty inference);

	}

}
