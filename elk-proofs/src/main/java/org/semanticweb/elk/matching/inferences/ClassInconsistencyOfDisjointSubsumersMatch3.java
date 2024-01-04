
package org.semanticweb.elk.matching.inferences;

import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.ClassInconsistencyMatch2;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.DisjointSubsumerMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;

public class ClassInconsistencyOfDisjointSubsumersMatch3 extends
		AbstractInferenceMatch<ClassInconsistencyOfDisjointSubsumersMatch2> {

	private final IndexedContextRootMatch extendedExtendedOriginMatch_;

	ClassInconsistencyOfDisjointSubsumersMatch3(
			ClassInconsistencyOfDisjointSubsumersMatch2 parent,
			DisjointSubsumerMatch2 secondPremiseMatch) {
		super(parent);
		if (!secondPremiseMatch.getDisjointExpressionsMatch()
				.equals(getParent().getDisjointExpressionsMatch())) {
			throw new ElkMatchException(
					parent.getParent().getParent().getDisjointExpressions(),
					secondPremiseMatch.getDisjointExpressionsMatch());
		}
		this.extendedExtendedOriginMatch_ = secondPremiseMatch
				.getExtendedDestinationMatch();
		checkEquals(secondPremiseMatch,
				getSecondPremiseMatch(DEBUG_FACTORY));
	}

	public IndexedContextRootMatch getExtendedExtendedOriginMatch() {
		return extendedExtendedOriginMatch_;
	}

	DisjointSubsumerMatch2 getSecondPremiseMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getDisjointSubsumerMatch2(
				getParent().getSecondPremiseMatch(factory),
				getExtendedExtendedOriginMatch(),
				getParent().getDisjointExpressionsMatch());
	}

	public ClassInconsistencyMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		return factory.getClassInconsistencyMatch2(
				getParent().getParent().getConclusionMatch(factory),
				getExtendedExtendedOriginMatch());
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

		O visit(ClassInconsistencyOfDisjointSubsumersMatch3 inferenceMatch3);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ClassInconsistencyOfDisjointSubsumersMatch3 getClassInconsistencyOfDisjointSubsumersMatch3(
				ClassInconsistencyOfDisjointSubsumersMatch2 parent,
				DisjointSubsumerMatch2 secondPremiseMatch);

	}

}
