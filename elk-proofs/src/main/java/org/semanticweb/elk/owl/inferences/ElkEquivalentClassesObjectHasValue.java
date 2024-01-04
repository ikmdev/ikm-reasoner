
package org.semanticweb.elk.owl.inferences;



import java.util.Collections;

import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ObjectHasValue(R a) ≡ ∃R.{a}
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkEquivalentClassesObjectHasValue extends AbstractElkInference {

	public final static String NAME = "ObjectHasValue Translation";

	private final ElkObjectPropertyExpression property_;

	private final ElkIndividual value_;

	ElkEquivalentClassesObjectHasValue(ElkObjectPropertyExpression property,
			ElkIndividual value) {
		this.property_ = property;
		this.value_ = value;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}

	public ElkIndividual getValue() {
		return value_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return 0;
	}

	@Override
	public ElkSubClassOfAxiom getPremise(int index, ElkObject.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public ElkEquivalentClassesAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getEquivalentClassesAxiom(
				factory.getObjectHasValue(getProperty(), getValue()),
				factory.getObjectSomeValuesFrom(getProperty(),
						factory.getObjectOneOf(
								Collections.singletonList(getValue()))));
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

		ElkEquivalentClassesObjectHasValue getElkEquivalentClassesObjectHasValue(
				ElkObjectPropertyExpression property, ElkIndividual value);

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

		O visit(ElkEquivalentClassesObjectHasValue inference);

	}

}
