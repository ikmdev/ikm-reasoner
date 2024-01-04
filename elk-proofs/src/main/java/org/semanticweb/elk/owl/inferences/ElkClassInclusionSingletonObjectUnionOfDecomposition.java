
package org.semanticweb.elk.owl.inferences;



import java.util.Collections;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  ObjectUnionOf(D) ⊑ D
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionSingletonObjectUnionOfDecomposition
		extends AbstractElkInference {

	public final static String NAME = "Singleton Disjunction Decomposition";

	private final ElkClassExpression disjunct_;

	ElkClassInclusionSingletonObjectUnionOfDecomposition(
			ElkClassExpression disjunct) {
		this.disjunct_ = disjunct;
	}

	public ElkClassExpression getDisjunct() {
		return disjunct_;
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
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(
				factory.getObjectUnionOf(Collections.singletonList(disjunct_)),
				disjunct_);
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

		ElkClassInclusionSingletonObjectUnionOfDecomposition getElkClassInclusionSingletonObjectUnionOfDecomposition(
				ElkClassExpression disjunct);

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

		O visit(ElkClassInclusionSingletonObjectUnionOfDecomposition inference);

	}

}
