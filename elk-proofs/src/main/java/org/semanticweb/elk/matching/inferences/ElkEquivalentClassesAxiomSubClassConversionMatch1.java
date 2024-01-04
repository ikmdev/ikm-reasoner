
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomSubClassConversion;

public class ElkEquivalentClassesAxiomSubClassConversionMatch1 extends
		AbstractInferenceMatch<ElkEquivalentClassesAxiomSubClassConversion> {

	ElkEquivalentClassesAxiomSubClassConversionMatch1(
			ElkEquivalentClassesAxiomSubClassConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkEquivalentClassesAxiomSubClassConversion parent = getParent();
		ElkEquivalentClassesAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkClassExpression> members = premise
				.getClassExpressions();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				members.get(parent.getSubClassPosition()),
				members.get(parent.getSuperClassPosition()));
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

		O visit(ElkEquivalentClassesAxiomSubClassConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkEquivalentClassesAxiomSubClassConversionMatch1 getElkEquivalentClassesAxiomSubClassConversionMatch1(
				ElkEquivalentClassesAxiomSubClassConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
