
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkObjectPropertyAssertionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkObjectPropertyAssertionAxiomConversion;

public class ElkObjectPropertyAssertionAxiomConversionMatch1 extends
		AbstractInferenceMatch<ElkObjectPropertyAssertionAxiomConversion> {

	ElkObjectPropertyAssertionAxiomConversionMatch1(
			ElkObjectPropertyAssertionAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkObjectPropertyAssertionAxiomConversion parent = getParent();
		ElkObjectPropertyAssertionAxiom premise = parent.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectOneOf(premise.getSubject()),
				factory.getObjectSomeValuesFrom(premise.getProperty(),
						factory.getObjectOneOf(premise.getObject())));
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

		O visit(ElkObjectPropertyAssertionAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkObjectPropertyAssertionAxiomConversionMatch1 getElkObjectPropertyAssertionAxiomConversionMatch1(
				ElkObjectPropertyAssertionAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
