
package org.semanticweb.elk.reasoner.saturation.inferences;

import org.semanticweb.elk.reasoner.saturation.properties.inferences.ObjectPropertyInference;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.PropertyRangeInherited;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainExpandedSubObjectPropertyOf;
import org.semanticweb.elk.reasoner.saturation.properties.inferences.SubPropertyChainTautology;



/**
 * A {@link SaturationInference.Visitor} that always returns {@code null}.
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <O>
 *            the type of the output
 */
public class DummySaturationInferenceVisitor<O>
		implements
			SaturationInference.Visitor<O> {

	protected O defaultVisit(ClassInference inference) {
		return defaultVisit((SaturationInference) inference);
	}

	protected O defaultVisit(ObjectPropertyInference inference) {
		return defaultVisit((SaturationInference) inference);
	}

	protected O defaultVisit(
			@SuppressWarnings("unused") SaturationInference inference) {
		// can be overriden in sub-classes
		return null;
	}

	@Override
	public O visit(BackwardLinkComposition inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(BackwardLinkOfObjectHasSelf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(BackwardLinkOfObjectSomeValuesFrom inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(BackwardLinkReversedExpanded inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ClassInconsistencyOfDisjointSubsumers inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ClassInconsistencyOfObjectComplementOf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ClassInconsistencyOfOwlNothing inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ClassInconsistencyPropagated inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ContextInitializationNoPremises inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(DisjointSubsumerFromSubsumer inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ForwardLinkComposition inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ForwardLinkOfObjectHasSelf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(ForwardLinkOfObjectSomeValuesFrom inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(PropagationGenerated inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(PropertyRangeInherited inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionComposedDefinedClass inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionComposedEntity inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionComposedObjectIntersectionOf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionComposedObjectSomeValuesFrom inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionComposedObjectUnionOf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionDecomposedFirstConjunct inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionDecomposedSecondConjunct inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionExpandedDefinition inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionExpandedFirstEquivalentClass inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionExpandedSecondEquivalentClass inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionExpandedSubClassOf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionObjectHasSelfPropertyRange inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionOwlThing inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionRange inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubClassInclusionTautology inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubContextInitializationNoPremises inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubPropertyChainExpandedSubObjectPropertyOf inference) {
		return defaultVisit(inference);
	}

	@Override
	public O visit(SubPropertyChainTautology inference) {
		return defaultVisit(inference);
	}

}
