
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  {a} ⊑ C
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *    C(a)
 * </pre>
 * 
 * @author Peter Skocovsky
 */
public class ElkClassAssertionOfClassInclusion extends AbstractElkInference {

	private final static String NAME_ = "Class Assertion Introduction";

	private final ElkIndividual instance_;

	private final ElkClassExpression type_;

	ElkClassAssertionOfClassInclusion(final ElkIndividual instance,
			final ElkClassExpression type) {
		this.instance_ = instance;
		this.type_ = type;
	}

	public ElkIndividual getInstance() {
		return instance_;
	}

	public ElkClassExpression getType() {
		return type_;
	}

	@Override
	public String getName() {
		return NAME_;
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
		return factory.getSubClassOfAxiom(factory.getObjectOneOf(instance_),
				type_);
	}

	@Override
	public ElkClassAssertionAxiom getConclusion(
			final ElkObject.Factory factory) {
		return factory.getClassAssertionAxiom(type_, instance_);
	}

	@Override
	public <O> O accept(final ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances.
	 * 
	 * @author Peter Skocovsky
	 */
	public interface Factory {

		ElkClassAssertionOfClassInclusion getElkClassAssertionOfClassInclusion(
				ElkIndividual instance, ElkClassExpression type);
	}

	/**
	 * The visitor pattern for instances.
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(ElkClassAssertionOfClassInclusion inference);

	}

}
