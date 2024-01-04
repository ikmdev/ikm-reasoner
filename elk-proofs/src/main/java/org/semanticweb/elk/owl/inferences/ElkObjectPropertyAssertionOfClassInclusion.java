
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  {a} ⊑ ∃R.{b}
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *     R(a,b)
 * </pre>
 * 
 * @author Peter Skocovsky
 */
public class ElkObjectPropertyAssertionOfClassInclusion
		extends AbstractElkInference {

	public final static String NAME = "Property Assertion Introduction";

	private final ElkIndividual subject_, object_;

	private final ElkObjectPropertyExpression property_;

	ElkObjectPropertyAssertionOfClassInclusion(final ElkIndividual subject,
			final ElkObjectPropertyExpression property,
			final ElkIndividual object) {
		this.subject_ = subject;
		this.property_ = property;
		this.object_ = object;
	}

	public ElkIndividual getSubject() {
		return subject_;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}

	public ElkIndividual getObject() {
		return object_;
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
	public ElkAxiom getPremise(final int index,
			final ElkObject.Factory factory) {
		if (index == 0) {
			return getPremise(factory);
		}
		// else
		return failGetPremise(index);
	}

	public ElkSubClassOfAxiom getPremise(final ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(factory.getObjectOneOf(subject_),
				factory.getObjectSomeValuesFrom(property_,
						factory.getObjectOneOf(object_)));
	}

	@Override
	public ElkObjectPropertyAssertionAxiom getConclusion(
			final ElkObject.Factory factory) {
		return factory.getObjectPropertyAssertionAxiom(property_, subject_,
				object_);
	}

	@Override
	public <O> O accept(final ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Peter Skocovsky
	 */
	public interface Factory {

		ElkObjectPropertyAssertionOfClassInclusion getElkObjectPropertyAssertionOfClassInclusion(
				ElkIndividual subject, ElkObjectPropertyExpression property,
				ElkIndividual object);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkObjectPropertyAssertionOfClassInclusion inference);

	}

}
