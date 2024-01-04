
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.SubsumerMatch;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;

public class SubClassInclusionDecomposedMatch2
		extends SubClassInclusionMatch<SubClassInclusionDecomposedMatch1> {

	private final IndexedContextRootMatch extendedDestinationMatch_;

	SubClassInclusionDecomposedMatch2(SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			ElkClassExpression subsumerMatchValue) {
		super(parent, subsumerMatchValue);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	SubClassInclusionDecomposedMatch2(SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			ElkIndividual subsumerMatchValue) {
		super(parent, subsumerMatchValue);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	SubClassInclusionDecomposedMatch2(SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			ElkObjectIntersectionOf subsumerMatchFullValue,
			int subsumerMatchPrefixLength) {
		super(parent, subsumerMatchFullValue, subsumerMatchPrefixLength);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	SubClassInclusionDecomposedMatch2(SubClassInclusionDecomposedMatch1 parent,
			IndexedContextRootMatch extendedDestinationMatch,
			SubsumerMatch subsumerMatch) {
		super(parent, subsumerMatch);
		this.extendedDestinationMatch_ = extendedDestinationMatch;
	}

	public IndexedContextRootMatch getExtendedDestinationMatch() {
		return extendedDestinationMatch_;
	}

	@Override
	IndexedClassExpression getSubsumer() {
		return getParent().getParent().getSubsumer();
	}

	@Override
	public <O> O accept(ClassConclusionMatch.Visitor<O> visitor) {
		return visitor.visit(this);
	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				ElkClassExpression subsumerMatchValue);

		SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				ElkIndividual subsumerMatchValue);

		SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				ElkObjectIntersectionOf subsumerMatchFullValue,
				int subsumerMatchPrefixLength);

		SubClassInclusionDecomposedMatch2 getSubClassInclusionDecomposedMatch2(
				SubClassInclusionDecomposedMatch1 parent,
				IndexedContextRootMatch extendedDestinationMatch,
				SubsumerMatch subsumerMatch);

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

		O visit(SubClassInclusionDecomposedMatch2 conclusionMatch);

	}

}
