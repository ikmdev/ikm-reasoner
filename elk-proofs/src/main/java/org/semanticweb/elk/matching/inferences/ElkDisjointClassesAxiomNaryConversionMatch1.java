 
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomNaryConversion;

public class ElkDisjointClassesAxiomNaryConversionMatch1
		extends AbstractInferenceMatch<ElkDisjointClassesAxiomNaryConversion> {

	ElkDisjointClassesAxiomNaryConversionMatch1(
			ElkDisjointClassesAxiomNaryConversion parent,
			IndexedDisjointClassesAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedDisjointClassesAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointClassesAxiomNaryConversion parent = getParent();
		ElkDisjointClassesAxiom premise = parent.getOriginalAxiom();
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

		O visit(ElkDisjointClassesAxiomNaryConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointClassesAxiomNaryConversionMatch1 getElkDisjointClassesAxiomNaryConversionMatch1(
				ElkDisjointClassesAxiomNaryConversion parent,
				IndexedDisjointClassesAxiomMatch1 conclusionMatch);

	}

}
