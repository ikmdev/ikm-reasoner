
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomNaryConversion;

public class ElkDisjointUnionAxiomNaryConversionMatch1
		extends AbstractInferenceMatch<ElkDisjointUnionAxiomNaryConversion> {

	ElkDisjointUnionAxiomNaryConversionMatch1(
			ElkDisjointUnionAxiomNaryConversion parent,
			IndexedDisjointClassesAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedDisjointClassesAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointUnionAxiomNaryConversion parent = getParent();
		ElkDisjointUnionAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkClassExpression> disjoint = premise
				.getClassExpressions();
		return factory.getIndexedDisjointClassesAxiomMatch2(
				factory.getIndexedDisjointClassesAxiomMatch1(
						parent.getConclusion(factory)),
				disjoint);
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

		O visit(ElkDisjointUnionAxiomNaryConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointUnionAxiomNaryConversionMatch1 getElkDisjointUnionAxiomNaryConversionMatch1(
				ElkDisjointUnionAxiomNaryConversion parent,
				IndexedDisjointClassesAxiomMatch1 conclusionMatch);

	}

}
