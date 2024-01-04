
package org.semanticweb.elk.reasoner.saturation.inferences;



/**
 * A {@link ClassInference.Visitor} that returns {@code null}, can be used as a
 * prototype of other visitors by overriding default methods or or other visit
 * methods
 * 
 * @author Pavel Klinov
 * 
 *         pavel.klinov@uni-ulm.de
 * 
 * @author Yevgeny Kazakov
 * 
 * @param <O>
 *            the type of the output
 */
public class DummyClassInferenceVisitor<O>
		implements
			ClassInference.Visitor<O> {

	/**
	 * @param inference
	 * @return {@code null} by default; should be overridden in sub-classes
	 */
	protected O defaultVisit(ClassInference inference) {
		return null;
	}

	protected O defaultVisit(SubClassInference inference) {
		return defaultVisit((ClassInference) inference);
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

}
