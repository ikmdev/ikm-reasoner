
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedObjectPropertyRangeAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.PropertyRangeInherited;

public class PropertyRangeInheritedMatch1
		extends AbstractInferenceMatch<PropertyRangeInherited>
		implements IndexedObjectPropertyRangeAxiomMatch1Watch {

	PropertyRangeInheritedMatch1(PropertyRangeInherited parent,
			PropertyRangeMatch1 conclusionMatch) {
		super(parent);
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	PropertyRangeMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory
				.getPropertyRangeMatch1(getParent().getConclusion(factory));
	}

	public IndexedObjectPropertyRangeAxiomMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedObjectPropertyRangeAxiomMatch1(
				getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			IndexedObjectPropertyRangeAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(PropertyRangeInheritedMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		PropertyRangeInheritedMatch1 getPropertyRangeInheritedMatch1(
				PropertyRangeInherited parent,
				PropertyRangeMatch1 conclusionMatch);

	}

}
