
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.matching.root.IndexedContextRootMatch;
import org.semanticweb.elk.matching.subsumers.IndexedObjectSomeValuesFromMatch;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.reasoner.indexing.classes.DummyIndexedContextRootVisitor;
import org.semanticweb.elk.reasoner.indexing.model.IndexedClassExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedContextRoot;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;
import org.semanticweb.elk.reasoner.indexing.model.IndexedRangeFiller;

abstract class LinkOfObjectSomeValuesFromMatch2<P>
		extends AbstractInferenceMatch<P> {

	private final IndexedObjectSomeValuesFromMatch premiseSuperExpressionMatch_;

	public LinkOfObjectSomeValuesFromMatch2(P parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent);
		this.premiseSuperExpressionMatch_ = premiseMatch
				.getSubsumerIndexedObjectSomeValuesFromMatch();
	}

	public IndexedObjectSomeValuesFromMatch getPremiseSuperExpressionMatch() {
		return premiseSuperExpressionMatch_;
	}

	ElkObjectProperty getPremisePropertyMatch(
			IndexedObjectProperty premiseProperty) {
		ElkObjectPropertyExpression premisePropertyMatch = premiseSuperExpressionMatch_
				.getPropertyMatch();
		if (premisePropertyMatch instanceof ElkObjectProperty) {
			return (ElkObjectProperty) premisePropertyMatch;
		} else {
			throw new ElkMatchException(premiseProperty, premisePropertyMatch);
		}
	}

	IndexedContextRootMatch getRootMatch(IndexedContextRoot root,
			final IndexedContextRootMatch.Factory factory) {
		return root.accept(
				new DummyIndexedContextRootVisitor<IndexedContextRootMatch>() {

					@Override
					protected IndexedContextRootMatch defaultVisit(
							IndexedClassExpression element) {
						return premiseSuperExpressionMatch_
								.getFillerRootMatch(factory);
					}

					@Override
					public IndexedContextRootMatch visit(
							IndexedRangeFiller element) {
						return premiseSuperExpressionMatch_
								.getRangeRootMatch(factory);
					}

				});
	}

}
