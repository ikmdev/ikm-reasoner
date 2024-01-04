
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomOwlNothingConversion;

public class ElkDisjointUnionAxiomOwlNothingConversionMatch1 extends
		AbstractInferenceMatch<ElkDisjointUnionAxiomOwlNothingConversion> {

	ElkDisjointUnionAxiomOwlNothingConversionMatch1(
			ElkDisjointUnionAxiomOwlNothingConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointUnionAxiomOwlNothingConversion parent = getParent();
		ElkDisjointUnionAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						getParent().getConclusion(factory)),
				premise.getDefinedClass(), factory.getOwlNothing());
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

		O visit(ElkDisjointUnionAxiomOwlNothingConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointUnionAxiomOwlNothingConversionMatch1 getElkDisjointUnionAxiomOwlNothingConversionMatch1(
				ElkDisjointUnionAxiomOwlNothingConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
