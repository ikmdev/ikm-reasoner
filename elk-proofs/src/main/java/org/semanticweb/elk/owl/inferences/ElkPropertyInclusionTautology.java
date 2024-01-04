 
package org.semanticweb.elk.owl.inferences;



import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.owl.interfaces.ElkSubObjectPropertyOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * 
 * ⎯⎯⎯⎯⎯⎯⎯⎯
 *  R ⊑ R
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkPropertyInclusionTautology extends AbstractElkInference {

	public final static String NAME = "Property Inclusion Tautology";

	private final ElkObjectPropertyExpression expression_;

	ElkPropertyInclusionTautology(ElkObjectPropertyExpression expression) {
		this.expression_ = expression;
	}

	public ElkObjectPropertyExpression getExpression() {
		return expression_;
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
	public ElkAxiom getPremise(int index, ElkObject.Factory factory) {
		return failGetPremise(index);
	}

	@Override
	public ElkSubObjectPropertyOfAxiom getConclusion(
			ElkObject.Factory factory) {
		return factory.getSubObjectPropertyOfAxiom(expression_, expression_);
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

		ElkPropertyInclusionTautology getElkPropertyInclusionTautology(
				ElkObjectPropertyExpression expression);

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

		O visit(ElkPropertyInclusionTautology inference);

	}

}
