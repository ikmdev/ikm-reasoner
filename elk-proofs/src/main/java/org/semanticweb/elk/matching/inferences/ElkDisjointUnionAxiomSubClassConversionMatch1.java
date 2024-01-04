
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomSubClassConversion;

public class ElkDisjointUnionAxiomSubClassConversionMatch1 extends
		AbstractInferenceMatch<ElkDisjointUnionAxiomSubClassConversion> {

	ElkDisjointUnionAxiomSubClassConversionMatch1(
			ElkDisjointUnionAxiomSubClassConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointUnionAxiomSubClassConversion parent = getParent();
		ElkDisjointUnionAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				premise.getClassExpressions().get(parent.getDisjunctPosition()),
				premise.getDefinedClass());
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

		O visit(ElkDisjointUnionAxiomSubClassConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointUnionAxiomSubClassConversionMatch1 getElkDisjointUnionAxiomSubClassConversionMatch1(
				ElkDisjointUnionAxiomSubClassConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
