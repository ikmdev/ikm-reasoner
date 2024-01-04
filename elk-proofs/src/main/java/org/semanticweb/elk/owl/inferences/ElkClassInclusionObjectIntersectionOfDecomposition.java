
package org.semanticweb.elk.owl.inferences;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  D1 ⊓ ... ⊓ Dn ⊑ Di
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionObjectIntersectionOfDecomposition
		extends AbstractElkInference {

	public final static String NAME = "Intersection Decomposition";

	private final List<? extends ElkClassExpression> conjuncts_;

	private final int conjunctPos_;

	ElkClassInclusionObjectIntersectionOfDecomposition(
			List<? extends ElkClassExpression> conjuncts, int conjunctPos) {
		this.conjuncts_ = conjuncts;
		this.conjunctPos_ = conjunctPos;
	}

	public List<? extends ElkClassExpression> getConjuncts() {
		return conjuncts_;
	}

	public int getConjunctPos() {
		return conjunctPos_;
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
		return factory.getSubClassOfAxiom(
				factory.getObjectIntersectionOf(conjuncts_),
				conjuncts_.get(conjunctPos_));
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

		ElkClassInclusionObjectIntersectionOfDecomposition getElkClassInclusionObjectIntersectionOfDecomposition(
				List<? extends ElkClassExpression> conjuncts, int conjunctPos);

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

		O visit(ElkClassInclusionObjectIntersectionOfDecomposition inference);

	}

}
