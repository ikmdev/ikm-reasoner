
package org.semanticweb.elk.matching.inferences;

import java.util.ArrayList;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedDisjointClassesAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDifferentIndividualsAxiom;
import org.semanticweb.elk.owl.interfaces.ElkIndividual;
import org.semanticweb.elk.reasoner.indexing.model.ElkDifferentIndividualsAxiomNaryConversion;

public class ElkDifferentIndividualsAxiomNaryConversionMatch1 extends
		AbstractInferenceMatch<ElkDifferentIndividualsAxiomNaryConversion> {

	ElkDifferentIndividualsAxiomNaryConversionMatch1(
			ElkDifferentIndividualsAxiomNaryConversion parent,
			IndexedDisjointClassesAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedDisjointClassesAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDifferentIndividualsAxiomNaryConversion parent = getParent();
		ElkDifferentIndividualsAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkIndividual> different = premise.getIndividuals();
		int size = different.size();
		List<ElkClassExpression> disjoint = new ArrayList<ElkClassExpression>(
				size);
		for (int i = 0; i < size; i++) {
			disjoint.set(i, factory.getObjectOneOf(different.get(i)));
		}
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

		O visit(ElkDifferentIndividualsAxiomNaryConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDifferentIndividualsAxiomNaryConversionMatch1 getElkDifferentIndividualsAxiomNaryConversionMatch1(
				ElkDifferentIndividualsAxiomNaryConversion parent,
				IndexedDisjointClassesAxiomMatch1 conclusionMatch);

	}

}
