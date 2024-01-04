
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointClassesAxiomBinaryConversion;

public class ElkDisjointClassesAxiomBinaryConversionMatch1 extends
		AbstractInferenceMatch<ElkDisjointClassesAxiomBinaryConversion> {

	ElkDisjointClassesAxiomBinaryConversionMatch1(
			ElkDisjointClassesAxiomBinaryConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointClassesAxiomBinaryConversion parent = getParent();
		ElkDisjointClassesAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkClassExpression> disjoint = premise
				.getClassExpressions();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectIntersectionOf(
						disjoint.get(parent.getFirstClassPosition()),
						disjoint.get(parent.getSecondClassPosition())),
				factory.getOwlNothing());
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

		O visit(ElkDisjointClassesAxiomBinaryConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointClassesAxiomBinaryConversionMatch1 getElkDisjointClassesAxiomBinaryConversionMatch1(
				ElkDisjointClassesAxiomBinaryConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
