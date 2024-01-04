
package org.semanticweb.elk.owl.inferences;



import java.util.ArrayList;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯ if L1 ⊆ L2
 *  ObjectOneOf(L1) ⊑ ObjectOneOf(L2)
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkClassInclusionObjectOneOfInclusion
		extends AbstractElkInference {

	public final static String NAME = "Sub-Enumeration";

	/**
	 * list L2 that contain all individuals
	 */
	private final List<? extends ElkIndividual> superIndividuals_;

	/**
	 * corresponding positions of elements in L1 within L2
	 */
	private final List<Integer> subIndividualPositions_;

	ElkClassInclusionObjectOneOfInclusion(
			List<? extends ElkIndividual> superIndividuals,
			List<Integer> subPositions) {
		this.superIndividuals_ = superIndividuals;
		this.subIndividualPositions_ = subPositions;
	}

	public List<? extends ElkIndividual> getSuperIndividuals() {
		return superIndividuals_;
	}

	public List<Integer> getSubIndividualPositions() {
		return subIndividualPositions_;
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
		List<ElkIndividual> subIndividuals = new ArrayList<ElkIndividual>(
				subIndividualPositions_.size());
		for (int pos : subIndividualPositions_) {
			subIndividuals.add(superIndividuals_.get(pos));
		}
		return factory.getSubClassOfAxiom(
				factory.getObjectOneOf(subIndividuals),
				factory.getObjectOneOf(superIndividuals_));
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

		ElkClassInclusionObjectOneOfInclusion getElkClassInclusionObjectOneOfInclusion(
				List<? extends ElkIndividual> superIndividuals,
				List<Integer> subPositions);

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

		O visit(ElkClassInclusionObjectOneOfInclusion inference);

	}

}
