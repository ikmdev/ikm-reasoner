 
package org.semanticweb.elk.owl.inferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.semanticweb.elk.owl.interfaces.ElkAxiom;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObject;
import org.semanticweb.elk.owl.interfaces.ElkObjectOneOf;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;

/**
 * Represents the inference:
 * 
 * <pre>
 *  EquivalentClasses({a0} {a1} ... {an})
 * ⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯
 *      SameIndividual(a0 a1 ... an)
 * </pre>
 * 
 * @author Peter Skocovsky
 */
public class ElkSameIndividualOfEquivalentClasses extends AbstractElkInference {

	public final static String NAME = "Same Individual Introduction";

	private final List<? extends ElkIndividual> same_;

	ElkSameIndividualOfEquivalentClasses(
			final List<? extends ElkIndividual> same) {
		this.same_ = same;
	}

	public List<? extends ElkIndividual> getSame() {
		return same_;
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
	public ElkAxiom getPremise(final int index,
			final ElkObject.Factory factory) {
		if (index == 0) {
			return getPremise(factory);
		}
		// else
		return failGetPremise(index);
	}

	public ElkEquivalentClassesAxiom getPremise(
			final ElkObject.Factory factory) {
		final List<ElkObjectOneOf> equivalent = new ArrayList<ElkObjectOneOf>(
				same_.size());
		for (final ElkIndividual individual : same_) {
			equivalent.add(factory
					.getObjectOneOf(Collections.singletonList(individual)));
		}
		return factory.getEquivalentClassesAxiom(equivalent);
	}

	@Override
	public ElkSameIndividualAxiom getConclusion(
			final ElkObject.Factory factory) {
		return factory.getSameIndividualAxiom(same_);
	}

	@Override
	public <O> O accept(final ElkInference.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Peter Skocovsky
	 */
	public interface Factory {

		ElkSameIndividualOfEquivalentClasses getElkSameIndividualOfEquivalentClasses(
				final List<? extends ElkIndividual> same);

	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Peter Skocovsky
	 *
	 * @param <O>
	 *            the type of the output
	 */
	interface Visitor<O> {

		O visit(final ElkSameIndividualOfEquivalentClasses inference);

	}

}
