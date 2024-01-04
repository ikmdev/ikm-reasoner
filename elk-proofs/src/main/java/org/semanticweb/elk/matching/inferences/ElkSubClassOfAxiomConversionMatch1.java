
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkSubClassOfAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkSubClassOfAxiomConversion;

public class ElkSubClassOfAxiomConversionMatch1
		extends AbstractInferenceMatch<ElkSubClassOfAxiomConversion> {

	ElkSubClassOfAxiomConversionMatch1(ElkSubClassOfAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkSubClassOfAxiomConversion parent = getParent();
		ElkSubClassOfAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				premise.getSubClassExpression(),
				premise.getSuperClassExpression());
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

		O visit(ElkSubClassOfAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkSubClassOfAxiomConversionMatch1 getElkSubClassOfAxiomConversionMatch1(
				ElkSubClassOfAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
