
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyDomainAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *         ∃R.⊤ ⊑ C
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ObjectPropertyDomain(R C)
 * </pre>
 * 
 * @author Peter Skocovsky
 */
public class ElkObjectPropertyDomainOfClassInclusion
		extends AbstractElkInference {

	private final static String NAME = "Property Domain Introduction";

	private final ElkObjectPropertyExpression property_;

	private final ElkClassExpression domain_;

	ElkObjectPropertyDomainOfClassInclusion(
			final ElkObjectPropertyExpression property,
			final ElkClassExpression domain) {
		this.property_ = property;
		this.domain_ = domain;
	}

	public ElkObjectPropertyExpression getProperty() {
		return property_;
	}

	public ElkClassExpression getDomain() {
		return domain_;
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
		return factory.getSubClassOfAxiom(factory.getObjectSomeValuesFrom(
				property_, factory.getOwlThing()), domain_);
	}

	@Override
	public ElkObjectPropertyDomainAxiom getConclusion(
			final ElkObject.Factory factory) {
		return factory.getObjectPropertyDomainAxiom(property_, domain_);
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

		ElkObjectPropertyDomainOfClassInclusion getElkObjectPropertyDomainOfClassInclusion(
				ElkObjectPropertyExpression property,
				ElkClassExpression domain);
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

		O visit(ElkObjectPropertyDomainOfClassInclusion inference);

	}

}
