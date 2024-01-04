
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.owl.predefined.PredefinedElkIris;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionOwlThing;

public class SubClassInclusionOwlThingMatch1
		extends AbstractInferenceMatch<SubClassInclusionOwlThing> {

	private final IndexedContextRootMatch originMatch_;

	SubClassInclusionOwlThingMatch1(SubClassInclusionOwlThing parent,
			SubClassInclusionComposedMatch1 conclusionMatch) {
		super(parent);
		originMatch_ = conclusionMatch.getDestinationMatch();
		ElkClass subsumerMatch = conclusionMatch.getSubsumerElkClassMatch();
		if (!subsumerMatch.getIri().equals(PredefinedElkIris.OWL_THING)) {
			throw new ElkMatchException(parent.getConclusionSubsumer(),
					subsumerMatch);
		}
		checkEquals(conclusionMatch, getParentConclusionMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	public SubClassInclusionComposedMatch1 getParentConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getConclusion(factory), getOriginMatch(),
				factory.getOwlThing());
	}

	public SubClassInclusionComposedMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch2(
				getParentConclusionMatch(factory), getOriginMatch());
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * The visitor pattern for instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 * @param <O>
	 *            the type of the output
	 */
	public interface Visitor<O> {

		O visit(SubClassInclusionOwlThingMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionOwlThingMatch1 getSubClassInclusionOwlThingMatch1(
				SubClassInclusionOwlThing parent,
				SubClassInclusionComposedMatch1 conclusionMatch);

	}

}
