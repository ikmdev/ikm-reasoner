
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkEquivalentClassesAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkEquivalentClassesAxiomEquivalenceConversion;

public class ElkEquivalentClassesAxiomEquivalenceConversionMatch1 extends
		AbstractInferenceMatch<ElkEquivalentClassesAxiomEquivalenceConversion> {

	ElkEquivalentClassesAxiomEquivalenceConversionMatch1(
			ElkEquivalentClassesAxiomEquivalenceConversion parent,
			IndexedEquivalentClassesAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedEquivalentClassesAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkEquivalentClassesAxiomEquivalenceConversion parent = getParent();
		ElkEquivalentClassesAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkClassExpression> members = premise
				.getClassExpressions();
		return factory.getIndexedEquivalentClassesAxiomMatch2(
				factory.getIndexedEquivalentClassesAxiomMatch1(
						getParent().getConclusion(factory)),
				members.get(parent.getFirstMemberPosition()),
				members.get(parent.getSecondMemberPosition()));
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

		O visit(ElkEquivalentClassesAxiomEquivalenceConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkEquivalentClassesAxiomEquivalenceConversionMatch1 getElkEquivalentClassesAxiomEquivalenceConversionMatch1(
				ElkEquivalentClassesAxiomEquivalenceConversion parent,
				IndexedEquivalentClassesAxiomMatch1 conclusionMatch);

	}

}
