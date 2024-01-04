
package org.semanticweb.elk.matching.inferences;

import java.util.List;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.owl.interfaces.ElkSameIndividualAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkSameIndividualAxiomConversion;

public class ElkSameIndividualAxiomConversionMatch1
		extends AbstractInferenceMatch<ElkSameIndividualAxiomConversion> {

	ElkSameIndividualAxiomConversionMatch1(
			ElkSameIndividualAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkSameIndividualAxiomConversion parent = getParent();
		ElkSameIndividualAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkIndividual> members = premise.getIndividuals();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getObjectOneOf(
						members.get(parent.getSubIndividualPosition())),
				factory.getObjectOneOf(
						members.get(parent.getSuperIndividualPosition())));
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

		O visit(ElkSameIndividualAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkSameIndividualAxiomConversionMatch1 getElkSameIndividualAxiomConversionMatch1(
				ElkSameIndividualAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
