
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.ElkMatchException;
import org.semanticweb.elk.matching.conclusions.SubClassInclusionDecomposedMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectHasSelf;
import org.semanticweb.elk.owl.interfaces.ElkObjectProperty;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyExpression;
import org.semanticweb.elk.reasoner.indexing.model.IndexedObjectProperty;

abstract class LinkOfObjectHasSelfMatch2<P> extends AbstractInferenceMatch<P> {

	private final ElkObjectHasSelf premiseSuperExpressionMatch_;

	public LinkOfObjectHasSelfMatch2(P parent,
			SubClassInclusionDecomposedMatch2 premiseMatch) {
		super(parent);
		this.premiseSuperExpressionMatch_ = premiseMatch
				.getSubsumerIndexedObjectHasSelfMatch();
	}

	ElkObjectHasSelf getPremiseSuperExpressionMatch() {
		return premiseSuperExpressionMatch_;
	}

	ElkObjectProperty getPremisePropertyMatch(
			IndexedObjectProperty premiseProperty) {
		ElkObjectPropertyExpression premisePropertyMatch = premiseSuperExpressionMatch_
				.getProperty();
		if (premisePropertyMatch instanceof ElkObjectProperty) {
			return (ElkObjectProperty) premisePropertyMatch;
		} else {
			throw new ElkMatchException(premiseProperty, premisePropertyMatch);
		}
	}

}
