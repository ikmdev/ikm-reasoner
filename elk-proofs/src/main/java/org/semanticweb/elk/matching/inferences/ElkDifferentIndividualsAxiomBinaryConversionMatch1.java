
package org.semanticweb.elk.matching.inferences;

import java.util.List;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomBinaryConversion;

public class ElkDifferentIndividualsAxiomBinaryConversionMatch1 extends
		AbstractInferenceMatch<ElkDifferentIndividualsAxiomBinaryConversion> {

	ElkDifferentIndividualsAxiomBinaryConversionMatch1(
			ElkDifferentIndividualsAxiomBinaryConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDifferentIndividualsAxiomBinaryConversion parent = getParent();
		ElkDifferentIndividualsAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkIndividual> disjoint = premise.getIndividuals();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectIntersectionOf(
						factory.getObjectOneOf(disjoint
								.get(parent.getFirstIndividualPosition())),
						factory.getObjectOneOf(disjoint
								.get(parent.getSecondIndividualPosition()))),
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

		O visit(ElkDifferentIndividualsAxiomBinaryConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDifferentIndividualsAxiomBinaryConversionMatch1 getElkDifferentIndividualsAxiomBinaryConversionMatch1(
				ElkDifferentIndividualsAxiomBinaryConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
