
package org.semanticweb.elk.matching.inferences;



import java.util.List;

import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedEquivalentClassesAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkClassExpression;
import org.semanticweb.elk.owl.interfaces.ElkDisjointUnionAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkDisjointUnionAxiomEquivalenceConversion;

public class ElkDisjointUnionAxiomEquivalenceConversionMatch1 extends
		AbstractInferenceMatch<ElkDisjointUnionAxiomEquivalenceConversion> {

	ElkDisjointUnionAxiomEquivalenceConversionMatch1(
			ElkDisjointUnionAxiomEquivalenceConversion parent,
			IndexedEquivalentClassesAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedEquivalentClassesAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkDisjointUnionAxiomEquivalenceConversion parent = getParent();
		ElkDisjointUnionAxiom premise = parent.getOriginalAxiom();
		List<? extends ElkClassExpression> members = premise
				.getClassExpressions();
		return factory.getIndexedEquivalentClassesAxiomMatch2(
				factory.getIndexedEquivalentClassesAxiomMatch1(
						getParent().getConclusion(factory)),
				premise.getDefinedClass(), members.get(0));
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

		O visit(ElkDisjointUnionAxiomEquivalenceConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkDisjointUnionAxiomEquivalenceConversionMatch1 getElkDisjointUnionAxiomEquivalenceConversionMatch1(
				ElkDisjointUnionAxiomEquivalenceConversion parent,
				IndexedEquivalentClassesAxiomMatch1 conclusionMatch);

	}

}
