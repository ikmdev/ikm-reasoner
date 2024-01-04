
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassAssertionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkClassAssertionAxiomConversion;

public class ElkClassAssertionAxiomConversionMatch1
		extends AbstractInferenceMatch<ElkClassAssertionAxiomConversion> {

	ElkClassAssertionAxiomConversionMatch1(
			ElkClassAssertionAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkClassAssertionAxiomConversion parent = getParent();
		ElkClassAssertionAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectOneOf(premise.getIndividual()),
				premise.getClassExpression());
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

		O visit(ElkClassAssertionAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkClassAssertionAxiomConversionMatch1 getElkClassAssertionAxiomConversionMatch1(
				ElkClassAssertionAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
