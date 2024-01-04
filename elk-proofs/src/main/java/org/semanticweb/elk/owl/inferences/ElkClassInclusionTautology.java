
package org.semanticweb.elk.owl.inferences;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * 
 * ⎯⎯⎯⎯⎯⎯⎯⎯
 *  C ⊑ C
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionTautology extends AbstractElkInference {

	public final static String NAME = "Class Inclusion Tautology";

	private final ElkClassExpression expression_;

	ElkClassInclusionTautology(ElkClassExpression expression) {
		this.expression_ = expression;
	}

	public ElkClassExpression getExpression() {
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
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(expression_, expression_);
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

		ElkClassInclusionTautology getElkClassInclusionTautology(
				ElkClassExpression expression);

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

		O visit(ElkClassInclusionTautology inference);

	}

}
