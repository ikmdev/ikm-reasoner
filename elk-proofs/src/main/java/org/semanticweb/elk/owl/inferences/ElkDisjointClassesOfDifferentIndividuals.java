
package org.semanticweb.elk.owl.inferences;

import java.util.ArrayList;
import java.util.Collections;



import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;

/**
 * Represents the inference:
 * 
 * <pre>
 *  DifferentIndividuals(a0 a1 ... an)
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *  DisjointClasses({a0} {a1} ... {an})
 * </pre>
 * 
 * @author Yevgeny Kazakov
 *
 */
public class ElkDisjointClassesOfDifferentIndividuals
		extends AbstractElkInference {

	public final static String NAME = "Different Individuals Translation";

	private final List<? extends ElkIndividual> different_;

	ElkDisjointClassesOfDifferentIndividuals(
			List<? extends ElkIndividual> different) {
		this.different_ = different;
	}

	public List<? extends ElkIndividual> getDifferent() {
		return different_;
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

	public ElkDifferentIndividualsAxiom getPremise(ElkObject.Factory factory) {
		return factory.getDifferentIndividualsAxiom(different_);
	}

	@Override
	public ElkDisjointClassesAxiom getConclusion(ElkObject.Factory factory) {
		List<ElkObjectOneOf> disjoint = new ArrayList<ElkObjectOneOf>(
				different_.size());
		for (ElkIndividual individual : different_) {
			disjoint.add(factory
					.getObjectOneOf(Collections.singletonList(individual)));
		}
		return factory.getDisjointClassesAxiom(disjoint);
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

		ElkDisjointClassesOfDifferentIndividuals getElkDisjointClassesOfDifferentIndividuals(
				List<? extends ElkIndividual> different);

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

		O visit(ElkDisjointClassesOfDifferentIndividuals inference);

	}

}
