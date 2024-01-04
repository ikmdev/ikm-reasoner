
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1Watch;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.reasoner.saturation.inferences.DisjointSubsumerFromSubsumer;

public class DisjointSubsumerFromSubsumerMatch1
		extends AbstractInferenceMatch<DisjointSubsumerFromSubsumer>
		implements IndexedDisjointClassesAxiomMatch1Watch {

	private final IndexedContextRootMatch originMatch_;

	DisjointSubsumerFromSubsumerMatch1(DisjointSubsumerFromSubsumer parent,
			DisjointSubsumerMatch1 conclusionMatch) {
		super(parent);
		this.originMatch_ = conclusionMatch.getDestinationMatch();
		checkEquals(conclusionMatch, getConclusionMatch(DEBUG_FACTORY));
	}

	IndexedContextRootMatch getOriginMatch() {
		return originMatch_;
	}

	DisjointSubsumerMatch1 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getDisjointSubsumerMatch1(
				getParent().getConclusion(factory), getOriginMatch());
	}

	public IndexedDisjointClassesAxiomMatch1 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getIndexedDisjointClassesAxiomMatch1(
				getParent().getSecondPremise(factory));
	}

	@Override
	public <O> O accept(InferenceMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	@Override
	public <O> O accept(
			IndexedDisjointClassesAxiomMatch1Watch.Visitor<O> visitor) {
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

		O visit(DisjointSubsumerFromSubsumerMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		DisjointSubsumerFromSubsumerMatch1 getDisjointSubsumerFromSubsumerMatch1(
				DisjointSubsumerFromSubsumer parent,
				DisjointSubsumerMatch1 conclusionMatch);

	}

}
