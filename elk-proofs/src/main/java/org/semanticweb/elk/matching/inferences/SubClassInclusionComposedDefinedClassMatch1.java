
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionComposedMatch1;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.owl.interfaces.ElkClass;
import org.semanticweb.elk.reasoner.saturation.inferences.SubClassInclusionComposedDefinedClass;

public class SubClassInclusionComposedDefinedClassMatch1
		extends AbstractInferenceMatch<SubClassInclusionComposedDefinedClass>
		implements IndexedEquivalentClassesAxiomMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	private final ElkClass definedClassMatch_;

	SubClassInclusionComposedDefinedClassMatch1(
			SubClassInclusionComposedDefinedClass parent,
			SubClassInclusionComposedMatch1 conclusionMatch) {
		super(parent);
		this.originMatch_ = conclusionMatch.getDestinationMatch();
		definedClassMatch_ = conclusionMatch.getSubsumerElkClassMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	public ElkClass getDefinedClassMatch() {
		return definedClassMatch_;
	}

	SubClassInclusionComposedMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getSubClassInclusionComposedMatch1(
				getParent().getConclusion(factory), getOriginMatch(),
				getDefinedClassMatch());
	}

	public IndexedEquivalentClassesAxiomMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedEquivalentClassesAxiomMatch1(
				getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			IndexedEquivalentClassesAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(SubClassInclusionComposedDefinedClassMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionComposedDefinedClassMatch1 getSubClassInclusionComposedDefinedClassMatch1(
				SubClassInclusionComposedDefinedClass parent,
				SubClassInclusionComposedMatch1 conclusionMatch);

	}

}
