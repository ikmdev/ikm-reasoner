
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1;
import org.semanticweb.elk.matching.conclusions.PropertyRangeMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;

public class SubClassInclusionObjectHasSelfPropertyRangeMatch2 extends
		AbstractInferenceMatch<SubClassInclusionObjectHasSelfPropertyRangeMatch1>
		implements PropertyRangeMatch1Watch {

	private final IndexedContextRootMatch extendedOriginMatch_;

	private final ElkObjectProperty propertyMatch_;

	SubClassInclusionObjectHasSelfPropertyRangeMatch2(
			SubClassInclusionObjectHasSelfPropertyRangeMatch1 parent,
			SubClassInclusionDecomposedMatch2 firstPremiseMatch) {
		super(parent);
		this.extendedOriginMatch_ = firstPremiseMatch
				.getExtendedDestinationMatch();
		ElkObjectHasSelf premiseSubsumerMatch = firstPremiseMatch
				.getSubsumerIndexedObjectHasSelfMatch();
		ElkObjectPropertyExpression premisePropertyMatch = premiseSubsumerMatch
				.getProperty();
		if (premisePropertyMatch instanceof ElkObjectProperty) {
			this.propertyMatch_ = (ElkObjectProperty) premisePropertyMatch;
		} else {
			throw new ElkMatchException(parent.getParent().getSubsumer(),
					premiseSubsumerMatch);
		}
		checkEquals(firstPremiseMatch, getFirstPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedOriginMatch() {
		return extendedOriginMatch_;
	}

	public ElkObjectProperty getPropertyMatch() {
		return propertyMatch_;
	}

	SubClassInclusionDecomposedMatch2 getFirstPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionDecomposedMatch2(
				getParent().getFirstPremiseMatch(factory),
				getExtendedOriginMatch(),
				factory.getObjectHasSelf(getPropertyMatch()));
	}

	public PropertyRangeMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getPropertyRangeMatch1(
				getParent().getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(PropertyRangeMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionObjectHasSelfPropertyRangeMatch2 inferenceMatch2);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionObjectHasSelfPropertyRangeMatch2 getSubClassInclusionObjectHasSelfPropertyRangeMatch2(
				SubClassInclusionObjectHasSelfPropertyRangeMatch1 parent,
				SubClassInclusionDecomposedMatch2 firstPremiseMatch);

	}

}
