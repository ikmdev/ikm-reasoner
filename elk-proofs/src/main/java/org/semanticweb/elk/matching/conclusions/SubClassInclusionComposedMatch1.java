
package org.semanticweb.elk.matching.conclusions;

import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.SubsumerMatch;



import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkObjectIntersectionOf;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.saturation.conclusions.model.SubClassInclusionComposed;

public class SubClassInclusionComposedMatch1
		extends SubClassInclusionMatch<SubClassInclusionComposed> {

	private final IndexedContextRootMatch destinationMatch_;

	SubClassInclusionComposedMatch1(SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkClassExpression subsumerMatchValue) {
		super(parent, subsumerMatchValue);
		this.destinationMatch_ = destinationMatch;
	}

	SubClassInclusionComposedMatch1(SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkIndividual subsumerMatchValue) {
		super(parent, subsumerMatchValue);
		this.destinationMatch_ = destinationMatch;
	}
	
	SubClassInclusionComposedMatch1(SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			ElkObjectIntersectionOf fullSubsumerMatch,
			int subsumerPrefixLength) {
		super(parent, fullSubsumerMatch, subsumerPrefixLength);
		this.destinationMatch_ = destinationMatch;
	}

	SubClassInclusionComposedMatch1(SubClassInclusionComposed parent,
			IndexedContextRootMatch destinationMatch,
			SubsumerMatch subsumerMatch) {
		super(parent, subsumerMatch);
		this.destinationMatch_ = destinationMatch;
	}

	@Override
	IndexedClassExpression getSubsumer() {
		return getParent().getSubsumer();
	}

	public IndexedContextRootMatch getDestinationMatch() {
		return destinationMatch_;
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

		SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkClassExpression subsumerMatchValue);
		
		SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkIndividual subsumerMatchValue);

		SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
				ElkObjectIntersectionOf fullSubsumerMatch,
				int subsumerPrefixLength);

		SubClassInclusionComposedMatch1 getSubClassInclusionComposedMatch1(
				SubClassInclusionComposed parent,
				IndexedContextRootMatch destinationMatch,
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

		O visit(SubClassInclusionComposedMatch1 conclusionMatch);

	}

}
