
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
 *  ObjectPropertyDomain(R C)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *         ∃R.⊤ ⊑ C
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionOfObjectPropertyDomain
		extends AbstractElkInference {

	private final static String NAME = "Property Domain Transaltion";

	private final ElkObjectPropertyExpression property_;

	private final ElkClassExpression domain_;

	ElkClassInclusionOfObjectPropertyDomain(
			ElkObjectPropertyExpression property, ElkClassExpression domain) {
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
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		if (index == 0) {
			return getPremise(factory);
		}
		// else
		return failGetPremise(index);
	}

	public ElkObjectPropertyDomainAxiom getPremise(ElkObject.Factory factory) {
		return factory.getObjectPropertyDomainAxiom(property_, domain_);
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(factory.getObjectSomeValuesFrom(
				property_, factory.getOwlThing()), domain_);
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

		ElkClassInclusionOfObjectPropertyDomain getElkClassInclusionOfObjectPropertyDomain(
				ElkObjectPropertyExpression property,
				ElkClassExpression domain);
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

		O visit(ElkClassInclusionOfObjectPropertyDomain inference);

	}

}
