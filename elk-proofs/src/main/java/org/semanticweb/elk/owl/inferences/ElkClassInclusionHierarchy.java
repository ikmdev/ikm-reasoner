
package org.semanticweb.elk.owl.inferences;



import java.util.Arrays;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *    (1)           (n)
 *  C0 ⊑ C1 ... Cn-1 ⊑ Cn
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *        C0 ⊑ Cn
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionHierarchy extends AbstractElkInference {

	public final static String NAME = "Class Hierarchy";

	private final List<? extends ElkClassExpression> expressions_;

	ElkClassInclusionHierarchy(List<? extends ElkClassExpression> expressions) {
		this.expressions_ = expressions;
	}

	ElkClassInclusionHierarchy(ElkClassExpression... expressions) {
		this.expressions_ = Arrays.asList(expressions);
	}

	public List<? extends ElkClassExpression> getExpressions() {
		return expressions_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return expressions_.size() - 1;
	}

	@Override
	public ElkSubClassOfAxiom getPremise(int index, ElkObject.Factory factory) {
		checkPremiseIndex(index);
		// else
		return factory.getSubClassOfAxiom(expressions_.get(index),
				expressions_.get(index + 1));
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(expressions_.get(0),
				expressions_.get(expressions_.size() - 1));
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

		ElkClassInclusionHierarchy getElkClassInclusionHierarchy(
				ElkClassExpression... expressions);

		ElkClassInclusionHierarchy getElkClassInclusionHierarchy(
				List<? extends ElkClassExpression> expressions);

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

		O visit(ElkClassInclusionHierarchy inference);

	}

}
