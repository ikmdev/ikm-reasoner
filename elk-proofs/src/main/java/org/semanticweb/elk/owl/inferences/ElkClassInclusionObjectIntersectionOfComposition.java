
package org.semanticweb.elk.owl.inferences;

import java.util.ArrayList;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *   (1)        (n)
 *  C ⊑ C1 ... C ⊑ Cn
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *   C ⊑ C1 ⊓...⊓ Cn
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionObjectIntersectionOfComposition
		extends AbstractElkInference {

	public final static String NAME = "Intersection Composition";

	private final ElkClassExpression subExpression_;

	private final List<? extends ElkClassExpression> conjuncts_;

	ElkClassInclusionObjectIntersectionOfComposition(
			ElkClassExpression subExpression,
			List<? extends ElkClassExpression> conjuncts) {
		this.subExpression_ = subExpression;
		this.conjuncts_ = conjuncts;
	}

	ElkClassInclusionObjectIntersectionOfComposition(
			ElkClassExpression subExpression, ElkClassExpression firstConjunct,
			ElkClassExpression secondConjunct) {
		this.subExpression_ = subExpression;
		List<ElkClassExpression> conjuncts = new ArrayList<ElkClassExpression>(
				2);
		this.conjuncts_ = conjuncts;
		conjuncts.add(firstConjunct);
		conjuncts.add(secondConjunct);
	}

	public ElkClassExpression getSubExpression() {
		return subExpression_;
	}

	public List<? extends ElkClassExpression> getConjuncts() {
		return conjuncts_;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public int getPremiseCount() {
		return conjuncts_.size();
	}

	@Override
	public ElkSubClassOfAxiom getPremise(int index, ElkObject.Factory factory) {
		checkPremiseIndex(index);
		return factory.getSubClassOfAxiom(subExpression_,
				conjuncts_.get(index));
	}

	@Override
	public ElkSubClassOfAxiom getConclusion(ElkObject.Factory factory) {
		return factory.getSubClassOfAxiom(subExpression_,
				factory.getObjectIntersectionOf(conjuncts_));
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

		ElkClassInclusionObjectIntersectionOfComposition getElkClassInclusionObjectIntersectionOfComposition(
				ElkClassExpression subExpression,
				List<? extends ElkClassExpression> conjuncts);

		ElkClassInclusionObjectIntersectionOfComposition getElkClassInclusionObjectIntersectionOfComposition(
				ElkClassExpression subExpression,
				ElkClassExpression firstConjunct,
				ElkClassExpression secondConjunct);

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

		O visit(ElkClassInclusionObjectIntersectionOfComposition inference);

	}

}
