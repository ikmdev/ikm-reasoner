
package org.semanticweb.elk.matching.inferences;



import org.semanticweb.elk.matching.conclusions.ConclusionMatchExpressionFactory;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch1;
import org.semanticweb.elk.matching.conclusions.IndexedSubClassOfAxiomMatch2;
import org.semanticweb.elk.owl.interfaces.ElkReflexiveObjectPropertyAxiom;
import org.semanticweb.elk.reasoner.indexing.model.ElkReflexiveObjectPropertyAxiomConversion;

public class ElkReflexiveObjectPropertyAxiomConversionMatch1 extends
		AbstractInferenceMatch<ElkReflexiveObjectPropertyAxiomConversion> {

	ElkReflexiveObjectPropertyAxiomConversionMatch1(
			ElkReflexiveObjectPropertyAxiomConversion parent,
			IndexedSubClassOfAxiomMatch1 conclusionMatch) {
		super(parent);
	}

	public IndexedSubClassOfAxiomMatch2 getConclusionMatch(
			ConclusionMatchExpressionFactory factory) {
		ElkReflexiveObjectPropertyAxiomConversion parent = getParent();
		ElkReflexiveObjectPropertyAxiom premise = getParent()
				.getOriginalAxiom();
		return factory.getIndexedSubClassOfAxiomMatch2(
				factory.getIndexedSubClassOfAxiomMatch1(
						parent.getConclusion(factory)),
				factory.getOwlThing(),
				factory.getObjectHasSelf(premise.getProperty()));
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

		O visit(ElkReflexiveObjectPropertyAxiomConversionMatch1 inferenceMatch1);

	}

	/**
	 * A factory for creating instances
	 * 
	 * @author Yevgeny Kazakov
	 *
	 */
	public interface Factory {

		ElkReflexiveObjectPropertyAxiomConversionMatch1 getElkReflexiveObjectPropertyAxiomConversionMatch1(
				ElkReflexiveObjectPropertyAxiomConversion parent,
				IndexedSubClassOfAxiomMatch1 conclusionMatch);

	}

}
